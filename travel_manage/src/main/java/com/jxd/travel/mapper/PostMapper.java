package com.jxd.travel.mapper;

import com.jxd.travel.model.Post;

import java.util.List;

public interface PostMapper {
    /**
     * 得到所有职位信息
     *
     * @return {@link List}<{@link Post}>
     */
    List<Post> selectAll();
}
