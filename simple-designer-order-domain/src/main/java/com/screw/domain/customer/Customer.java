package com.screw.domain.customer;

import com.screw.domain.Entity;
import com.screw.domain.order.DesignerOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(of = {"id"})
public class Customer implements Entity<Customer> {
    private int id;
    private String name;
    private String phone;
    private Address address;
    private boolean enabled;

    private List<DesignerOrder> designerOrders;

    public boolean canCreateDesignerOrder() {
        return new DesignerOrderCreationSpecification().isSatisfiedBy(this);
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    @Override
    public boolean sameIdentityAs(Customer other) {
        return this.equals(other);
    }
}
