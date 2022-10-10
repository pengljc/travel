package com.jxd.travel.service;

import com.jxd.travel.model.Emp;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IEmpService
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@Transactional
public interface IEmpService {
    /**
     * 通过工号查询员工的数据信息
     *
     * @param eno 工号
     * @return {@link Emp} 员工的数据信息，null代表未查到
     */
    Emp getByEno(Integer eno);

    /**
     * 通过工号查询员工的数据信息，之后登录校验
     *
     * @param emp 包含工号和密码的员工对象信息
     * @return {@link Emp} 查询出的员工数据信息
     */
    Emp getByEnoLogin(Emp emp);

    /**
     * 查询所有非管理员的员工信息；员工表和职位表关联
     * 可以进行员工姓名的模糊查询
     * 可以进行员工工号的精确查询
     * 可以进行分页
     *
     * @param map 传入参数，工号，姓名，page，limit
     * @return {@link List}<{@link Map}<{@link String},{@link String}>> 员工信息的集合
     */
    List<Map<String, String>> getAll(Map<String,Object> map);

    /**
     * 查询员工数量
     *
     * @return {@link Integer} 数量
     */
    Integer getNums();

    /**
     * 新增员工信息
     *
     * @param emp 员工对象
     */
    void save(Emp emp);

    /**
     * 更新员工信息
     *
     * @param emp 员工对象
     * @return {@link Integer} 改变的行数
     */
    void edit(Emp emp);
}
