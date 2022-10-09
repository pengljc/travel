package com.jxd.travel.service;

import com.jxd.travel.model.Post;

import java.util.List;

public interface IPostService {
    /**
     * 得到所有职位信息
     *
     * @return {@link List}<{@link Post}>
     */
    List<Post> getAll();
}