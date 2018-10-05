package com.screw.domain.order;

import com.screw.domain.DomainException;
import com.screw.domain.DomainExceptionMessage;
import com.screw.domain.Entity;
import com.screw.domain.refund.RefundOrder;
import com.screw.domain.refund.RefundOrderFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.Assert;

import java.util.Date;

@Data
@EqualsAndHashCode(of = {"id"})
public class DesignerOrder implements Entity<DesignerOrder> {
    private int id;
    private DesignerOrderState state;
    private int customerId;
    private int designerId;
    private float area;

    private float expectedAmount;
    private int estimatedDays;
    private DesigningProgressReport report;

    private String abortCause;

    private float actualPaidAmount;

    private int feedbackStar;
    private String feedbackDescription;

    private Date createdTime;
    private Date updatedTime;

    public void measure(float area) {
        Assert.isTrue(area > 0, "The area must be bigger than 0.");

        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.MEASURED);
        this.area = area;
    }

    public void quote(float amount, int[] estimatedDaysList) {
        Assert.isTrue(amount > 0, "The price must be bigger than 0.");
        this.assertEstimatedDaysList(estimatedDaysList);

        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.QUOTED);
        this.expectedAmount = amount;
        this.report = DesigningProgressReportFactory.newReport(this, estimatedDaysList);
        this.estimatedDays = this.report.getEstimatedCompletionDays();
    }

    private void assertEstimatedDaysList(int[] estimatedDaysList) {
        if (null == estimatedDaysList || estimatedDaysList.length != 4) {
            throw new IllegalArgumentException("The size of estimatedDaysList must be 4.");
        }

        for (int days : estimatedDaysList) {
            if (days <= 0) {
                throw new IllegalArgumentException("Each element of estimatedDaysList must be bigger than 0.");
            }
        }
    }

    public void acceptQuote() {
        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.ACCEPT_QUOTE);
    }

    public void rejectQuote() {
        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.REJECT_QUOTE);
    }

    public void abort(String cause) {
        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.ABORTED);
        this.abortCause = cause;
    }

    public void pay(float amount) {
        Assert.isTrue(amount > 0, "The amount must be bigger than 0.");

        if (!DesignerOrderWorkflowService.canChangeState(state, DesignerOrderState.PAID)) {
            DomainException.throwDomainException(DomainExceptionMessage.PAYMENT_NOT_IN_READY_STATE_CODE, DomainExceptionMessage.PAYMENT_NOT_IN_READY_STATE, this.id, this.state);
        }

        if (Math.abs(amount - this.expectedAmount) < 0.0001) {
            DomainException.throwDomainException(DomainExceptionMessage.PAYMENT_NOT_MATCHED_CODE, DomainExceptionMessage.PAYMENT_NOT_MATCHED, this.id, this.expectedAmount, amount);
        }

        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.PAID);
        this.actualPaidAmount = amount;

        // 付款完成后，自动启动进度跟踪
        this.report.startup();
    }

    private void assertChangeProgressNode() {
        if (this.state != DesignerOrderState.PAID) {
            DomainException.throwDomainException(DomainExceptionMessage.PROGRESS_UPDATE_FAILED_FOR_ERROR_STATE_CODE, DomainExceptionMessage.PROGRESS_UPDATE_FAILED_FOR_ERROR_STATE, this.id, this.state);
        }
    }

    public void requestCompletionForProgressNode(DesigningProgressNodeType nodeType, String achievement) {
        this.assertChangeProgressNode();

        DesigningProgressNode node = this.report.getNode(nodeType);
        node.requestCompletion(achievement);
    }

    public void confirmCompletionForProgressNode(DesigningProgressNodeType nodeType) {
        this.assertChangeProgressNode();

        DesigningProgressNode node = this.report.getNode(nodeType);
        node.confirmCompletion();
    }

    public RefundOrder refund(String cause) {
        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.REFUND);

        DesigningProgressNode constructionDrawingDesignNode = this.report.getNode(DesigningProgressNodeType.CONSTRUCTION_DRAWING_DESIGN);
        if (constructionDrawingDesignNode.getState() == DesigningProgressNodeState.REQUEST_COMPLETION ||
                constructionDrawingDesignNode.getState() == DesigningProgressNodeState.CONFIRM_COMPLETION) {
            DomainException.throwDomainException(DomainExceptionMessage.FAILED_TO_REFUND_FOR_PROGRESS_CODE, DomainExceptionMessage.FAILED_TO_REFUND_FOR_PROGRESS, this.id);
        }

        return RefundOrderFactory.newRefundOrder(this, cause);
    }

    public void complete() {
        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.COMPLETION);
    }

    public void feedback(int star, String description) {
        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.FEEDBACK);
        this.feedbackStar = star;
        this.feedbackDescription = description;
    }

    @Override
    public boolean sameIdentityAs(DesignerOrder other) {
        return this.equals(other);
    }
}
