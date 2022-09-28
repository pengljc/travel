package com.jxd.travel.controller;

import com.jxd.travel.model.Emp;
import com.jxd.travel.service.IEmpService;
import com.jxd.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EmpController
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@RestController
@RequestMapping("/emps")
public class EmpController extends BaseController {
    @Autowired
    private IEmpService empService;

    @PostMapping
    public JsonResult<Emp> login(@RequestBody Emp emp) {
        Emp result = empService.getByEno(emp);
        return new JsonResult<Emp>(OK,result);
    }
}
