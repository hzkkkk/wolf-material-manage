/**
 * @Title: SoftwareInfo
 * @Description: 软件组成员逻辑实体类
 * @author 黄彦钊
 * @date 2019/6/9
 */
package com.wolf.material.service;
import com.wolf.material.pojo.SoftwareInfo;

import java.util.List;

public interface SoftwareInfoService {
    //查找软件组所有成员
    List<SoftwareInfo> findAll() throws Exception;
    //根据id查找数据库中对应软件组成员信息
    List<SoftwareInfo> findOne(Integer id) throws Exception;


}