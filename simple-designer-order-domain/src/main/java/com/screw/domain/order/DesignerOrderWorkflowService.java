package com.screw.domain.order;

import com.screw.domain.DomainException;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 设计师订单处理流程。
 *
 * Note: this can be replaced by the state machine.
 */
public class DesignerOrderWorkflowService {
    private DesignerOrderWorkflowService() { }

    private static Map<DesignerOrderState, DesignerOrderState[]> states = new HashMap<>();
    private static Map<DesignerOrderState, String[]> actions = new HashMap<>();
    static {
        states.put(DesignerOrderState.NEW, new DesignerOrderState[]{ DesignerOrderState.MEASURED, DesignerOrderState.ABORTED });
        states.put(DesignerOrderState.MEASURED, new DesignerOrderState[]{ DesignerOrderState.QUOTED, DesignerOrderState.ABORTED });
        states.put(DesignerOrderState.QUOTED, new DesignerOrderState[]{ DesignerOrderState.ACCEPT_QUOTE, DesignerOrderState.REJECT_QUOTE, DesignerOrderState.ABORTED });
        states.put(DesignerOrderState.REJECT_QUOTE, new DesignerOrderState[]{ DesignerOrderState.MEASURED, DesignerOrderState.ABORTED });
        states.put(DesignerOrderState.ACCEPT_QUOTE, new DesignerOrderState[]{ DesignerOrderState.PAID, DesignerOrderState.ABORTED });
        states.put(DesignerOrderState.PAID, new DesignerOrderState[]{ DesignerOrderState.REQUEST_COMPLETION });
        states.put(DesignerOrderState.REQUEST_COMPLETION, new DesignerOrderState[]{ DesignerOrderState.CONFIRM_COMPLETION });
        states.put(DesignerOrderState.CONFIRM_COMPLETION, new DesignerOrderState[]{ DesignerOrderState.FEEDBACK });
        states.put(DesignerOrderState.FEEDBACK, new DesignerOrderState[]{ DesignerOrderState.FEEDBACK }); // 允许多次评价

        actions.put(DesignerOrderState.NEW, new String[]{ "量房", "终止" });
        actions.put(DesignerOrderState.MEASURED, new String[]{ "报价", "终止" });
        actions.put(DesignerOrderState.QUOTED, new String[]{ "接受报价", "拒绝报价", "终止" });
        actions.put(DesignerOrderState.REJECT_QUOTE, new String[]{ "量房", "终止" });
        actions.put(DesignerOrderState.ACCEPT_QUOTE, new String[]{ "付款", "终止" });
        actions.put(DesignerOrderState.PAID, new String[]{ "请求完成" });
        actions.put(DesignerOrderState.REQUEST_COMPLETION, new String[]{ "确定完成" });
        actions.put(DesignerOrderState.CONFIRM_COMPLETION, new String[]{ "评价" });
        actions.put(DesignerOrderState.FEEDBACK, new String[]{ "评价" });
    }

    public static boolean canChangeState(DesignerOrderState state, DesignerOrderState nextState) {
        Assert.notNull(state, "The state can not be null.");
        Assert.notNull(nextState, "The nextState can not be null.");

        DesignerOrderState[] nextStates = states.get(state);
        for (DesignerOrderState possibleNextState : nextStates) {
            if (possibleNextState.equals(nextState)) {
                return true;
            }
        }

        return false;
    }

    public static String[] getNextActions(DesignerOrderState state) {
        Assert.notNull(state, "The state can not be null.");

        return actions.get(state);
    }

    public static DesignerOrderState changeState(long orderId, DesignerOrderState state, DesignerOrderState nextState) {
        if (!canChangeState(state, nextState)) {
            throw new DomainException("Can not change the order " + orderId + " from " + state.toString() + " to " + nextState.toString());
        }

        return nextState;
    }

    private static boolean isInDesigning(DesignerOrder order) {
        if (order.getState() != DesignerOrderState.ABORTED &&
                order.getState() != DesignerOrderState.PAID &&
                order.getState() != DesignerOrderState.CONFIRM_COMPLETION &&
                order.getState() != DesignerOrderState.FEEDBACK) {
            return true;
        }

        return false;
    }

    public static boolean canAbort(DesignerOrder order) {
        return !isInDesigning(order) || order.getState() == DesignerOrderState.ABORTED;
    }

    public static boolean isAborted(DesignerOrder order) {
        return order.getState() == DesignerOrderState.ABORTED;
    }

    public static boolean isCompleted(DesignerOrder order) {
        return order.getState() == DesignerOrderState.CONFIRM_COMPLETION ||
                order.getState() == DesignerOrderState.FEEDBACK;
    }
}

