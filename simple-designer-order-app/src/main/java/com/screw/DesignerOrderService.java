package com.screw;

import com.screw.domain.order.DesignerOrder;
import com.screw.domain.order.DesigningProgressNodeType;

public interface DesignerOrderService {
    DesignerOrder selectByKey(int orderId);
    DesignerOrder createOrder(int customerId, int designerId);
    void measure(int orderId, float area);
    void quote(int orderId, float expectedAmount, int[] estimatedDaysList);
    void acceptQuote(int orderId);
    void rejectQuote(int orderId);
    void pay(int orderId, float amount);
    void abort(int orderId, String cause);
    void requestCompletionForProgressNode(int orderId, DesigningProgressNodeType nodeType, String achievement);
    void confirmCompletionForProgressNode(int orderId, DesigningProgressNodeType nodeType);
    void refund(int orderId, String cause);
    void feedback(int orderId, int star, String description);
}