package com.jxd.travel.service.impl;

import com.jxd.travel.mapper.NavigationMapper;
import com.jxd.travel.model.Navigation;
import com.jxd.travel.service.INavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName NavigationServiceImpl
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@Service
public class NavigationServiceImpl implements INavigationService {
    @Autowired
    private NavigationMapper navigationMapper;


    @Override
    public List<Navigation> getByPostno(Integer postno) {
        return navigationMapper.selectByPostno(postno);
    }
}
