package com.wolf.material.mapper;

import com.wolf.material.pojo.Mcategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Title: MrevisionsMapper
 * @Description: 物资种类数据库语句编写
 * @author 吴科男/吴志雄
 * @date 2019/11/10
 **/
@Mapper
public interface McategoryMapper {
    @Select("SELECT * FROM mcategory")
    List<Mcategory>findAll()throws Exception;

    @Select("select * from mcategory where CID=#{cid}")
    List<Mcategory>findOne(Integer cid) throws Exception;

}
