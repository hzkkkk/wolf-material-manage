package com.wolf.material.mapper;

/**
 * @description: 物质表
 * @author: 江毅东
 * @createDate: 2019/10/31
 * @version: 1.0
 */
import com.wolf.material.pojo.SoftwareInfo;
import com.wolf.material.pojo.Tb_materials;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
@Mapper//指定该接口为Mapper，注入spring容器
public interface Tb_materialsMapper {

    @Insert("insert into tb_materials(IID,Iname,CID,Inote,Istate) values(null,#{Iname},#{Cid},#{Inote},#{Istate})")
    boolean insertOne(Tb_materials tb_materials) throws Exception;
    @Delete("DELETE FROM tb_materials WHERE IID=#{Iid}")
    boolean deleteOne(Integer Iid)throws Exception;

    @Update("update tb_materials set Ostate= where uid=#{uid}")
    void updateBorrow(Integer uid) throws Exception;


}
