package com.jxd.travel.service;

import com.jxd.travel.model.Emp;

/**
 * @ClassName IEmpService
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
public interface IEmpService {
    /**
     * 通过工号查询员工的数据信息，之后登录校验
     *
     * @param emp 包含工号和密码的员工对象信息
     * @return {@link Emp} 查询出的员工数据信息
     */
    Emp getByEno(Emp emp);
}
