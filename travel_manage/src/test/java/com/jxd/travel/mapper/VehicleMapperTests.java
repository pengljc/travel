package com.jxd.travel.mapper;

import com.jxd.travel.model.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName Vehicle
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/28
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleMapperTests {
    @Autowired
    private  VehicleMapper vehicleMapper;

    @Test
    public void selectAllVehicle() {
        List<Vehicle> allVehicle = vehicleMapper.getAllVehicle();
        System.out.println(allVehicle);
    }
}
