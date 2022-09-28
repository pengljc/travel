package com.jxd.travel.service;

import com.jxd.travel.mapper.VehicleMapper;
import com.jxd.travel.model.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName VehicleServiceTests
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/28
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleServiceTests {
    @Autowired
    private VehicleMapper vehicleMapper;

    @Test
    public void getAllVehicle() {
        System.out.println(vehicleMapper.getAllVehicle());
    }
}
