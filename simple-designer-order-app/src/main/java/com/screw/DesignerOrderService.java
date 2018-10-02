package com.screw;

public interface DesignerOrderService {
    void createOrder(int customerId, int designerId);
    void quote(int orderId, float price, int estimatedDays);
    void acceptPrice(int orderId);
    void rejectPrice(int orderId);
    void pay(float amount);
    boolean abort(int orderId, String cause);
    void complete(int orderId);
    void confirmCompletion(int orderId);
    void feedback(int start, int description);
}