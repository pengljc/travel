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
        Ticket ticket = ticketMapper.selectNewByEno(10003);
        System.out.println(ticket);
    }
    @Test
    public void selectByTno() {
        Ticket ticket = ticketMapper.selectByTno(1016);
        System.out.println(ticket);
    }

    @Test
    public void insert() {
        Ticket ticket = ticketMapper.selectNewByEno(10002);
        //Ticket(tno=1011, eno=10001, bno=null, vno=6, type=1, leavePlace=青岛, arrivePlace=济南,
        // leaveTime=2022-08-26 13:30:00, arriveTime=2022-08-26 16:36:00, ticketMoney=267.0, photo=null, description=null)
        ticket.setTno(null);
        ticket.setTicketMoney(280D);
        ticket.setType(0);
        ticket.setLeavePlace("济南");
        ticket.setArrivePlace("北京");
        Integer insert = ticketMapper.insert(ticket);
        System.out.println(insert);
    }

    @Test
    public void deleteBatch() {
        Integer[] tnos = new Integer[] {1021,1022,1023,1024};
        Integer rows = ticketMapper.deleteBatch(tnos);
        System.out.println(rows);
    }
}
