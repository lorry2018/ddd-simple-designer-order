package com.screw.domain;

import java.util.List;

public interface Repository<TEntity> {
    void create(TEntity entity);
    TEntity selectByKey(int id);
    TEntity selectOneBySpecification(TEntity example);
    List<TEntity> selectAll();
    List<TEntity> selectBySpecification(TEntity example);
    void update(TEntity customer);
    void delete(TEntity customer);
}
