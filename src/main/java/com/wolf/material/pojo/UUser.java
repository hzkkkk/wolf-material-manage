package com.wolf.material.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @description:用户
 * @author: 江毅东
 * @createDate: 2019/10/31
 * @version: 1.0
 */
@Data
public class UUser {
    Integer Uid; //用户id
    String Uname; //名称
    String Usex;  //性别
    String Upassword;  //密码
    String Uapartment;  //部门
    String Upermission;  //权限
    String Uphone;  //手机号

    public void Transfer(JSONObject object)
    {
        //this.setSw_id(object.getInteger("uid"));
        this.setUname(object.getString("name"));
        this.setUsex(object.getString("sex"));
        this.setUpassword(object.getString("password"));
        this.setUapartment(object.getString("apartment"));
        this.setUpermission(object.getString("permission"));
        this.setUphone(object.getString("phone"));

    }

}
