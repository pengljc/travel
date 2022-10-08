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
        Integer total = ticketService.getNumsByEno((String)map.get("eno"),(String)map.get("bno"));
        return new JsonResult<List<Map<String, String>>>(OK,mapList,total);
    }

    @GetMapping("/eno/{eno}")
    public JsonResult<Ticket> getNewByEno(@PathVariable("eno") Integer eno) {
        Ticket result = ticketService.getNewByEno(eno);
        return new JsonResult<Ticket>(OK,result);
    }
    @GetMapping("/tno/{tno}")
    public JsonResult<Ticket> getByTno(@PathVariable("tno") Integer tno) {
        Ticket result = ticketService.getByTno(tno);
        return new JsonResult<Ticket>(OK,result);
    }

    @PostMapping
    public JsonResult<Void> saveTicket(@RequestBody Ticket ticket) {
        ticketService.saveTicket(ticket);
        return new JsonResult<Void>(OK);
    }

    @DeleteMapping
    public JsonResult<Void> deleteBatch(@RequestBody Integer[] tnos) {
        ticketService.deleteBatch(tnos);
        return new JsonResult<Void>(OK);
    }

    @PutMapping("/whole")
    public JsonResult<Void> editWhole(@RequestBody Ticket ticket) {
        ticketService.editWhole(ticket);
        return new JsonResult<Void>(OK);
    }
}
