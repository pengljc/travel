package com.jxd.travel.controller;

import com.jxd.travel.model.Emp;
import com.jxd.travel.service.IEmpService;
import com.jxd.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        Emp result = empService.getByEnoLogin(emp);
        return new JsonResult<Emp>(OK,result);
    }

    @GetMapping
    public JsonResult<List<Map<String,String>>> getAll(@RequestParam(required = false) Map<String,Object> map) {
        List<Map<String, String>> mapList = empService.getAll(map);
        Integer total = empService.getNums();
        return new JsonResult<List<Map<String, String>>>(OK,mapList,total);
    }

    @GetMapping("/{eno}")
    public JsonResult<Emp> getNewByEno(@PathVariable("eno") Integer eno) {
        Emp result = empService.getByEno(eno);
        return new JsonResult<Emp>(OK,result);
    }

    @PostMapping("/add")
    public JsonResult<Void> saveEmp(@RequestBody Emp emp) {
        empService.save(emp);
        return new JsonResult<Void>(OK);
    }

    @PutMapping
    public JsonResult<Void> editEmp(@RequestBody Emp emp) {
        empService.edit(emp);
        return new JsonResult<Void>(OK);
    }
}
