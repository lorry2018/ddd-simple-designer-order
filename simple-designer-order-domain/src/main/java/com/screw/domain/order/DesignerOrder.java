package com.screw.domain.order;

import com.screw.domain.DomainException;
import com.screw.domain.Entity;
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
    private float expectedAmount;
    private float actualPaidAmount;
    private int estimatedDays;
    private float area;
    private String abortCause;
    private int feedbackStar;
    private String feedbackDescription;
    private Date createdTime;
    private Date updatedTime;

    public void measure(float area) {
        Assert.isTrue(area > 0, "The area must be bigger than 0.");

        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.MEASURED);
        this.area = area;
    }

    public void quote(float amount, int days) {
        Assert.isTrue(amount > 0, "The price must be bigger than 0.");
        Assert.isTrue(days > 0, "The days must be bigger than 0.");

        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.QUOTED);
        this.expectedAmount = amount;
        this.estimatedDays = days;
    }

    public void acceptPrice() {
        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.ACCEPT_QUOTE);
    }

    public void rejectPrice() {
        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.REJECT_QUOTE);
    }

    public void pay(float amount) {
        Assert.isTrue(amount > 0, "The amount must be bigger than 0.");

        if (!DesignerOrderWorkflowService.canChangeState(state, DesignerOrderState.PAID)) {
            throw new DomainException("The order state is " + this.state + " , can not be paid.");
        }

        if (Math.abs(amount - this.expectedAmount) < 0.0001) {
            throw new DomainException("The payment is not the matched. The expected payment is " + this.expectedAmount + " , the actual payment is " + amount + ".");
        }

        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.PAID);
        this.actualPaidAmount = amount;
    }

    public void abort(String cause) {
        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.ABORTED);
        this.abortCause = cause;
    }

    public void requestCompletion() {
        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.REQUEST_COMPLETION);
    }

    public void confirmCompletion() {
        this.state = DesignerOrderWorkflowService.changeState(this.id, state, DesignerOrderState.CONFIRM_COMPLETION);
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
