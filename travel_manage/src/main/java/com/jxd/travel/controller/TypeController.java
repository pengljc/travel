package com.jxd.travel.controller;

import com.jxd.travel.model.Type;
import com.jxd.travel.service.ITypeService;
import com.jxd.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TypeController
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/10/10
 * @Version 1.0
 */
@RestController
@RequestMapping("/types")
public class TypeController extends BaseController {
    @Autowired
    private ITypeService typeService;

    @GetMapping
    JsonResult<List<Type>> getAll() {
        List<Type> result = typeService.getAll();
        return new JsonResult<>(OK,result);
    }
}
