package com.jxd.travel.service.impl;

import com.jxd.travel.mapper.EmpMapper;
import com.jxd.travel.model.Emp;
import com.jxd.travel.service.IEmpService;
import com.jxd.travel.service.ex.EnoNotFindException;
import com.jxd.travel.service.ex.InsertException;
import com.jxd.travel.service.ex.PasswordNotMatchException;
import com.jxd.travel.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public Emp getByEno(Integer eno) {
        return empMapper.selectByEno(eno);
    }

    @Override
    public Emp getByEnoLogin(Emp emp) {
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

    @Override
    public List<Map<String, String>> getAll(Map<String, Object> map) {
        //取出page和limit
        Integer page = Integer.parseInt((String)map.get("page"));
        Integer limit = Integer.parseInt((String)map.get("limit"));
        //对page进行运算
        page = (page - 1) * limit;
        //把新数据存入map
        map.put("page",page);
        map.put("limit",limit);

        return empMapper.selectWithPost(map);
    }

    @Override
    public Integer getNums() {
        return empMapper.selectNums();
    }

    @Override
    public void save(Emp emp) {
        Integer rows = empMapper.insert(emp);
        if (rows != 1) {
            throw new InsertException("在员工插入数据库过程中产生了未知的异常");
        }
    }

    @Override
    public void edit(Emp emp) {
        Integer rows = empMapper.update(emp);
        //改变行数不为一行时，抛出异常
        if (rows != 1) {
            throw new UpdateException("数据库中的员工信息，修改的过程中产生了未知的异常");
        }
    }
}
