package com.wolf.material.service;

import com.wolf.material.pojo.UUser;

import java.util.List;

/**
 * @description:
 * @author: 江毅东
 * @createDate: 2019/10/31
 * @version: 1.0
 */
public interface UUserService {

    List<UUser> findOne(Integer id) throws Exception;
}
