package com.screw;

import com.screw.domain.order.DesignerOrder;

public interface DesignerOrderService {
    DesignerOrder selectByKey(int orderId);
    DesignerOrder createOrder(int customerId, int designerId);
    void measure(int orderId, float area);
    void quote(int orderId, float expectedAmount, int estimatedDays);
    void acceptPrice(int orderId);
    void rejectPrice(int orderId);
    void pay(int orderId, float amount);
    void abort(int orderId, String cause);
    void requestCompletion(int orderId);
    void confirmCompletion(int orderId);
    void feedback(int orderId, int star, String description);
}