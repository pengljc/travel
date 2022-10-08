package com.jxd.travel.mapper;

import com.jxd.travel.model.Ticket;
import sun.util.resources.cldr.kea.TimeZoneNames_kea;

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
     * 可以查没绑定报销号的所有信息
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
    Integer selectNumsByEno(Integer eno,String bno);

    /**
     * 查询当前工号下最新的一条车票信息
     *
     * @param eno 工号
     * @return {@link Ticket} 最新的车票信息
     */
    Ticket selectNewByEno(Integer eno);

    /**
     * 根据车票号查询车票的数据对象
     *
     * @param tno tno 车票号
     * @return {@link Ticket} 车票的数据对象
     */
    Ticket selectByTno(Integer tno);

    /**
     * 新增一条车票数据
     *
     * @param ticket 车票数据对象
     * @return {@link Integer} 数据库改变的行数  1 为新增成功 其余为新增失败
     */
    Integer insert(Ticket ticket);

    /**
     * 批量删除车票
     *
     * @param tnos 车票数组
     * @return {@link Integer} 改变的行数，行数为数组长度即删除成功，其余情况删除失败
     */
    Integer deleteBatch(Integer[] tnos);

    /**
     * 更新整体车票信息
     *
     * @param ticket 车票数据对象
     * @return {@link Integer} 更新的行数， 1为修改成功，其余全为修改失败
     */
    Integer updateWhole(Ticket ticket);
}
