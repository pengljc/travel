package com.jxd.travel.service;

import com.jxd.travel.model.Emp;
import com.jxd.travel.model.Navigation;
import com.jxd.travel.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName EmpServiceTests
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NavigationServiceTests {
    @Autowired
    private INavigationService navigationService;

    @Test
    public void getByPostno() {
        List<Navigation> navList = navigationService.getByPostno(1);
        System.out.println(navList);
    }
}
