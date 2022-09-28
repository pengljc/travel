package com.jxd.travel.controller;

import com.jxd.travel.model.Navigation;
import com.jxd.travel.service.INavigationService;
import com.jxd.travel.util.JsonResult;
import com.mysql.cj.x.protobuf.Mysqlx;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName NavigationController
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@RestController
@RequestMapping("/navs")
public class NavigationController extends BaseController {
    @Autowired
    private INavigationService navigationService;

    @GetMapping("/{postno}")
    JsonResult<List<Navigation>> getNav(@PathVariable("postno") Integer postno) {
//        Integer postno = navigation.getPostno();
        List<Navigation> navList = navigationService.getByPostno(postno);
        return new JsonResult<List<Navigation>>(OK,navList);
    }
}
