package com.screw.infrastructure.mapper;

import java.util.List;

public interface BaseMapper<TEntity> {
    int create(TEntity entity);
    TEntity selectByKey(int id);
    TEntity selectOneBySpecification(TEntity example);
    List<TEntity> selectAll();
    List<TEntity> selectBySpecification(TEntity example);
    int update(TEntity entity);
    int delete(TEntity entity);
}
