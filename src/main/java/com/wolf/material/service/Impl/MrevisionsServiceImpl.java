package com.wolf.material.service.Impl;

import com.wolf.material.mapper.MrevisionsMapper;
import com.wolf.material.pojo.Mrevisions;
import com.wolf.material.service.MrevisionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MrevisionsServiceImpl implements MrevisionsService {
    @Autowired(required = false)
    private MrevisionsMapper mrevisionsMapper;

    @Override
    public Boolean insertOne(Mrevisions mrevisions) throws Exception{
        return mrevisionsMapper.insertOne(mrevisions);
    }
}
