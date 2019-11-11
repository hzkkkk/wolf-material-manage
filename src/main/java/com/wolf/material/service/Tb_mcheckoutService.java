package com.wolf.material.service;

import com.wolf.material.pojo.Tb_mcheckout;

import java.util.List;

/**
 * @description:
 * @author: 江毅东
 * @createDate: 2019/10/29
 * @version: 1.0
 */
public interface Tb_mcheckoutService {

    Boolean insertOne(Tb_mcheckout tb_mcheckout) throws Exception;

    List<Tb_mcheckout> findOne(Integer id) throws Exception;

    //查找软件组所有成员
    List<Tb_mcheckout> findAll() throws Exception;

    boolean TBAvailableOne(Integer id) throws Exception;
}
