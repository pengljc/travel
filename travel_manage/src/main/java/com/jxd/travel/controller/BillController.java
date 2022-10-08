package com.jxd.travel.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.jxd.travel.model.Bill;
import com.jxd.travel.service.IBillService;
import com.jxd.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BillController
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/10/8
 * @Version 1.0
 */
@RestController
@RequestMapping("/bills")
public class BillController extends BaseController {
    @Autowired
    private IBillService billService;

    @GetMapping
    public JsonResult<List<Bill>> getByEno(@RequestParam(required = false) Map<String,Object> map) {
        List<Bill> billList = billService.getByEno(map);
        Integer total = billService.getNumsByEno((String)map.get("eno"));
        return new JsonResult<List<Bill>>(OK,billList,total);
    }
}
