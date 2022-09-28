package com.jxd.travel.mapper;

import com.jxd.travel.model.Navigation;

import java.util.List;

/**
 * @ClassName NavigationMapper
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
public interface NavigationMapper {
    /**
     * 通过身份码查询出专属于此身份的导航栏
     *
     * @param postno 身份码
     * @return {@link List}<{@link Navigation}> 属于此身份的导航栏集合
     */
    List<Navigation> selectByPostno(Integer postno);
}
