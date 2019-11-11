/**
 * @Title: MrevisionsMapper
 * @Description: 物资删除记录数据库语句编写
 * @author 吴科男/吴志雄
 * @date 2019/11/08
 **/
package com.wolf.material.mapper;
import com.wolf.material.pojo.Mrevisions;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper//指定该接口为Mapper，注入spring容器
public interface MrevisionsMapper {

    @Insert("insert into mrevisions(MmID,IID,UID,Mstate,Mtime,Mlog) values(null,#{IID},#{UID},#{Mstate},#{Mtime},#{Mlog})")
    boolean insertOne(Mrevisions mrevisions) throws Exception;


}
