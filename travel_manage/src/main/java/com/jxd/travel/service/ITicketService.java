package com.jxd.travel.service;

import com.jxd.travel.model.Ticket;

import java.util.List;
import java.util.Map;

public interface ITicketService {
    /**
     * 通过工号查询所有车票信息
     * 可以进行出发地和到达地的模糊查询
     * 可以进行分页
     *
     * @param map 传入参数，工号，出发地，到达地，page，limit
     * @return {@link List}<{@link Map}<{@link String}, {@link Object}>>  属于该用户的所有车票信息的集合
     */
    List<Map<String, String>> getByEno(Map<String,Object> map);

    /**
     * 查询该工号下的所有票数
     *
     * @param enoStr 工号
     * @return {@link Integer} 工号下的所有票数
     */
    Integer getNumsByEno(String enoStr);

    /**
     * 查询当前工号下最新的一条车票信息
     *
     * @param eno 工号
     * @return {@link Ticket} 最新的车票信息
     */
    Ticket getNewByEno(Integer eno);
}
