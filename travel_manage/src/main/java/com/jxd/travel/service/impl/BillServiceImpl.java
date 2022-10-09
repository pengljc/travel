package com.jxd.travel.service.impl;

import com.jxd.travel.mapper.BillMapper;
import com.jxd.travel.mapper.TicketMapper;
import com.jxd.travel.model.Bill;
import com.jxd.travel.service.IBillService;
import com.jxd.travel.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BillServiceImpl
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/10/8
 * @Version 1.0
 */
@Service
public class BillServiceImpl implements IBillService {
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public List<Bill> getByEno(Map<String, Object> map) {
        //取出page和limit
        Integer page = Integer.parseInt((String)map.get("page"));
        Integer limit = Integer.parseInt((String)map.get("limit"));
        //对page进行运算
        page = (page - 1) * limit;
        //把新数据存入map
        map.put("page",page);
        map.put("limit",limit);

        return billMapper.selectByEno(map);
    }

    @Override
    public Integer getNumsByEno(String enoStr) {
        Integer eno = Integer.parseInt(enoStr);
        return billMapper.selectNumsByEno(eno);
    }

    @Override
    public Bill getByBno(Integer bno) {
        return billMapper.selectByBno(bno);
    }

    @Override
    public void insertBill(Map<String, Object> map) {
        Integer rows = billMapper.insertBill(map);
        //改变行数不为一行时，抛出异常
        if (rows != 1) {
            throw new InsertException("票据在数据库，新增的过程中产生了未知的异常");
        }
    }

    @Override
    public void updateBill(Map<String, Object> map) {
        Integer rows = billMapper.updateBill(map);
        //改变行数不为一行时，抛出异常
        if (rows != 1) {
            throw new InsertException("票据在数据库，修改的过程中产生了未知的异常");
        }
    }

    @Override
    public void deleteBatch(Integer[] bnos) {
        //删除车票关联的bno和description
        Integer rows1 = ticketMapper.updateBatchByBno(bnos);
        //行数不对则抛出异常
        if (rows1 == 0) {
            throw new InsertException("车票在数据库，修改的过程中产生了未知的异常");
        }

        //批量删除单据
        Integer rows2 = billMapper.deleteBatch(bnos);
        if (rows2 != bnos.length) {
            throw new InsertException("票据在数据库，修改的过程中产生了未知的异常");
        }
    }
}
