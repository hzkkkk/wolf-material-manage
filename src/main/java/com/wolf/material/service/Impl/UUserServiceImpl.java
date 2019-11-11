package com.wolf.material.service.Impl;

import com.wolf.material.mapper.UUserMapper;
import com.wolf.material.pojo.UUser;
import com.wolf.material.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户表
 * @author: 江毅东
 * @createDate: 2019/10/31
 * @version: 1.0
 */

@Service
public class UUserServiceImpl implements UUserService {

    @Autowired(required = false)
    private UUserMapper uUserMapper;

    @Override
    public List<UUser> findOne(Integer id) throws Exception {
        return uUserMapper.UUserFindOne(id);
    }
}
