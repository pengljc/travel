package com.jxd.travel.service;

import com.jxd.travel.model.Bill;

import java.util.List;
import java.util.Map;

public interface IBillService {
    /**
     * 通过工号查询所有报销信息；
     * 可以进行单据序号和出差时间的查询
     * 可以进行分页
     *
     * @param map 传入参数，单据序号bno，工号eno，起始时间，结束时间，page，limit
     * @return {@link Bill}
     */
    List<Bill> getByEno(Map<String,Object> map);

    /**
     * 查询该工号下的所有报销票据
     *
     * @param enoStr 工号
     * @return {@link Integer} 工号下的所有报销票据数量
     */
    Integer getNumsByEno(String enoStr);
}
