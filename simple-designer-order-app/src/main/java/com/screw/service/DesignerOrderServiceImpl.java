package com.screw.service;

import com.screw.DesignerOrderService;
import com.screw.domain.order.*;
import com.screw.domain.refund.RefundOrder;
import com.screw.domain.refund.RefundOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class DesignerOrderServiceImpl implements DesignerOrderService {
    @Autowired
    private DesignerOrderRepository designerOrderRepository;
    @Autowired
    private RefundOrderRepository refundOrderRepository;

    @Override
    public DesignerOrder selectByKey(int orderId) {
        return designerOrderRepository.selectByKey(orderId);
    }

    @Override
    public DesignerOrder createOrder(int customerId, int designerId) {
        DesignerOrder order = DesignerOrderFactory.createOrder(customerId, designerId);
        designerOrderRepository.create(order);
        return designerOrderRepository.selectByKey(order.getId());
    }

    @Override
    public void measure(int orderId, float area) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        order.measure(area);
        designerOrderRepository.update(order);
    }

    @Override
    public void quote(int orderId, float expectedAmount, int[] estimatedDaysList) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        order.quote(expectedAmount, estimatedDaysList);
        designerOrderRepository.update(order);
    }

    @Override
    public void acceptQuote(int orderId) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        order.acceptQuote();
        designerOrderRepository.update(order);
    }

    @Override
    public void rejectQuote(int orderId) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        order.rejectQuote();
        designerOrderRepository.update(order);
    }

    @Override
    public void pay(int orderId, float amount) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        order.pay(amount);
        designerOrderRepository.update(order);
    }

    @Override
    public void abort(int orderId, String cause) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        order.abort(cause);

        designerOrderRepository.update(order);
    }

    @Override
    public void requestCompletionForProgressNode(int orderId, DesigningProgressNodeType nodeType, String achievement) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        order.requestCompletionForProgressNode(nodeType, achievement);

        designerOrderRepository.update(order);
    }

    @Override
    public void confirmCompletionForProgressNode(int orderId, DesigningProgressNodeType nodeType) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        order.confirmCompletionForProgressNode(nodeType);

        designerOrderRepository.update(order);
    }

    @Override
    public void refund(int orderId, String cause) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        RefundOrder refundOrder = order.refund(cause);

        designerOrderRepository.update(order);
        refundOrderRepository.create(refundOrder);
    }

    @Override
    public void feedback(int orderId, int star, String description) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        order.feedback(star, description);

        designerOrderRepository.update(order);
    }
}
