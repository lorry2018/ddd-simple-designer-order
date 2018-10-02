package com.screw.domain.customer;

import com.screw.domain.order.DesignerOrder;
import com.screw.domain.order.DesignerOrderWorkflowService;

import java.util.List;

public class DesignerOrderCreationSpecification {
    public boolean isSatisfiedBy(Customer customer) {

        List<DesignerOrder> orders = customer.getDesignerOrders();
        if (null == orders || orders.isEmpty()) {
            return true;
        }

        for (DesignerOrder order : orders) {
            if (DesignerOrderWorkflowService.canAbort(order)) {
                return false;
            }
        }

        return true;
    }
}
