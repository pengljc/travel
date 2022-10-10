package com.jxd.travel.service;

import com.jxd.travel.model.Ticket;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Transactional
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
     * 通过票据号查询所有车票信息；车票表和交通类型表关联
     *
     * @param bno bno 单据号
     * @return {@link List}<{@link Map}<{@link String},{@link String}>> 属于该单据的所有车票信息的集合
     */
    List<Map<String, String>> getByBno(Integer bno);

    /**
     * 查询该工号下的所有票数
     *
     * @param enoStr 工号
     * @return {@link Integer} 工号下的所有票数
     */
    Integer getNumsByEno(String enoStr,String bno);

    /**
     * 查询当前工号下最新的一条车票信息
     *
     * @param eno 工号
     * @return {@link Ticket} 最新的车票信息
     */
    Ticket getNewByEno(Integer eno);

    /**
     * 根据车票号查询车票的数据对象
     *
     * @param tno tno 车票号
     * @return {@link Ticket}  车票的数据对象
     */
    Ticket getByTno(Integer tno);
    /**
     * 保存一条车票数据
     *
     * @param ticket 车票的数据对象
     * @return {@link Void}
     */
    void saveTicket(Ticket ticket);

    /**
     * 批量删除车票
     *
     * @param tnos tno 车票号数组
     */
    void deleteBatch(Integer[] tnos);

    /**
     * 编辑整体车票信息
     *
     * @param ticket 车票的数据对象
     */
    void editWhole(Ticket ticket);

    /**
     * 根据车票号批量更新
     *
     * @param tnos        tno 车票号
     * @param bno         bno 报销票据号
     * @param description 出差描述
     * @return {@link Integer}  改变的行数
     */
    void editBatchByTnos(Integer[] tnos, Integer bno, String description);
}
