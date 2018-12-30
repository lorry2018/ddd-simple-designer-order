package com.screw.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.screw.BusinessException;
import com.screw.domain.DomainExceptionMessage;
import com.screw.domain.ValueObject;
import lombok.Data;
import lombok.ToString;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@ToString(of = {"nodes"})
public class DesigningProgressReport implements ValueObject<DesigningProgressReport> {
    @JsonIgnore
    private DesignerOrder order;
    private List<DesigningProgressNode> nodes;
    private Date startupTime;
    private int estimatedCompletionDays;
    private Date estimatedCompletionTime;
    private int actualCompletionDays;
    private Date actualCompletionTime;
    private boolean completed;

    public DesigningProgressNode getNode(DesigningProgressNodeType nodeType) {
        for (DesigningProgressNode node : this.nodes) {
            if (node.getNodeType().getCode() == nodeType.getCode()) {
                return node;
            }
        }

        BusinessException.throwException(DomainExceptionMessage.FAILED_TO_GET_PROGRESS_NODE_CODE, DomainExceptionMessage.FAILED_TO_GET_PROGRESS_NODE, nodeType.getName());
        return null;
    }

    private void assertChangeProgressNode(DesigningProgressNodeType nodeType) {
        if (this.getOrder().getState() != DesignerOrderState.PAID) {
            BusinessException.throwException(DomainExceptionMessage.PROGRESS_UPDATE_FAILED_FOR_ERROR_STATE_CODE, DomainExceptionMessage.PROGRESS_UPDATE_FAILED_FOR_ERROR_STATE, this.getOrder().getId(), this.getOrder().getState());
        }

        DesigningProgressNode node = this.getNode(nodeType);
        int currentNodeIndex = nodes.indexOf(node);
        for (currentNodeIndex --; currentNodeIndex >=0; currentNodeIndex --) {
            if (!this.nodes.get(currentNodeIndex).isCompleted()) {
                BusinessException.throwException(DomainExceptionMessage.PROGRESS_UPDATE_FAILED_FOR_ERROR_STATE_CODE, DomainExceptionMessage.PROGRESS_UPDATE_FAILED_FOR_ERROR_STATE, this.getOrder().getState());
            }
        }
    }

    public void startup() {
        this.setStartupTime(new Date());

        Calendar cal = Calendar.getInstance();
        cal.setTime(this.startupTime);
        cal.add(Calendar.DATE, this.estimatedCompletionDays);

        this.setEstimatedCompletionTime(cal.getTime());

        this.startupFirstProgressNode();
    }

    private void startupFirstProgressNode() {
        this.nodes.get(0).startup();
    }

    private void startupNextProgressNode(DesigningProgressNode node) {
        int currentNodeIndex = this.nodes.indexOf(node);
        this.nodes.get(currentNodeIndex + 1).startup();
    }

    public void requestCompletionForProgressNode(DesigningProgressNodeType nodeType, String archievement) {
        this.assertChangeProgressNode(nodeType);

        DesigningProgressNode node = this.getNode(nodeType);
        node.requestCompletion(archievement);
    }

    public void confirmCompletionForProgressNode(DesigningProgressNodeType nodeType) {
        this.assertChangeProgressNode(nodeType);

        DesigningProgressNode node = this.getNode(nodeType);
        node.confirmCompletion();

        if (node.getNodeType().getCode() == DesigningProgressNodeType.DISCLOSURE.getCode()) {
            this.complete();
        } else {
            this.startupNextProgressNode(node);
        }
    }

    private void complete() {
        int completionDays = (int)((new Date().getTime() - this.startupTime.getTime())/(1000 * 60 * 60 * 24));

        this.setCompleted(true);
        this.setActualCompletionTime(new Date());
        this.setActualCompletionDays(completionDays);

        this.order.complete();
    }

    public int computeOutOfDays() {
        int result = actualCompletionDays - estimatedCompletionDays;
        if (result < 0) {
            result = 0;
        }
        return result;
    }

    @Override
    public boolean sameValueAs(DesigningProgressReport other) {
        if (null == other) {
            return false;
        }
        return this.order.getId() == other.getOrder().getId();
    }
}
