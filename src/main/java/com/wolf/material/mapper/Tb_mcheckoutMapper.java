package com.wolf.material.mapper;

import com.wolf.material.pojo.Tb_mcheckout;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description: 借还表：记录每一条借还信息
 * @author: 江毅东
 * @createDate: 2019/10/29
 * @version: 1.0
 */
@Mapper
public interface Tb_mcheckoutMapper {

    @Insert("insert into tb_mcheckout(OID,IID,UID,Ostate,Otime) values(null,#{IID},#{UID},#{Ostate},#{Otime})")
    boolean insertOne(Tb_mcheckout tb_mcheckout) throws Exception;

    @Select("select * from tb_mcheckout where IID = #{IID}")//#{id}为动态
    List<Tb_mcheckout> findOne(Integer id) throws Exception;

    @Select("select * from tb_mcheckout")//#{id}为动态
    List<Tb_mcheckout> findAll() throws Exception;

    @Select("select * from tb_mcheckout where UID = #{UID}")//#{id}为动态
    boolean TBAvailableOne(Integer id) throws Exception;
}
