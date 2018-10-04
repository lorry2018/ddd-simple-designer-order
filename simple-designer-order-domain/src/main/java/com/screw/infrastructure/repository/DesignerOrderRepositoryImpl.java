package com.screw.infrastructure.repository;

import com.screw.domain.order.DesignerOrder;
import com.screw.domain.order.DesignerOrderRepository;
import com.screw.infrastructure.mapper.DesignerOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DesignerOrderRepositoryImpl implements DesignerOrderRepository {
    private static final String DESIGNER_ORDER_TABLE = "designer_order";

    @Autowired
    private DesignerOrderMapper designerOrderMapper;

    @Override
    public void create(DesignerOrder order) {
        if (designerOrderMapper.create(order) == 0) {
            TableException.throwTableException(DESIGNER_ORDER_TABLE, TableOperation.CREATE);
        }
    }

    @Override
    public DesignerOrder selectByKey(int id) {
        return designerOrderMapper.selectByKey(id);
    }

    @Override
    public DesignerOrder selectOneBySpecification(DesignerOrder example) {
        return designerOrderMapper.selectOneBySpecification(example);
    }

    @Override
    public List<DesignerOrder> selectAll() {
        return designerOrderMapper.selectAll();
    }

    @Override
    public List<DesignerOrder> selectBySpecification(DesignerOrder example) {
        return designerOrderMapper.selectBySpecification(example);
    }

    @Override
    public void update(DesignerOrder order) {
        if (designerOrderMapper.update(order) == 0) {
            TableException.throwTableException(DESIGNER_ORDER_TABLE, TableOperation.UPDATE);
        }
    }

    @Override
    public void delete(DesignerOrder order) {
        if (designerOrderMapper.delete(order) == 0) {
            TableException.throwTableException(DESIGNER_ORDER_TABLE, TableOperation.DELETE);
        }
    }
}
