package com.jxd.travel.service.impl;

import com.jxd.travel.mapper.EmpMapper;
import com.jxd.travel.model.Emp;
import com.jxd.travel.service.IEmpService;
import com.jxd.travel.service.ex.EnoNotFindException;
import com.jxd.travel.service.ex.PasswordNotMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName EmpServiceImpl
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@Service
public class EmpServiceImpl implements IEmpService {
    @Autowired
    private EmpMapper empMapper;


    @Override
    public Emp getByEno(Emp emp) {
        //取出工号
        Integer eno = emp.getEno();
        //通过工号查出数据对象
        Emp result = empMapper.selectByEno(eno);

        //判断工号是否正确
        if (result == null) {
            throw new EnoNotFindException("该工号不存在");
        }

        //密码校验
        if (!result.getPassword().equals(emp.getPassword())) {
            throw new PasswordNotMatchException("密码输入错误");
        }

        return result;
    }
}
