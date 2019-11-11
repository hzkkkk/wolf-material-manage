package com.wolf.material.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Date;

/**
 * @description: 借还表
 * @author: 江毅东
 * @createDate: 2019/10/29
 * @version: 1.0
 */
@Data
public class Tb_mcheckout {
    private Integer OID; //借还id
    private Integer IID; //物资id
    private Integer UID; //用户id
    private String Ostate; //借还状态
    private  Date Otime;  //借还时间

    public void Transfer(JSONObject object){
        this.setIID(object.getInteger("I" +
                "ID"));
        this.setUID(object.getInteger("UID"));
        this.setOstate(object.getString("Ostate"));
        this.setOtime(object.getSqlDate("Otime"));
    }


}
