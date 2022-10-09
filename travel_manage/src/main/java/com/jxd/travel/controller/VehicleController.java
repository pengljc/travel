package com.jxd.travel.controller;

import com.jxd.travel.model.Emp;
import com.jxd.travel.model.Vehicle;
import com.jxd.travel.service.IVehicleService;
import com.jxd.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public JsonResult<List<Vehicle>> getAllVehicle() {
        List<Vehicle> result = vehicleService.getAllVehicle();
        return new JsonResult<List<Vehicle>>(OK,result);
    }

    @GetMapping("/all")
    public JsonResult<List<Vehicle>> getAllWithPage(@RequestParam(required = false) Map<String,Object> map) {
        List<Vehicle> result = vehicleService.getAllByPage(map);
        Integer total = vehicleService.getNums();
        return new JsonResult<List<Vehicle>>(OK,result,total);
    }

    @PostMapping
    public JsonResult<Void> saveVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.save(vehicle);
        return new JsonResult<Void>(OK);
    }

    @GetMapping("/{vno}")
    public JsonResult<Vehicle> getByVno(@PathVariable("vno") Integer vno) {
        Vehicle result = vehicleService.getByVno(vno);
        return new JsonResult<Vehicle>(OK,result);
    }

    @PutMapping
    public JsonResult<Void> editVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.edit(vehicle);
        return new JsonResult<Void>(OK);
    }

    @DeleteMapping
    public JsonResult<Void> deleteBatch(@RequestBody Integer[] vnos) {
        vehicleService.deleteBatch(vnos);
        return new JsonResult<Void>(OK);
    }
}
