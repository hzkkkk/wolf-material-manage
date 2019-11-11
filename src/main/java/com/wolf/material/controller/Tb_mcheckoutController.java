package com.wolf.material.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wolf.material.pojo.Tb_mcheckout;
import com.wolf.material.pojo.UUser;
import com.wolf.material.service.Tb_materiaslsService;
import com.wolf.material.service.Tb_mcheckoutService;
import com.wolf.material.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @description:
 * @author: 江毅东
 * @createDate: 2019/10/30
 * @version: 1.0
 */
@RestController//返回数据直接显示，而不是跳转
@CrossOrigin(origins = "*",maxAge = 3600)//实现跨域注解
@RequestMapping("/Tb_mcheckout")//拦截有software的url
public class Tb_mcheckoutController {
    @Autowired
    private Tb_mcheckoutService tb_mcheckoutService;

    @Autowired
    private UUserService uUserService;

    @Autowired//自动注入service层
    private Tb_materiaslsService tb_materiaslsService;

    Tb_mcheckout temp = new Tb_mcheckout();

//    //页面访问localhost:8080/software/findAll,返回数据库软件组所有人数据
//    @RequestMapping("findAll")//拦截有findAll的url
//    public List<Tb_mcheckout> findAll() throws Exception{//抛出异常
//        List<Tb_mcheckout> tb_mcheckout = tb_mcheckoutService.findAll();//调用service类方法
//        System.out.println(tb_mcheckoutService);
//        return tb_mcheckout;//返回json数据
//    }

    @RequestMapping(value="findOne", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    //拦截有jsonInteractive的url,拦截该访问路径的json数据
    public String findOne(@RequestParam(value="iid") Integer iid,@RequestParam(value = "uid") Integer uid) throws Exception{//拦截一个key为id的json数据，并注入定义的变量
        List<Tb_mcheckout> tb_mcheckout = tb_mcheckoutService.findOne(iid);//调用service类方法
        JSONObject jsonObject = new JSONObject();
        for(Tb_mcheckout tb:tb_mcheckout)
        {
            if(tb.getIID()==iid&&tb.getUID()==uid)
            {
                System.out.println(tb.toString());
                jsonObject.put("iid",iid);
                jsonObject.put("uid",uid);
                jsonObject.put("Ostate",tb.getOstate());
                jsonObject.put("Otime",tb.getOtime().toString());
                System.out.println(jsonObject.toJSONString());
            }
        }

        return jsonObject.toJSONString();//返回json数据
    }
    //页面访问localhost:8080/software/findOne,接收网页传来的json中id属性，到数据库查询id相同的人员信息并返回
    @RequestMapping(value="TBfindAll", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    //拦截有jsonInteractive的url,拦截该访问路径的json数据
    public String findAll() throws Exception{//拦截一个key为id的json数据，并注入定义的变量
        List<Tb_mcheckout> tb_mcheckouts = tb_mcheckoutService.findAll();//调用service类方法
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray=new JSONArray();
        System.out.println(tb_mcheckouts.toString());

        if(tb_mcheckouts!=null&&!tb_mcheckouts.isEmpty())//判断该物资是否存在
        {

            for(Tb_mcheckout tb_mcheckout:tb_mcheckouts)
            {
////                System.out.println(tb_mcheckout.toString());
//               jsonObject1.put("content",tb_mcheckout);
//               // System.out.println(jsonObject1.toJSONString());
//                Map<String,JSONObject> jsonArray2 =  new HashMap<String,JSONObject>();
//                jsonArray2.put("content",tb_mcheckout);
////                jsonArray.add(jsonObject1);
                jsonArray.add(tb_mcheckout);//jsonArray添加物资信息

            }
             jsonObject.put("data",jsonArray);//封装物资信息
          //System.out.println(tb_mcheckouts.get(1).toString());
        }
        else
        {
            jsonObject.put("id", 400);
        }
//        for(Tb_mcheckout tb:tb_mcheckout)
//        {
//            if(tb.getIID()==iid&&tb.getUID()==uid)
//            {
//                System.out.println(tb.toString());
//                jsonObject.put("iid",iid);
//                jsonObject.put("uid",uid);
//                jsonObject.put("Ostate",tb.getOstate());
//                jsonObject.put("Otime",tb.getOtime().toString());
//                System.out.println(jsonObject.toJSONString());
//            }
//        }

        return jsonObject.toJSONString();//返回json数据
    }

    @RequestMapping(value = "TBAvailable", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String TBAvailableOne(@RequestParam(value="iid") Integer iid,@RequestParam(value = "uid") Integer uid)  throws Exception{
        List<UUser> uUser = uUserService.findOne(uid);//调用service类方法
        JSONObject jsonObject = new JSONObject();

        if(uUser.get(0).getUpermission().equals("user")){
            jsonObject.put("id", 400);//强制给数据
        }else{
            temp.setIID(iid);
            temp.setUID(uid);
            temp.setOstate("available");
            java.util.Date date1=new java.util.Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(dateFormat.format(date1));
            long l = Long.parseLong(dateFormat.format(date1));
            temp.setOtime(new Date(l));
            System.out.println(tb_mcheckoutService);
            if( tb_mcheckoutService.insertOne(temp)) {
                jsonObject.put("id", 200);//成功返回
            } else{
                jsonObject.put("id", 500);//失败返回
            }
        }

        return jsonObject.toJSONString();
    }



}
