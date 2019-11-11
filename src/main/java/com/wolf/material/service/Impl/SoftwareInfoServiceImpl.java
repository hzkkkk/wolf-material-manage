/**
 * @Title: SoftwareInfoServiceImpl
 * @Description: SoftwareInfoService实现类
 * @author 黄彦钊
 * @date 2019/9/23
 **/
package com.wolf.material.service.Impl;
import com.wolf.material.mapper.SoftwareInfoMapper;
import com.wolf.material.pojo.SoftwareInfo;
import com.wolf.material.service.SoftwareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareInfoServiceImpl implements SoftwareInfoService {
    @Autowired(required = false) //承认mapper用非bean配置，否则报错
    private SoftwareInfoMapper software_infoMapper;

    @Override
    public List<SoftwareInfo> findAll() throws Exception {
        return software_infoMapper.findAll();
    }

    @Override
    public List<SoftwareInfo> findOne(Integer id) throws Exception {
        return software_infoMapper.findOne(id);
    }



}