package com.screw.domain.refund;

import com.screw.domain.order.DesignerOrder;
import com.screw.domain.order.DesigningProgressNode;
import com.screw.domain.order.DesigningProgressNodeState;
import com.screw.domain.order.DesigningProgressReport;

import java.util.List;

public class RefundOrderFactory {
    private RefundOrderFactory() {}

    public static RefundOrder newRefundOrder(DesignerOrder order, String cause) {
        RefundOrder refundOrder = new RefundOrder();
        refundOrder.setDesignerOrderId(order.getId());
        refundOrder.setCause(cause);
        refundOrder.setRefundAmount(computeRefundAmount(order));

        return refundOrder;
    }

    public static float computeRefundAmount(DesignerOrder order) {
        DesigningProgressReport report = order.getReport();
        List<DesigningProgressNode> nodes = report.getNodes();

        // 平面图未完成
        if (nodes.get(0).getState() != DesigningProgressNodeState.REQUEST_COMPLETION &&
                nodes.get(0).getState() != DesigningProgressNodeState.CONFIRM_COMPLETION) {
            return order.getActualPaidAmount();
        }

        // 平面图完成，效果图未完成
        if (nodes.get(1).getState() != DesigningProgressNodeState.REQUEST_COMPLETION &&
                nodes.get(1).getState() != DesigningProgressNodeState.CONFIRM_COMPLETION) {
            return (float)(order.getActualPaidAmount() * 0.9);
        }

        // 效果图完成，施工图未完成
        if (nodes.get(2).getState() != DesigningProgressNodeState.REQUEST_COMPLETION &&
                nodes.get(2).getState() != DesigningProgressNodeState.CONFIRM_COMPLETION) {
            return (float)(order.getActualPaidAmount() * 0.5);
        }

        // 施工图完成之后，不能付款，因此返回0
        return 0;
    }


}
