package com.wolf.material.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @description: 物资表
 * @author: 江毅东
 * @createDate: 2019/10/31
 * @version: 1.0
 */
@Data
public class Tb_materials {
    Integer Iid; //物资id
    String Iname; //物资名称
    Integer Cid; //物资种类id
    String Inote; //物资解释
    String Istate; //物资状态
    public void Transfer(JSONObject object)
    {
        //this.setIid(object.getInteger("iid"));
        this.setIname(object.getString("Iname"));
        this.setCid(object.getInteger("Cid"));
        this.setInote(object.getString("Inote"));
        this.setIstate(object.getString("Istate"));
    }
}
