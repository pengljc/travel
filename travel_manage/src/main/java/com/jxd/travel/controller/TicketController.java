package com.jxd.travel.controller;

import com.jxd.travel.model.Ticket;
import com.jxd.travel.service.ITicketService;
import com.jxd.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TicketController
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/28
 * @Version 1.0
 */
@RestController
@RequestMapping("/tickets")
public class TicketController extends BaseController {
    @Autowired
    private ITicketService ticketService;

    @GetMapping
    public JsonResult<List<Map<String,String>>> getByEno(@RequestParam(required = false) Map<String,Object> map) {
        List<Map<String, String>> mapList = ticketService.getByEno(map);
        Integer total = ticketService.getNumsByEno((String)map.get("eno"));
        return new JsonResult<List<Map<String, String>>>(OK,mapList,total);
    }

    @GetMapping("/{eno}")
    public JsonResult<Ticket> getNewByEno(@PathVariable("eno") Integer eno) {
        Ticket result = ticketService.getNewByEno(eno);
        return new JsonResult<Ticket>(OK,result);
    }
}
