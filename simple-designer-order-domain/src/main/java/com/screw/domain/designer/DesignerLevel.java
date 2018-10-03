package com.screw.domain.designer;

public enum  DesignerLevel {
    JUNIOR(1, "初级"),
    INTERMEDIATE(2, "中级"),
    SENIOR(3, "高级"),
    STAFF(4, "专家");

    private int code;
    private String name;

    public static String getName(int code) {
        for (DesignerLevel level : DesignerLevel.values()) {
            if (level.getCode() == code) {
                return level.name;
            }
        }
        return null;
    }

    DesignerLevel(int stateCode, String name) {
        this.code = stateCode;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
