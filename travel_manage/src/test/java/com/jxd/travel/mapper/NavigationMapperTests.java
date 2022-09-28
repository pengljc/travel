package com.jxd.travel.mapper;

import com.jxd.travel.model.Emp;
import com.jxd.travel.model.Navigation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName UserMapperTests
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NavigationMapperTests {
    @Autowired
    private NavigationMapper navigationMapper;

    @Test
    public void selectByPostno() {
        List<Navigation> navList = navigationMapper.selectByPostno(1);
        System.out.println(navList);
    }
}
