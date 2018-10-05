package com.screw.domain.refund;

import com.screw.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(of = {"id"})
public class RefundOrder implements Entity<RefundOrder> {
    private int id;
    private int designerOrderId;
    private String cause;
    private float refundAmount;
    private boolean completed;
    private Date createdTime;
    private Date updatedTime;

    public void complete() {
        completed = true;
    }

    @Override
    public boolean sameIdentityAs(RefundOrder other) {
        return this.equals(other);
    }
}
