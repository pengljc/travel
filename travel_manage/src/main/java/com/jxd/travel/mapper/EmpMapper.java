package com.jxd.travel.mapper;

import com.jxd.travel.model.Emp;

import java.util.List;
import java.util.Map;

public interface EmpMapper {
    /**
     * 通过工号查询员工的数据信息
     *
     * @param eno 工号
     * @return {@link Emp} 员工的数据信息，null代表未查到
     */
    Emp selectByEno(Integer eno);

    /**
     * 查询所有非管理员的员工信息；员工表和职位表关联
     * 可以进行员工姓名的模糊查询
     * 可以进行员工工号的精确查询
     * 可以进行分页
     *
     * @param map 传入参数，工号，姓名，page，limit
     * @return {@link List}<{@link Map}<{@link String},{@link String}>> 员工信息的集合
     */
    List<Map<String,String>> selectWithPost(Map<String,Object> map);

    /**
     * 查询员工数量
     *
     * @return {@link Integer} 数量
     */
    Integer selectNums();

    /**
     * 新增一条员工信息
     *
     * @param emp 员工对象
     * @return {@link Integer} 改变的行数
     */
    Integer insert(Emp emp);

    /**
     * 更新员工信息
     *
     * @param emp 员工对象
     * @return {@link Integer} 改变的行数
     */
    Integer update(Emp emp);
}
