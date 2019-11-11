package com.wolf.material.service;

import com.wolf.material.pojo.Tb_materials;

import java.util.List;

/**
 * @description:
 * @author: 江毅东
 * @createDate: 2019/10/31
 * @version: 1.0
 */
public interface Tb_materiaslsService {
    Boolean insertOne(Tb_materials tb_materials) throws Exception;
    Boolean deleteOne(Integer Iid)throws Exception;
    List<Tb_materials> findAll()throws Exception;
}
