package com.screw.domain;

public class DomainExceptionMessage {
    private DomainExceptionMessage() {}

    public static int DOMAIN_EXCEPTION_CODE = 10000;

    public static int FAILED_TO_GET_PROGRESS_NODE_CODE = DOMAIN_EXCEPTION_CODE ++;
    public static final String FAILED_TO_GET_PROGRESS_NODE = "获取进度节点{0}失败。";

    public static int STATE_CHANGE_ILLEGAL_CODE = DOMAIN_EXCEPTION_CODE ++;
    public static final String STATE_CHANGE_ILLEGAL = "订单{0}状态变更失败，无法从状态{1}变更到状态状态{2}。";

    public static int PAYMENT_NOT_MATCHED_CODE = DOMAIN_EXCEPTION_CODE ++;
    public static final String PAYMENT_NOT_MATCHED = "订单{0}支付失败：待支付金额{1}，实际支付金额{2}，二者不匹配。";

    public static int PAYMENT_NOT_IN_READY_STATE_CODE = DOMAIN_EXCEPTION_CODE ++;
    public static final String PAYMENT_NOT_IN_READY_STATE = "订单{0}支付失败：当前订单状态是{1}，当前状态不允许支付。";

    public static int PROGRESS_UPDATE_FAILED_FOR_ERROR_STATE_CODE = DOMAIN_EXCEPTION_CODE ++;
    public static final String PROGRESS_UPDATE_FAILED_FOR_ERROR_STATE = "订单{0}的状态不是处于已付款，当前状态为{1}，不允许更新订单进度。";

    public static int UPDATE_PROGRESS_ERROR_CODE = DOMAIN_EXCEPTION_CODE ++;
    public static final String UPDATE_PROGRESS_ERROR = "订单{0}的节点{1}状态变更失败，无法从状态{2}变更到状态状态{3}。";

    public static int FAILED_TO_REFUND_FOR_PROGRESS_CODE = DOMAIN_EXCEPTION_CODE ++;
    public static final String FAILED_TO_REFUND_FOR_PROGRESS = "订单{0}退款失败，当前订单进度已经超过了设计师完成施工图设计。";
}