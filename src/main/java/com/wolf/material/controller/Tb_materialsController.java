package com.wolf.material.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wolf.material.pojo.*;
import com.wolf.material.service.McategoryService;
import com.wolf.material.service.MrevisionsService;
import com.wolf.material.service.Tb_materialsService;
import com.wolf.material.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @description: 物资管理控制层
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
    private McategoryService mcategoryService;

    @Autowired
    private MrevisionsService mrevisionsService;

    @Autowired
    private UUserService uUserService;


    /**
     * @description: 修改物资-(增加)
     * @author cola
     */
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


    /**
     * @description: 修改物资-(删除)
     * @author cola
     */
    @RequestMapping(value="TBMdeleteOne", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteOne(@RequestBody Map<String,Object>data) throws Exception
    {
        Mrevisions mrevisions=new Mrevisions();
        JSONObject jsonObject=new JSONObject(data);
        int uid=jsonObject.getInteger("uid");
        int Iid=jsonObject.getInteger("iid");
        System.out.println(uid+"   "+Iid);
        List<UUser> uUser = uUserService.findOne(uid);

        if(uUser.get(0).getUpermission().equals("user")){
            jsonObject.put("id", 400);//强制给数据
        }else {
            boolean result = tb_materialsService.deleteOne(Iid);
            if (result) {
                //191111 cola在物资修改表那边加一条记录
                mrevisions.setIID(Iid);
                mrevisions.setUID(uid);
                mrevisions.setMstate("asdjasjdlasj");//强制给数据
                java.util.Date date1=new java.util.Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(dateFormat.format(date1));
                mrevisions.setMtime(dateFormat.format(date1));
                boolean save_result= mrevisionsService.insertOne(mrevisions);//增加一条物资删除记录
                System.out.println(save_result);
                jsonObject.put("id", 200);//成功返回
            } else {
                jsonObject.put("id", 500);//失败返回
            }
        }
     return jsonObject.toJSONString();
    }


    /**
     * @description: 查询物资-(查询一个)
     * @author cola
     */
        @RequestMapping(value="/select_findCategory", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String findOneCategory(@RequestParam(value="cid") Integer cid,@RequestParam(value = "uid") Integer uid) throws Exception{//拦截一个key为id的json数据，并注入定义的变量
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        List<Mcategory>mcategoryList=mcategoryService.findAll();
        List<Tb_materials>materialsList=tb_materialsService.findAll();
        List<UUser> uUser = uUserService.findOne(uid);
        int flag=0;
        for(Mcategory mcategory:mcategoryList)//查询物资种类是否存在
        {
            if(mcategory.getCid()==cid)
            {
                flag=1;
            }
        }
        //判断用户是否为管理员以及物资是否存在
        if(uUser.get(0).getUpermission().equals("user")){
            jsonObject.put("id", 400);//强制给数据
        }
        else if(flag==0)
        {
            jsonObject.put("id",500);
        }
        else{
            //按种类查询物资信息
            for(Tb_materials materials:materialsList){
                if(materials.getCid()==cid){
                    jsonArray.add(materials);
                }
            }
            jsonObject.put("data",jsonArray);
        }


        return jsonObject.toJSONString();//返回json数据
    }



    //查询一个
    @RequestMapping(value="/findOne", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    //拦截有jsonInteractive的url,拦截该访问路径的json数据
    public String findOne(@RequestParam(value="iid") Integer iid,@RequestParam(value = "uid") Integer uid) throws Exception {//拦截一个key为id的json数据，并注入定义的变量
        List<Tb_materials> tb_materials = tb_materialsService.findOne(iid);//调用service类方法
        JSONObject jsonObject = new JSONObject();
        if(tb_materials.isEmpty()){
            jsonObject.put("id",500);
        }
        for(Tb_materials tb:tb_materials) {
            if(tb.getIid()==iid) {
                //System.out.println(tb.toString());
                jsonObject.put("iid",iid);
                jsonObject.put("Iname",tb.getIname());
                jsonObject.put("CID",tb.getCid());
                jsonObject.put("Inote",tb.getInote());
                jsonObject.put("Istate",tb.getIstate());
                System.out.println(jsonObject.toJSONString());

            }

        }

        return jsonObject.toJSONString();//返回json数据
    }


}
