package com.screw.infrastructure.repository;

import com.screw.domain.refund.RefundOrder;
import com.screw.domain.refund.RefundOrderRepository;
import com.screw.infrastructure.mapper.RefundOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RefundOrderRepositoryImpl implements RefundOrderRepository {
    private static final String REFUND_ORDER_TABLE = "refund_order";
    @Autowired
    private RefundOrderMapper refundOrderMapper;

    @Override
    public void create(RefundOrder refundOrder) {
        if (refundOrderMapper.create(refundOrder) == 0) {
            TableException.throwTableException(REFUND_ORDER_TABLE, TableOperation.CREATE);
        }
    }

    @Override
    public RefundOrder selectByKey(int id) {
        return refundOrderMapper.selectByKey(id);
    }

    @Override
    public RefundOrder selectOneBySpecification(RefundOrder example) {
        return refundOrderMapper.selectOneBySpecification(example);
    }

    @Override
    public List<RefundOrder> selectAll() {
        return refundOrderMapper.selectAll();
    }

    @Override
    public List<RefundOrder> selectBySpecification(RefundOrder example) {
        return refundOrderMapper.selectBySpecification(example);
    }

    @Override
    public void update(RefundOrder refundOrder) {
        if (refundOrderMapper.update(refundOrder) == 0) {
            TableException.throwTableException(REFUND_ORDER_TABLE, TableOperation.UPDATE);
        }
    }

    @Override
    public void delete(RefundOrder refundOrder) {
        if (refundOrderMapper.delete(refundOrder) == 0) {
            TableException.throwTableException(REFUND_ORDER_TABLE, TableOperation.DELETE);
        }
    }
}
