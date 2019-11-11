/**
 * @Title: JsonController
 * @Description: 测试与前端的数据交互，json跨域演示
 * @author 黄彦钊
 * @date 2019/9/23
 **/
package com.wolf.material.controller;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
@RestController //实现跨域注解
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/api")
public class JsonController {

    //接收json数据，将其放入另一个json数据并返回
    @RequestMapping(value="finduser", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String finduser(@RequestParam(value="id") Integer id){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);//强制给数据
        jsonObject.put("name", "李四");//强制给数据
        System.out.println(id);
        System.out.println(jsonObject.toJSONString());
        return  jsonObject.toJSONString();
    }



}
