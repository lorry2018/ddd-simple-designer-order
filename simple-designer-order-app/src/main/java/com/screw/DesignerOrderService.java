package com.screw;

public interface DesignerOrderService {
    void createOrder(int customerId, int designerId);
    void quote(int orderId, float expectedAmount, int estimatedDays);
    void acceptPrice(int orderId);
    void rejectPrice(int orderId);
    void pay(int orderId, float amount);
    void abort(int orderId, String cause);
    void requestCompletion(int orderId);
    void confirmCompletion(int orderId);
    void feedback(int orderId, int star, String description);
}