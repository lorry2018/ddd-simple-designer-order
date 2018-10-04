package com.screw.domain;

public class DomainExceptionMessage {
    public static int DOMAIN_EXCEPTION_CODE = 10000;

    public static int STATE_CHANGE_ILLEGAL_CODE = DOMAIN_EXCEPTION_CODE ++;
    public static final String STATE_CHANGE_ILLEGAL = "订单{0}状态变更失败，无法从状态{1}变更到状态状态{2}。";

    public static int PAYMENT_NOT_MATCHED_CODE = DOMAIN_EXCEPTION_CODE ++;
    public static final String PAYMENT_NOT_MATCHED = "订单{0}支付失败：待支付金额{1}，实际支付金额{2}，二者不匹配。";

    public static int PAYMENT_NOT_IN_READY_STATE_CODE = DOMAIN_EXCEPTION_CODE ++;
    public static final String PAYMENT_NOT_IN_READY_STATE = "订单{0}支付失败：当前订单状态是{1}，当前状态不允许支付。";
}