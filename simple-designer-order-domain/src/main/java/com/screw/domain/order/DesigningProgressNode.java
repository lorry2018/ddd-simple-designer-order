package com.screw.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.screw.domain.DomainException;
import com.screw.domain.DomainExceptionMessage;
import com.screw.domain.Entity;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString(of = {"nodeName"})
public class DesigningProgressNode implements Entity<DesigningProgressNode> {
    private static Map<DesigningProgressNodeState, DesigningProgressNodeState> states = new HashMap<>();
    static {
        states.put(DesigningProgressNodeState.NOT_STARTED, DesigningProgressNodeState.STARTED);
        states.put(DesigningProgressNodeState.STARTED, DesigningProgressNodeState.REQUEST_COMPLETION);
        states.put(DesigningProgressNodeState.REQUEST_COMPLETION, DesigningProgressNodeState.CONFIRM_COMPLETION);
    }

    public DesigningProgressNodeState changeState(DesigningProgressNode node, DesigningProgressNodeState nextState) {
        if (states.get(node.state).equals(nextState)) {
            return nextState;
        }

        DomainException.throwDomainException(DomainExceptionMessage.PROGRESS_UPDATE_FAILED_FOR_ERROR_STATE_CODE, DomainExceptionMessage.PROGRESS_UPDATE_FAILED_FOR_ERROR_STATE, node.getReport().getOrder().getId(), node.nodeName, node.state, nextState);
        return null;
    }

    @JsonIgnore
    private DesigningProgressReport report;
    private String nodeName;
    private DesigningProgressNodeType nodeType;
    private int estimatedDays;
    private boolean completed;
    private DesigningProgressNodeState state;

    private String achievement; // 成果的URL或者内容，仅供测试，设计前三阶段实际成果为几个文件的合集
    private Date requestCompletionTime;
    private Date confirmCompletionTime;

    public void startup() {
        this.state = changeState(this, DesigningProgressNodeState.STARTED);
    }

    public void requestCompletion(String achievement) {
        this.state = changeState(this, DesigningProgressNodeState.REQUEST_COMPLETION);
        this.achievement = achievement;
        this.requestCompletionTime = new Date();
    }
    public void confirmCompletion() {
        this.state = changeState(this, DesigningProgressNodeState.CONFIRM_COMPLETION);
        this.confirmCompletionTime = new Date();
        this.completed = true;
    }

    @Override
    public boolean sameIdentityAs(DesigningProgressNode other) {
        if (null == other) {
            return false;
        }
        return this.nodeType.equals(other.nodeType) && this.report.equals(other.report);
    }
}
