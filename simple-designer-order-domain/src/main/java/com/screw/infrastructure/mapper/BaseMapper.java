package com.screw.infrastructure.mapper;

import java.util.List;

public interface BaseMapper<TEntity> {
    void create(TEntity entity);
    TEntity selectByKey(int id);
    TEntity selectOne(TEntity example);
    List<TEntity> selectAll();
    List<TEntity> selectBySpecification(TEntity example);
    void update(TEntity customer);
    void delete(TEntity customer);
}
