package com.jxd.travel.mapper;

import com.jxd.travel.model.Bill;
import com.jxd.travel.service.INavigationService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BillMapper
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/10/8
 * @Version 1.0
 */
public interface BillMapper {
    /**
     * 通过工号查询所有报销信息；
     * 可以进行单据序号和出差时间的查询
     * 可以进行分页
     *
     * @param map 传入参数，单据序号bno，工号eno，起始时间，结束时间，page，limit
     * @return {@link Bill}
     */
    List<Bill> selectByEno(Map<String,Object> map);

    /**
     * 查询该工号下的所有报销票据
     *
     * @param eno 工号
     * @return {@link Integer} 工号下的所有报销票据数量
     */
    Integer selectNumsByEno(Integer eno);

    /**
     * 根据bno得到报销的数据对象
     *
     * @param bno bno 报销信息主键
     * @return {@link Bill} bill的数据对象
     */
    Bill selectByBno(Integer bno);

    /**
     * 插入报销票据
     *
     * @param map 传入需要插入的参数
     * @return {@link Integer} 数据库改变的行数
     */
    Integer insertBill(Map<String,Object> map);

    /**
     * 更新报销票据
     *
     * @param map 传入需要的bill参数
     * @return {@link Integer} 改变的行数
     */
    Integer updateBill(Map<String,Object> map);

    /**
     * 删除批处理票据信息
     *
     * @param bnos 要删除的票据
     * @return {@link Integer} 删除的行数
     */
    Integer deleteBatch(Integer[] bnos);
}
