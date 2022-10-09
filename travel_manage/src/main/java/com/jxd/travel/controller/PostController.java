package com.jxd.travel.controller;

import com.jxd.travel.model.Post;
import com.jxd.travel.model.Vehicle;
import com.jxd.travel.service.IPostService;
import com.jxd.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName PostController
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/10/9
 * @Version 1.0
 */
@RestController
@RequestMapping("/posts")
public class PostController extends BaseController {
    @Autowired
    private IPostService postService;


    @GetMapping
    JsonResult<List<Post>> getAllVehicle() {
        List<Post> result = postService.getAll();
        return new JsonResult<>(OK,result);
    }
}
