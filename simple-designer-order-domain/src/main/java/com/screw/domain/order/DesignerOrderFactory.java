package com.screw.domain.order;

public class DesignerOrderFactory {
    private DesignerOrderFactory() {}

    public static DesignerOrder createOrder(int customerId, int designerId) {
        DesignerOrder designerOrder = new DesignerOrder();
        designerOrder.setCustomerId(customerId);
        designerOrder.setDesignerId(designerId);
        designerOrder.setState(DesignerOrderState.NEW);

        return designerOrder;
    }
}
