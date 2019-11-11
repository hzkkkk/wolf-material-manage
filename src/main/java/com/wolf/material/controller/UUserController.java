package com.wolf.material.controller;


import com.wolf.material.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:用户控制层
 * @author: 江毅东
 * @createDate: 2019/10/31
 * @version: 1.0
 */
@RestController//返回数据直接显示，而不是跳转
@CrossOrigin(origins = "*",maxAge = 3600)//实现跨域注解

/**
 * @description: 未知接口
 * @author hzk
 */
@RequestMapping("/UUser")//拦截有software的url
public class UUserController {
    @Autowired
    private UUserService uUserService;

    @RequestMapping(value = "TBAvailable", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String TBAvailableOne(@RequestParam(value="iid") Integer iid, @RequestParam(value = "uid") Integer uid)  throws Exception{
      //  List<UUser> uUsers = uUserService.findOne(uid);//调用service类方法

        return null;
    }

}
