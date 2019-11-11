package com.wolf.material.service.Impl;

import com.wolf.material.mapper.Tb_mcheckoutMapper;
import com.wolf.material.pojo.Tb_mcheckout;
import com.wolf.material.service.Tb_mcheckoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:借还表
 * @author: 江毅东
 * @createDate: 2019/10/29
 * @version: 1.0
 */
@Service
public class Tb_mcheckoutServiceImpl implements Tb_mcheckoutService {
    @Autowired(required = false) //承认mapper用非bean配置，否则报错
    private Tb_mcheckoutMapper tb_mcheckoutMapper;

    @Override
    public Boolean insertOne(Tb_mcheckout tb_mcheckout) throws Exception {
        return tb_mcheckoutMapper.insertOne(tb_mcheckout);
    }


    @Override
    public List<Tb_mcheckout> findOne(Integer id) throws Exception {
        return tb_mcheckoutMapper.findOne(id);
    }

    @Override
    public List<Tb_mcheckout> findAll() throws Exception {
        return tb_mcheckoutMapper.findAll();
    }

    @Override
    public boolean TBAvailableOne(Integer id) throws Exception {
        return tb_mcheckoutMapper.TBAvailableOne(id);
    }
}
