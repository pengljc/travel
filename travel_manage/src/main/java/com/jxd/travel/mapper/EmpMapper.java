package com.jxd.travel.mapper;

import com.jxd.travel.model.Emp;

public interface EmpMapper {
    /**
     * 通过工号查询员工的数据信息
     *
     * @param eno 工号
     * @return {@link Emp} 员工的数据信息，null代表未查到
     */
    Emp selectByEno(Integer eno);
}
