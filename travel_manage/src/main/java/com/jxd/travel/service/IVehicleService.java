package com.jxd.travel.service;

import com.jxd.travel.model.Vehicle;

import java.util.List;

public interface IVehicleService {
    /**
     * 得到所有交通方式
     *
     * @return {@link List}<{@link Vehicle}> 交通方式数据列表
     */
    List<Vehicle> getAllVehicle();
}
