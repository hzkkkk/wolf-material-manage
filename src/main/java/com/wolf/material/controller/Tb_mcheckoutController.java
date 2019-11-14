package com.wolf.material.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wolf.material.pojo.Tb_mcheckout;
import com.wolf.material.pojo.UUser;
import com.wolf.material.service.Tb_materialsService;
import com.wolf.material.service.Tb_mcheckoutService;
import com.wolf.material.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @description:借还控制层
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
    @Autowired
    private Tb_materialsService tb_materialsService;

    Tb_mcheckout temp = new Tb_mcheckout();

    /**
     * @description: 借物资
     * @author ZeroYale
     */
    //页面访问localhost:8080/software/findOne,接收网页传来的json中id属性，到数据库查询id相同的人员信息并返回
    @RequestMapping(value="/borrow", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    //拦截有jsonInteractive的url,拦截该访问路径的json数据
    public String TBBorroweOne(@RequestParam(value="iid") Integer iid,@RequestParam(value = "uid") Integer uid) throws Exception{//拦截一个key为id的json数据，并注入定义的变量
        List<Tb_mcheckout> tb_mcheckout = tb_mcheckoutService.findOne(iid);//调用service类方法
        JSONObject jsonObject = new JSONObject();

        //这里后期需修改
        if(tb_mcheckout.get(0).getOstate().equals("borrowed")){
            jsonObject.put("id", 400);//强制给数据
        }else {
            tb_materialsService.updateBorrow(iid);
            temp.setIID(iid);
            temp.setUID(uid);
            temp.setOstate("borrowed");
            temp.setOtime(new Date());
            System.out.println(tb_mcheckoutService.insertOne(temp));
            if( tb_mcheckoutService.insertOne(temp)) {
                jsonObject.put("id", 200);//成功返回
            } else{
                jsonObject.put("id", 500);//失败返回
            }
        }
        return jsonObject.toJSONString();//返回json数据
    }


    /**
     * @description: 还物资
     * @author ZeroYale
     */
    @RequestMapping(value = "TBAvailable", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String TBAvailableOne(@RequestParam(value="iid") Integer iid,@RequestParam(value = "uid") Integer uid)  throws Exception{
        List<UUser> uUser = uUserService.findOne(uid);//调用service类方法
        JSONObject jsonObject = new JSONObject();

        if(uUser.get(0).getUpermission().equals("user")){
            jsonObject.put("id", 400);//强制给数据
        }else{
            tb_materialsService.updateAvailable(iid);
            temp.setIID(iid);
            temp.setUID(uid);
            temp.setOstate("available");
            temp.setOtime(new Date());
            System.out.println(tb_mcheckoutService);
            if( tb_mcheckoutService.insertOne(temp)) {
                jsonObject.put("id", 200);//成功返回
            } else{
                jsonObject.put("id", 500);//失败返回
            }
        }

        return jsonObject.toJSONString();
    }




    //查询一个
    @RequestMapping(value="/findOne", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    //拦截有jsonInteractive的url,拦截该访问路径的json数据
    public String findOne(@RequestParam(value="iid") Integer iid,@RequestParam(value = "uid") Integer uid) throws Exception {//拦截一个key为id的json数据，并注入定义的变量
        List<Tb_mcheckout> tb_mcheckout = tb_mcheckoutService.findOne(iid);//调用service类方法
        JSONObject jsonObject = new JSONObject();
        for(Tb_mcheckout tb:tb_mcheckout) {
            if(tb.getIID()==iid&&tb.getUID()==uid) {
                //System.out.println(tb.toString());
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
    @RequestMapping(value="/TBfindAll", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    //拦截有jsonInteractive的url,拦截该访问路径的json数据
    public String findAll() throws Exception{//拦截一个key为id的json数据，并注入定义的变量
        List<Tb_mcheckout> tb_mcheckouts = tb_mcheckoutService.findAll();//调用service类方法
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        System.out.println(tb_mcheckouts.toString());

        if(tb_mcheckouts!=null&&!tb_mcheckouts.isEmpty()) {
            for(Tb_mcheckout tb_mcheckout:tb_mcheckouts) {
                jsonArray.add(tb_mcheckout);

            }
            jsonObject.put("data",jsonArray);
            //System.out.println(tb_mcheckouts.get(1).toString());
        } else {
            jsonObject.put("id", 400);
        }


        return jsonObject.toJSONString();//返回json数据
    }


}
