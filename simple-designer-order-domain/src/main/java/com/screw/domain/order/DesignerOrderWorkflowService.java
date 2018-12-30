package com.screw.domain.order;

import com.screw.BusinessException;
import com.screw.domain.DomainExceptionMessage;
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
    static {
        states.put(DesignerOrderState.NEW, new DesignerOrderState[]{ DesignerOrderState.MEASURED, DesignerOrderState.ABORTED });
        states.put(DesignerOrderState.MEASURED, new DesignerOrderState[]{ DesignerOrderState.QUOTED, DesignerOrderState.ABORTED });
        states.put(DesignerOrderState.QUOTED, new DesignerOrderState[]{ DesignerOrderState.ACCEPT_QUOTE, DesignerOrderState.REJECT_QUOTE, DesignerOrderState.ABORTED });
        states.put(DesignerOrderState.REJECT_QUOTE, new DesignerOrderState[]{ DesignerOrderState.QUOTED, DesignerOrderState.ABORTED });
        states.put(DesignerOrderState.ACCEPT_QUOTE, new DesignerOrderState[]{ DesignerOrderState.PAID, DesignerOrderState.ABORTED });
        states.put(DesignerOrderState.PAID, new DesignerOrderState[]{ DesignerOrderState.REFUND, DesignerOrderState.COMPLETION });
        states.put(DesignerOrderState.COMPLETION, new DesignerOrderState[]{ DesignerOrderState.FEEDBACK });

        states.put(DesignerOrderState.ABORTED, new DesignerOrderState[]{ DesignerOrderState.FEEDBACK });
        states.put(DesignerOrderState.REFUND, new DesignerOrderState[]{ DesignerOrderState.FEEDBACK });
        states.put(DesignerOrderState.FEEDBACK, new DesignerOrderState[]{ DesignerOrderState.FEEDBACK }); // 允许多次评价
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

    public static boolean canAbort(DesignerOrder order) {
        return canChangeState(order.getState(), DesignerOrderState.ABORTED);
    }

    public static DesignerOrderState changeState(long orderId, DesignerOrderState state, DesignerOrderState nextState) {
        if (!canChangeState(state, nextState)) {
            BusinessException.throwException(DomainExceptionMessage.STATE_CHANGE_ILLEGAL_CODE, DomainExceptionMessage.STATE_CHANGE_ILLEGAL, orderId, state, nextState);
        }

        return nextState;
    }

    public static boolean isCompleted(DesignerOrder order) {
        return order.getState() == DesignerOrderState.ABORTED ||
                order.getState() == DesignerOrderState.REFUND ||
                order.getState() == DesignerOrderState.COMPLETION ||
                order.getState() == DesignerOrderState.FEEDBACK;
    }
}

