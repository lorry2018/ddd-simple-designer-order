package com.screw.infrastructure.repository;

import com.screw.domain.designer.Designer;
import com.screw.domain.designer.DesignerRepository;
import com.screw.infrastructure.mapper.DesignerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DesignerRepositoryImpl implements DesignerRepository {
    private final static String DESIGNER_TABLE = "designer";

    @Autowired
    private DesignerMapper designerMapper;

    @Override
    public void create(Designer designer) {
        if (designerMapper.create(designer) == 0) {
            TableException.throwTableException(DESIGNER_TABLE, TableOperation.CREATE);
        }
    }

    @Override
    public Designer selectByKey(int id) {
        return designerMapper.selectByKey(id);
    }

    @Override
    public Designer selectOneBySpecification(Designer example) {
        return designerMapper.selectOneBySpecification(example);
    }

    @Override
    public List<Designer> selectAll() {
        return designerMapper.selectAll();
    }

    @Override
    public List<Designer> selectBySpecification(Designer example) {
        return designerMapper.selectBySpecification(example);
    }

    @Override
    public void update(Designer designer) {
        if (designerMapper.update(designer) == 0) {
            TableException.throwTableException(DESIGNER_TABLE, TableOperation.UPDATE);
        }
    }

    @Override
    public void delete(Designer designer) {
        if (designerMapper.delete(designer) == 0) {
            TableException.throwTableException(DESIGNER_TABLE, TableOperation.DELETE);
        }
    }
}
