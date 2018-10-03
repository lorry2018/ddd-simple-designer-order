package com.screw.domain.order;

public enum  DesignerOrderState {
    NEW(0, "新建"),
    MEASURED(1, "已量房"),
    QUOTED(2, "已报价"),
    ACCEPT_QUOTE(3, "接受报价"),
    REJECT_QUOTE(4, "拒绝报价"),
    PAID(5, "已付款"),
    ABORTED(6, "已终止"),
    REQUEST_COMPLETION(7, "请求完成"),
    CONFIRM_COMPLETION(8, "确认完成"),
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
