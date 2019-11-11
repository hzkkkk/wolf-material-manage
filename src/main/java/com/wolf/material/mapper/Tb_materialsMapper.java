package com.wolf.material.mapper;

/**
 * @description: 物资表
 * @author: 江毅东
 * @createDate: 2019/10/31
 * @version: 1.0
 */

import com.wolf.material.pojo.Tb_materials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//指定该接口为Mapper，注入spring容器
public interface Tb_materialsMapper {
    @Insert("insert into tb_materials(IID,Iname,CID,Inote,Istate) values(null,#{Iname},#{Cid},#{Inote},'available')")
    boolean insertOne(Tb_materials tb_materials) throws Exception;
    @Delete("DELETE FROM tb_materials WHERE IID=#{Iid}")
    boolean deleteOne(Integer Iid)throws Exception;
    @Update("update tb_materials set Ostate= where uid=#{uid}")
    void updateBorrow(Integer uid) throws Exception;
    @Select("SELECT * FROM tb_materials")
    List<Tb_materials>findAll()throws Exception;

}
