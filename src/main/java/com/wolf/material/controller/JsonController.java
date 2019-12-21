/**
 * @Title: JsonController
 * @Description: 测试与前端的数据交互，json跨域演示
 * @author 黄彦钊
 * @date 2019/9/23
 **/
package com.wolf.material.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//实现跨域注解
@Controller
@RequestMapping("/")
public class JsonController {

    //跳转界面
    @RequestMapping("OpenConsole")
    public String AdminConsole(){
        return "index";
    }


}
