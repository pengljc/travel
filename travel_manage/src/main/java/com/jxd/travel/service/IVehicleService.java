package com.jxd.travel.service;

import com.jxd.travel.model.Vehicle;

import java.util.List;
import java.util.Map;

public interface IVehicleService {
    /**
     * 得到所有交通方式
     *
     * @return {@link List}<{@link Vehicle}> 交通方式数据列表
     */
    List<Vehicle> getAllVehicle();

    /**
     * 得到所有交通方式
     *
     * @return {@link List}<{@link Vehicle}> 交通方式数据列表
     */
    List<Vehicle> getAllByPage(Map<String,Object> map);

    /**
     * 得到交通方式的数量
     *
     * @return {@link Integer} 数量
     */
    Integer getNums();

    /**
     * 新增交通工具
     */
    void save(Vehicle vehicle);

    /**
     * 由vno查询出Vehicle信息
     *
     * @param vno vno
     * @return {@link Vehicle}
     */
    Vehicle getByVno(Integer vno);

    /**
     * 更新交通工具
     *
     * @param vehicle 交通工具
     * @return {@link Integer} 改变的行数
     */
    void  edit(Vehicle vehicle);

    /**
     * 删除批处理交通工具
     *
     * @param vnos vno
     */
    void deleteBatch(Integer[] vnos);
}
