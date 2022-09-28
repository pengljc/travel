package com.jxd.travel.service;

import com.jxd.travel.model.Emp;
import com.jxd.travel.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EmpServiceTests
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTests {
    @Autowired
    private ITicketService ticketService;

    @Test
    public void getByEno() {
        Map<String,Object> map = new HashMap<>();
        map.put("eno","10001");
        map.put("arrivePlace","济");
        map.put("leavePlace","岛");
        map.put("page","1");
        map.put("limit","2");
        List<Map<String, String>> list = ticketService.getByEno(map);
        System.out.println(list);
    }

    @Test
    public void getNewByEno() {
        System.out.println(ticketService.getNewByEno(10001));
    }
}
