/**
 * @Title: SoftwareController
 * @Description: 软件组成员表与前端交换数据处
 * @author 黄彦钊
 * @date 2019/9/23
 **/
package com.wolf.material.controller;
import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.collections.MappingChange;
import com.wolf.material.pojo.SoftwareInfo;
import com.wolf.material.service.SoftwareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.sql.Date;
import java.util.List;
@RestController//返回数据直接显示，而不是跳转
@CrossOrigin(origins = "*",maxAge = 3600)//实现跨域注解
@RequestMapping("/software")//拦截有software的url
public class SoftwareController {

    @Autowired//自动注入service层
    private SoftwareInfoService softwareInfoService;

    //页面访问localhost:8080/software/findAll,返回数据库软件组所有人数据
    @RequestMapping("findAll")//拦截有findAll的url
    public List<SoftwareInfo> findAll() throws Exception{//抛出异常
        List<SoftwareInfo> softwareInfo = softwareInfoService.findAll();//调用service类方法
        System.out.println(softwareInfo);
        return softwareInfo;//返回json数据
    }
    
    //页面访问localhost:8080/software/findOne,接收网页传来的json中id属性，到数据库查询id相同的人员信息并返回
    @RequestMapping(value="findOne", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    //拦截有jsonInteractive的url,拦截该访问路径的json数据
    public List<SoftwareInfo> findOne(@RequestParam(value="id") Integer id) throws Exception{//拦截一个key为id的json数据，并注入定义的变量
        List<SoftwareInfo> softwareInfo = softwareInfoService.findOne(id);//调用service类方法
        return softwareInfo;//返回json数据
    }


}
