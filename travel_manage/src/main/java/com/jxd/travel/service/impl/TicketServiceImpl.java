package com.jxd.travel.service.impl;

import com.jxd.travel.mapper.TicketMapper;
import com.jxd.travel.model.Ticket;
import com.jxd.travel.service.ITicketService;
import com.jxd.travel.service.ex.DeleteException;
import com.jxd.travel.service.ex.InsertException;
import com.jxd.travel.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TicketServiceImpl
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/28
 * @Version 1.0
 */
@Service
public class TicketServiceImpl implements ITicketService {
    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public List<Map<String, String>> getByEno(Map<String,Object> map) {
        //取出page和limit
        Integer page = Integer.parseInt((String)map.get("page"));
        Integer limit = Integer.parseInt((String)map.get("limit"));
        //对page进行运算
        page = (page - 1) * limit;
        //把新数据存入map
        map.put("page",page);
        map.put("limit",limit);

        return ticketMapper.selectWithVehicleByEno(map);
    }

    @Override
    public Integer getNumsByEno(String enoStr,String bno) {
        Integer eno = Integer.parseInt(enoStr);
        return ticketMapper.selectNumsByEno(eno,bno);
    }

    @Override
    public Ticket getNewByEno(Integer eno) {
        return ticketMapper.selectNewByEno(eno);
    }

    @Override
    public Ticket getByTno(Integer tno) {
        return ticketMapper.selectByTno(tno);
    }

    @Override
    public void saveTicket(Ticket ticket) {
        Integer rows = ticketMapper.insert(ticket);
        //改变行数不为一行时，抛出异常
        if (rows != 1) {
            throw new InsertException("在车票插入数据库过程中产生了未知的异常");
        }
    }

    @Override
    public void deleteBatch(Integer[] tnos) {
        Integer rows = ticketMapper.deleteBatch(tnos);
        //删除行数和传入的要删除的车票数量不等时，抛出异常
        if (rows != tnos.length) {
            throw new DeleteException("数据库中的车票，批量删除的过程中产生了未知的异常");
        }
    }

    @Override
    public void editWhole(Ticket ticket) {
        Integer rows = ticketMapper.updateWhole(ticket);
        //改变行数不为一行时，抛出异常
        if (rows != 1) {
            throw new UpdateException("数据库中的车票，修改更新的过程中产生了未知的异常");
        }
    }


}
