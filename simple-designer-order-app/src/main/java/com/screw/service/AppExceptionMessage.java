package com.screw.service;

public class AppExceptionMessage {
    public static int APP_EXCEPTION_CODE = 20000;

    public static int CUSTOMER_NOT_EXIST_CODE = APP_EXCEPTION_CODE++;
    public static final String CUSTOMER_NOT_EXIST = "不存的用户Id{0}。";

    public static int DESIGNER_NOT_EXIST_CODE = APP_EXCEPTION_CODE++;
    public static final String DESIGNER_NOT_EXIST = "不存的设计师Id{0}。";

    public static int DESIGNER_ORDER_NOT_EXIST_CODE = APP_EXCEPTION_CODE++;
    public static final String DESIGNER_ORDER_NOT_EXIST = "不存的设计师订单Id{0}。";

    public static int REFUND_ORDER_NOT_EXIST_CODE = APP_EXCEPTION_CODE++;
    public static final String REFUND_ORDER_NOT_EXIST = "不存的退款单Id{0}。";
}
