/**
 * @Title: SoftwareInfoMapper
 * @Description: 软件组成员sql语句编写
 * @author 黄彦钊
 * @date 2019/9/23
 **/
package com.wolf.material.mapper;
import com.wolf.material.pojo.SoftwareInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper//指定该接口为Mapper，注入spring容器
public interface SoftwareInfoMapper {

    @Select("select * from SoftwareInfo") //将sql语句绑定该接口，调用时自动向数据库发送语句
    List<SoftwareInfo> findAll() throws Exception;
    @Select("select * from SoftwareInfo where sw_id = #{id}")//#{id}为动态
    List<SoftwareInfo> findOne(Integer id) throws Exception;

}
