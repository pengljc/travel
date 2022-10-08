package com.jxd.travel.service.impl;

import com.jxd.travel.mapper.BillMapper;
import com.jxd.travel.model.Bill;
import com.jxd.travel.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BillServiceImpl
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/10/8
 * @Version 1.0
 */
@Service
public class BillServiceImpl implements IBillService {
    @Autowired
    private BillMapper billMapper;

    @Override
    public List<Bill> getByEno(Map<String, Object> map) {
        //取出page和limit
        Integer page = Integer.parseInt((String)map.get("page"));
        Integer limit = Integer.parseInt((String)map.get("limit"));
        //对page进行运算
        page = (page - 1) * limit;
        //把新数据存入map
        map.put("page",page);
        map.put("limit",limit);

        return billMapper.selectByEno(map);
    }

    @Override
    public Integer getNumsByEno(String enoStr) {
        Integer eno = Integer.parseInt(enoStr);
        return billMapper.selectNumsByEno(eno);
    }
}
