package com.jxd.travel.service.impl;

import com.jxd.travel.mapper.TicketMapper;
import com.jxd.travel.model.Ticket;
import com.jxd.travel.service.ITicketService;
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
    public Integer getNumsByEno(String enoStr) {
        Integer eno = Integer.parseInt(enoStr);
        return ticketMapper.selectNumsByEno(eno);
    }

    @Override
    public Ticket getNewByEno(Integer eno) {
        return ticketMapper.selectNewByEno(eno);
    }


}
