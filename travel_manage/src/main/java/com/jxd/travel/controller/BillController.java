package com.jxd.travel.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.jxd.travel.model.Bill;
import com.jxd.travel.model.Ticket;
import com.jxd.travel.service.IBillService;
import com.jxd.travel.service.ITicketService;
import com.jxd.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
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
    @Autowired
    private ITicketService ticketService;

    @GetMapping
    public JsonResult<List<Bill>> getByEno(@RequestParam(required = false) Map<String,Object> map) {
        List<Bill> billList = billService.getByEno(map);
        Integer total = billService.getNumsByEno((String)map.get("eno"));
        return new JsonResult<List<Bill>>(OK,billList,total);
    }

    @GetMapping("/bno/{bno}")
    public JsonResult<Bill> getNewByEno(@PathVariable("bno") Integer bno) {
        Bill result = billService.getByBno(bno);
        return new JsonResult<Bill>(OK,result);
    }

    @PostMapping
    public JsonResult<Void> addBill(@RequestBody Map<String,Object> map) {
        //取出map中bill中的信息，存入bill集合
        Map bill = (HashMap<String,Object>)map.get("bill");
        //执行插入方法，主键自动存入bill中，键为bill
        billService.insertBill(bill);

        //取出主键
        BigInteger bnoBig = (BigInteger)bill.get("bno");
        Integer bno = bnoBig.intValue();
        //取出出差描述
        String description = (String) bill.get("description");
        //取出关联的票据数组
        List<Integer> tnosList = (List<Integer>) map.get("tnos");
        Integer[] tnos = tnosList.toArray(new Integer[0]);
        //调用ticket的修改方法，把出差描述和bill主键存入
        ticketService.editBatchByTnos(tnos,bno,description);

        return new JsonResult<>(OK);
    }

    @PutMapping
    public JsonResult<Void> editBill(@RequestBody Map<String,Object> map) {
        //取出map中bill中的信息，存入bill集合
        Map bill = (HashMap<String,Object>)map.get("bill");
        //取出报销id
        Integer bno = Integer.parseInt((String) bill.get("bno"));
        //取出出差描述
        String description = (String) bill.get("description");
        //取出关联的票据数组
        List<Integer> tnosDelList = (List<Integer>) map.get("delTnos");
        Integer[] delTnos = tnosDelList.toArray(new Integer[0]);
        //取出关联的票据数组
        List<Integer> tnosList = (List<Integer>) map.get("tnos");
        Integer[] tnos = tnosList.toArray(new Integer[0]);

        //执行更新方法
        billService.updateBill(bill);
        //删除之前的ticket的bno和description,即清空
        ticketService.editBatchByTnos(delTnos,null,null);
        //为新的的ticket赋值bno和description
        ticketService.editBatchByTnos(tnos,bno,description);

        return new JsonResult<>(OK);
    }

    @DeleteMapping
    public JsonResult<Void> deleteBatch(@RequestBody Integer[] bnos) {
        billService.deleteBatch(bnos);
        return new JsonResult<>(OK);
    }
}
