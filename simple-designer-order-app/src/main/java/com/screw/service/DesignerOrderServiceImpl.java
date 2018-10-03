package com.screw.service;

import com.screw.DesignerOrderService;
import com.screw.domain.order.DesignerOrder;
import com.screw.domain.order.DesignerOrderFactory;
import com.screw.domain.order.DesignerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class DesignerOrderServiceImpl implements DesignerOrderService {
    @Autowired
    private DesignerOrderRepository designerOrderRepository;

    @Override
    public DesignerOrder createOrder(int customerId, int designerId) {
        DesignerOrder order = DesignerOrderFactory.createOrder(customerId, designerId);
        designerOrderRepository.create(order);
        return designerOrderRepository.selectByKey(order.getId());
    }

    @Override
    public void quote(int orderId, float expectedAmount, int estimatedDays) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        order.quote(expectedAmount, estimatedDays);
        designerOrderRepository.update(order);
    }

    @Override
    public void acceptPrice(int orderId) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        order.acceptPrice();
        designerOrderRepository.update(order);
    }

    @Override
    public void rejectPrice(int orderId) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        order.rejectPrice();
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
    public void requestCompletion(int orderId) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        order.requestCompletion();

        designerOrderRepository.update(order);
    }

    @Override
    public void confirmCompletion(int orderId) {
        DesignerOrder order = designerOrderRepository.selectByKey(orderId);
        if (order == null) {
            return;
        }

        order.confirmCompletion();

        designerOrderRepository.update(order);
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
