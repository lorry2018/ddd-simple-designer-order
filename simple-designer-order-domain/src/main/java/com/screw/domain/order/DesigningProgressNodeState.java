package com.screw.domain.order;

public enum DesigningProgressNodeState {
    NOT_STARTED(0, "未开始"),
    STARTED(1, "执行中"),
    REQUEST_COMPLETION(2, "请求确认完成"),
    CONFIRM_COMPLETION(3, "确认完成");

    private int code;
    private String name;

    public static String getName(int code) {
        for (DesigningProgressNodeState state : DesigningProgressNodeState.values()) {
            if (state.getCode() == code) {
                return state.name;
            }
        }
        return null;
    }

    private DesigningProgressNodeState(int code, String name) {
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
