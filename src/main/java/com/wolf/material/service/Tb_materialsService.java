package com.wolf.material.service;

import com.wolf.material.pojo.Tb_materials;

/**
 * @description:
 * @author: 江毅东
 * @createDate: 2019/10/31
 * @version: 1.0
 */
public interface Tb_materialsService {
    Boolean insertOne(Tb_materials tb_materials) throws Exception;
    Boolean deleteOne(Integer Iid)throws Exception;
}
