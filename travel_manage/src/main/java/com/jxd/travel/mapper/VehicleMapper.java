package com.jxd.travel.mapper;

import com.jxd.travel.model.Vehicle;

import java.util.List;
import java.util.Map;

public interface VehicleMapper {
    /**
     * 查询所有交通工具
     *
     * @return {@link List}<{@link Vehicle}> 交通工具数据列表
     */
    List<Vehicle> getAllVehicle();

    /**
     * 查询所有交通工具
     * 分页
     *
     * @param map 分页
     * @return {@link List}<{@link Vehicle}>
     */
    List<Vehicle> getAllWithPage(Map<String,Object> map);

    /**
     * 获得交通工具数量
     *
     * @return {@link Integer} 数量
     */
    Integer getNums();

    /**
     * 插入交通工具信息
     *
     * @param vehicle 车辆
     * @return {@link Integer}
     */
    Integer insert(Vehicle vehicle);

    /**
     * 由vno查询出Vehicle信息
     *
     * @param vno vno
     * @return {@link Vehicle}
     */
    Vehicle selectByVno(Integer vno);

    /**
     * 更新交通工具
     *
     * @param vehicle 交通工具
     * @return {@link Integer} 改变的行数
     */
    Integer update(Vehicle vehicle);

    /**
     * 删除批处理交通工具
     *
     * @param vnos vno
     * @return {@link Integer} 改变的行数
     */
    Integer deleteBatch(Integer[] vnos);
}
