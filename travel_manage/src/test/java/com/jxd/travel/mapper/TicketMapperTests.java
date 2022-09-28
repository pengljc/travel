package com.jxd.travel.mapper;

import com.jxd.travel.model.Emp;
import com.jxd.travel.model.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserMapperTests
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketMapperTests {
    @Autowired
    private TicketMapper ticketMapper;

    @Test
    public void selectWithVehicleByEno() {
        Map<String,Object> map = new HashMap<>();
        map.put("eno","10001");
        map.put("arrivePlace","济");
        map.put("leavePlace","岛");
        map.put("page",0);
        map.put("limit",1);
        System.out.println(map);
        List<Map<String, String>> list = ticketMapper.selectWithVehicleByEno(map);
        System.out.println(list);
    }

    @Test
    public void selectNewByEno() {
        Ticket ticket = ticketMapper.selectNewByEno(10001);
        System.out.println(ticket);
    }
}
