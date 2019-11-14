package com.wolf.material.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//
//@Component
//public class TimedTask {
//    /*
//     * 定时服务 : 定时获取access_token
//     */
//    //@Scheduled(cron="0 0 12 * * ?")//每天12点跑
//    //@Scheduled(cron="0 0/5 * * * ?")//每5分钟    0 0 */1 * * ?
//    @Scheduled(cron=" 0 0 */1 * * ?")//每个小时跑一次
//    public void TaskJob(){
//        WechatUtils.token = WechatUtils.getToken(WechatUtils.APP_ID, WechatUtils.APP_SECRET);
//        System.out.println("当前access_token值------->" + WechatUtils.token.getAccessToken());
//        System.out.println("有效期---------->[" + WechatUtils.token.getExpiresIn()+"]秒");
//    }
//}