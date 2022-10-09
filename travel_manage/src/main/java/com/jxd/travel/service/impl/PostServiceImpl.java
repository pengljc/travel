package com.jxd.travel.service.impl;

import com.jxd.travel.mapper.PostMapper;
import com.jxd.travel.model.Post;
import com.jxd.travel.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PostServiceImpl
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/10/9
 * @Version 1.0
 */
@Service
public class PostServiceImpl  implements IPostService {
    @Autowired
    private PostMapper postMapper;


    @Override
    public List<Post> getAll() {
        return postMapper.selectAll();
    }
}
