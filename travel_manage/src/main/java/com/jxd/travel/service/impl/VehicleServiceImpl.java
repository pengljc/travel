package com.jxd.travel.service.impl;

import com.jxd.travel.mapper.VehicleMapper;
import com.jxd.travel.model.Vehicle;
import com.jxd.travel.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName VehicleServiceImpl
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/28
 * @Version 1.0
 */
@Service
public class VehicleServiceImpl implements IVehicleService {
    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public List<Vehicle> getAllVehicle() {
        return vehicleMapper.getAllVehicle();
    }
}
