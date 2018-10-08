package com.screw.infrastructure.repository;

import com.screw.domain.order.DesignerOrder;
import com.screw.domain.order.DesignerOrderRepository;
import com.screw.domain.order.DesigningProgressNode;
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
        DesignerOrder order = designerOrderMapper.selectByKey(id);
        buildConnection(order);
        return order;
    }

    @Override
    public DesignerOrder selectOneBySpecification(DesignerOrder example) {
        DesignerOrder designerOrder = designerOrderMapper.selectOneBySpecification(example);
        buildConnection(designerOrder);
        return designerOrder;
    }

    @Override
    public List<DesignerOrder> selectAll() {
        List<DesignerOrder> designerOrders = designerOrderMapper.selectAll();
        buildConnection(designerOrders);
        return designerOrders;
    }

    @Override
    public List<DesignerOrder> selectBySpecification(DesignerOrder example) {
        List<DesignerOrder> designerOrders = designerOrderMapper.selectBySpecification(example);
        buildConnection(designerOrders);
        return designerOrders;
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

    private void buildConnection(List<DesignerOrder> orders) {
        for (DesignerOrder order : orders) {
            this.buildConnection(order);
        }
    }

    private void buildConnection(DesignerOrder order) {
        if (null == order.getProgressReport()) {
            return;
        }
        order.getProgressReport().setOrder(order);
        for (DesigningProgressNode node : order.getProgressReport().getNodes()) {
            node.setReport(order.getProgressReport());
        }
    }
}
