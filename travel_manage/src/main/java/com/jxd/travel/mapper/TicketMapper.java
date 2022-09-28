package com.jxd.travel.mapper;

import com.jxd.travel.model.Ticket;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TicketMapper
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/28
 * @Version 1.0
 */
public interface TicketMapper {
    /**
     * 通过工号查询所有车票信息；车票表和交通类型表关联
     * 可以进行出发地和到达地的模糊查询
     * 可以进行分页
     *
     * @param map 传入参数，工号，出发地，到达地，page，limit
     * @return {@link List}<{@link Map}<{@link String},{@link String}>> 属于该用户的所有车票信息的集合
     */
    List<Map<String,String>> selectWithVehicleByEno(Map<String,Object> map);

    /**
     * 查询该工号下的所有票数
     *
     * @param eno 工号
     * @return {@link Integer} 该工号下的票数
     */
    Integer selectNumsByEno(Integer eno);

    /**
     * 查询当前工号下最新的一条车票信息
     *
     * @param eno 工号
     * @return {@link Ticket} 最新的车票信息
     */
    Ticket selectNewByEno(Integer eno);
}
