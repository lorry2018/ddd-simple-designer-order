package com.screw.infrastructure.repository;

import com.screw.domain.designer.Designer;
import com.screw.domain.designer.DesignerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DesignerRepositoryImpl implements DesignerRepository {
    @Override
    public void create(Designer designer) {

    }

    @Override
    public Designer selectByKey(int id) {
        return null;
    }

    @Override
    public Designer selectOne(Designer example) {
        return null;
    }

    @Override
    public List<Designer> selectAll() {
        return null;
    }

    @Override
    public List<Designer> selectBySpecification(Designer example) {
        return null;
    }

    @Override
    public void update(Designer customer) {

    }

    @Override
    public void delete(Designer customer) {

    }
}
