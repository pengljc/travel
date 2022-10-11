package com.jxd.travel.service.impl;

import com.jxd.travel.mapper.TicketMapper;
import com.jxd.travel.mapper.TypeMapper;
import com.jxd.travel.model.Type;
import com.jxd.travel.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TypeServiceImpl
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/10/10
 * @Version 1.0
 */
@Service
public class TypeServiceImpl implements ITypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> getAll() {
        return typeMapper.selectAll();
    }
}
