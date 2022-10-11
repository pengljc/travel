package com.jxd.travel.service;

import com.jxd.travel.model.Type;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ITypeService {
    List<Type> getAll();
}
