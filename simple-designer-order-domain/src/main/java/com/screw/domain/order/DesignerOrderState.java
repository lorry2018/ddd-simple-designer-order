package com.screw.domain.order;

public enum  DesignerOrderState {
    NEW(0, "新建"),
    MEASURED(1, "已量房"),
    QUOTED(2, "已报价"),
    ACCEPT_QUOTE(3, "接受报价"),
    REJECT_QUOTE(4, "拒绝报价"),
    PAID(5, "已付款"),
    REFUND(6, "已退款"),
    ABORTED(7, "已终止"),
    COMPLETION(8, "已完成"), // 当进度的所有节点都确定后，自动变更
    FEEDBACK(9, "已评价");

    private int code;
    private String name;

    public static String getName(int stateCode) {
        for (DesignerOrderState state : DesignerOrderState.values()) {
            if (state.getCode() == stateCode) {
                return state.name;
            }
        }
        return null;
    }

    DesignerOrderState(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
