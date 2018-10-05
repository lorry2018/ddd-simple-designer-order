package com.screw.domain.order;

public enum DesigningProgressNodeType {
    FLOORPLAN_DESIGN(0, "平面图设计"),
    SKETCH_DESIGN(1, "效果图设计"),
    CONSTRUCTION_DRAWING_DESIGN(2, "施工图设计"),
    DISCLOSURE(3, "交底");

    private int code;
    private String name;

    public static String getName(int code) {
        for (DesigningProgressNodeType nodeType : DesigningProgressNodeType.values()) {
            if (nodeType.getCode() == code) {
                return nodeType.name;
            }
        }
        return null;
    }

    private DesigningProgressNodeType(int code, String name) {
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
