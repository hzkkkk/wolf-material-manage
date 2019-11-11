package com.wolf.material.controller;

import com.alibaba.fastjson.JSONObject;

import com.wolf.material.pojo.Tb_materials;
import com.wolf.material.pojo.UUser;
import com.wolf.material.service.Tb_materialsService;
import com.wolf.material.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 吴科男
 * @createDate: 2019/11/2
 * @version: 1.0
 */
@RestController//返回数据直接显示，而不是跳转
@CrossOrigin(origins = "*",maxAge = 3600)//实现跨域注解
@RequestMapping("/Mmaterials")//拦截有software的url
public class Tb_materialsController {
    @Autowired
    private Tb_materialsService tb_materialsService;

    @Autowired
    private UUserService uUserService;
    @RequestMapping(value="TBMinsertOne", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    //拦截有jsonInteractive的url,拦截该访问路径的json数据
    public String insertOne(@RequestBody Map<String, Object> data) throws Exception{//拦截一个key为id的json数据，并注入定义的变量
        Tb_materials tb_material = new Tb_materials();
        JSONObject jsonObject=new JSONObject(data);
        tb_material.Transfer(jsonObject);
        boolean result=tb_materialsService.insertOne(tb_material);
        if(result) {
            jsonObject.put("id", 200);//成功返回
        } else{
            jsonObject.put("id", 500);//失败返回
        }
        System.out.println(tb_material.toString());
        return jsonObject.toJSONString(); //返回json数据
    }
    @RequestMapping(value="TBMdeleteOne", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String deleteOne(@RequestParam(value="iid") Integer Iid,@RequestParam(value="uid") Integer uid) throws Exception
    {
        List<UUser> uUser = uUserService.findOne(uid);
        JSONObject jsonObject=new JSONObject();
        if(uUser.get(0).getUpermission().equals("user")){
            jsonObject.put("id", 400);//强制给数据
        }else {
            boolean result = tb_materialsService.deleteOne(Iid);
            if (result) {
                jsonObject.put("id", 200);//成功返回
            } else {
                jsonObject.put("id", 500);//失败返回
            }
        }
     return jsonObject.toJSONString();
    }

}
