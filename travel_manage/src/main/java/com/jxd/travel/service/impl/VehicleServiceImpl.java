package com.jxd.travel.service.impl;

import com.jxd.travel.mapper.VehicleMapper;
import com.jxd.travel.model.Vehicle;
import com.jxd.travel.service.IVehicleService;
import com.jxd.travel.service.ex.DeleteException;
import com.jxd.travel.service.ex.InsertException;
import com.jxd.travel.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Vehicle> getAllByPage(Map<String, Object> map) {
        //取出page和limit
        Integer page = Integer.parseInt((String)map.get("page"));
        Integer limit = Integer.parseInt((String)map.get("limit"));
        //对page进行运算
        page = (page - 1) * limit;
        //把新数据存入map
        map.put("page",page);
        map.put("limit",limit);

        return vehicleMapper.getAllWithPage(map);
    }

    @Override
    public Integer getNums() {
        return vehicleMapper.getNums();
    }

    @Override
    public void save(Vehicle vehicle) {
        Integer rows = vehicleMapper.insert(vehicle);
        if (rows != 1) {
            throw new InsertException("在交通工具插入数据库过程中产生了未知的异常");
        }
    }

    @Override
    public Vehicle getByVno(Integer vno) {
        return vehicleMapper.selectByVno(vno);
    }

    @Override
    public void edit(Vehicle vehicle) {
        Integer rows = vehicleMapper.update(vehicle);
        //改变行数不为一行时，抛出异常
        if (rows != 1) {
            throw new UpdateException("数据库中的交通工具信息，修改的过程中产生了未知的异常");
        }
    }

    @Override
    public void deleteBatch(Integer[] vnos) {
        Integer rows = vehicleMapper.deleteBatch(vnos);
        if (rows != vnos.length) {
            throw new DeleteException("数据库中的交通工具信息，批量删除的过程中产生了未知的异常");
        }
    }
}
