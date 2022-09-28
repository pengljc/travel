package com.jxd.travel.controller;

import com.jxd.travel.model.Vehicle;
import com.jxd.travel.service.IVehicleService;
import com.jxd.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName VehicleController
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/28
 * @Version 1.0
 */
@RestController
@RequestMapping("/vehicles")
public class VehicleController extends BaseController {
    @Autowired
    private IVehicleService vehicleService;

    @GetMapping
    JsonResult<List<Vehicle>> getAllVehicle() {
        List<Vehicle> result = vehicleService.getAllVehicle();
        return new JsonResult<List<Vehicle>>(OK,result);
    }
}
