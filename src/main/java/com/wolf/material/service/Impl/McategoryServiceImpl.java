package com.wolf.material.service.Impl;
/**
 * @Title: McategoryServiceImpl
 * @Description: McategoryService实现类
 * @author 吴科男
 * @date 2019/11/10
 **/

import com.wolf.material.mapper.McategoryMapper;
import com.wolf.material.pojo.Mcategory;
import com.wolf.material.service.McategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class McategoryServiceImpl implements McategoryService {
    @Autowired(required = false)
    private McategoryMapper mapper;
    @Override
    public List<Mcategory> findAll()throws Exception {
        return mapper.findAll();
    }

    @Override
    public List<Mcategory> findOne(Integer cid)throws Exception {
        return mapper.findOne(cid);
    }
}
