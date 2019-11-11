package com.wolf.material.pojo;
/**
 * @Title: Mrevisions
 * @Description: 物资删除记录数据库实体类
 * @author 吴科男/吴志雄
 * @date 2019/11/08
 **/
import lombok.Data;

@Data
public class Mrevisions {
    private int MmID;
    private int IID;
    private int UID;
    private String Mstate;
    private String Mtime;
    private String Mlog;

}
