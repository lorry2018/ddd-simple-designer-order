package com.screw.infrastructure.repository;

import com.screw.domain.refund.RefundOrder;
import com.screw.domain.refund.RefundOrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RefundOrderRepositoryImpl implements RefundOrderRepository {
    @Override
    public void create(RefundOrder refundOrder) {

    }

    @Override
    public RefundOrder selectByKey(int id) {
        return null;
    }

    @Override
    public RefundOrder selectOneBySpecification(RefundOrder example) {
        return null;
    }

    @Override
    public List<RefundOrder> selectAll() {
        return null;
    }

    @Override
    public List<RefundOrder> selectBySpecification(RefundOrder example) {
        return null;
    }

    @Override
    public void update(RefundOrder refundOrder) {

    }

    @Override
    public void delete(RefundOrder refundOrder) {

    }
}
