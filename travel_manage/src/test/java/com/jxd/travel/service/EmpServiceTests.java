package com.jxd.travel.service;

import com.jxd.travel.model.Emp;
import com.jxd.travel.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName EmpServiceTests
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpServiceTests {
    @Autowired
    private IEmpService empService;

    @Test
    public void getByEno() {

        try {
            Emp emp = new Emp();

            //1.工号输错
            //emp.setEno(1);


            //2.密码输错
            //emp.setEno(10000);
            //emp.setPassword("1");

            //3.输入正确
            emp.setEno(10000);
            emp.setPassword("123");

            Emp result = empService.getByEnoLogin(emp);
            System.out.println(result);

        } catch (ServiceException e) {
            //获取类的对象，再获取类的名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常的具体描述信息
            System.out.println(e.getMessage());
        }

    }
}
