package com.jxd.travel.service;

import com.jxd.travel.model.Navigation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface INavigationService {
    /**
     * 通过身份码获取属于这个身份的导航栏
     *
     * @param postno postno 身份码
     * @return {@link List}<{@link Navigation}> 属于此身份的导航栏集合
     */
    List<Navigation> getByPostno(Integer postno);
}
