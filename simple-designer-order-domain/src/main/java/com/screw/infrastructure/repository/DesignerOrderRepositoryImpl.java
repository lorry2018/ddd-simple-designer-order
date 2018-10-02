package com.screw.infrastructure.repository;

import com.screw.domain.order.DesignerOrder;
import com.screw.domain.order.DesignerOrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DesignerOrderRepositoryImpl implements DesignerOrderRepository {
    @Override
    public void create(DesignerOrder order) {

    }

    @Override
    public DesignerOrder selectByKey(int id) {
        return null;
    }

    @Override
    public DesignerOrder selectOne(DesignerOrder example) {
        return null;
    }

    @Override
    public List<DesignerOrder> selectAll() {
        return null;
    }

    @Override
    public List<DesignerOrder> selectBySpecification(DesignerOrder example) {
        return null;
    }

    @Override
    public void update(DesignerOrder customer) {

    }

    @Override
    public void delete(DesignerOrder customer) {

    }
}
