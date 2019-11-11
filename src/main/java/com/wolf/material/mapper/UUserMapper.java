package com.wolf.material.mapper;

import com.wolf.material.pojo.UUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description: 用户表
 * @author: 江毅东
 * @createDate: 2019/10/31
 * @version: 1.0
 */
@Mapper
public interface UUserMapper {
    @Select("select * from uuser where UID = #{UID}")//#{id}为动态
    List<UUser> UUserFindOne (Integer id) throws Exception;
}
