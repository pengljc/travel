package com.jxd.travel.mapper;

import com.jxd.travel.model.Bill;
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
public class BillMapperTests {
    @Autowired
    private BillMapper billMapper;

    @Test
    public void selectByEno() {
        Map<String,Object> map = new HashMap<>();
        map.put("eno","10001");
        map.put("arrivePlace","济");
        map.put("leavePlace","岛");
        map.put("page",0);
        map.put("limit",1);
        System.out.println(map);
        List<Bill> billList = billMapper.selectByEno(map);
        System.out.println(billList);
    }

    @Test
    public void selectNumsByEno() {
        Integer nums = billMapper.selectNumsByEno(10001);
        System.out.println(nums);
    }
}
