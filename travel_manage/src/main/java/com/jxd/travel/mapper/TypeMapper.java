package com.jxd.travel.mapper;

import com.jxd.travel.model.Type;

import java.util.List;

/**
 * @ClassName TypeMapper
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/10/10
 * @Version 1.0
 */
public interface TypeMapper {
    /**
     * 查询所有出差类型
     *
     * @return {@link List}<{@link Type}>
     */
    List<Type> selectAll();
}
