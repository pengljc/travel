package com.jxd.travel.mapper;

import com.jxd.travel.model.Vehicle;

import java.util.List;

public interface VehicleMapper {
    /**
     * 查询所有交通工具
     *
     * @return {@link List}<{@link Vehicle}> 交通工具数据列表
     */
    List<Vehicle> getAllVehicle();
}
