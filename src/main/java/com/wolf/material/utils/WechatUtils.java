package com.wolf.material.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.parser.Token;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@RestController //实现跨域注解
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/wx")
public class WechatUtils {

    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    //获取凭证地址
    public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static String APP_ID = "正式appid";
    public static String APP_SECRET = "正式secret";

    public static String _token = "初始化";

    /*
     * 2物资增删函数需要调用 : Util-增删二维码
     */

    /*
     * 2-1物资增函数需要调用 : 增函数调用生成二维码图片的函数
     */

    /*
     * 2-2物资删函数需要调用 : 删函数调用删除二维码的函数
     */

    /*
     * 4内部调用函数 : 获取access_token,存到局部变量token
     */
    public void getToken(){
        String requestUrl = TOKEN_URL.replace("APPID", APP_ID).replace("APPSECRET", APP_SECRET);
        _token = HttpGetOtherInterface(requestUrl);
    }


    /*
     * 5内部调用函数 : http请求access_token
     */
    public String HttpGetOtherInterface(String requestUrl) {
        /**
         * 	创建Httpclient对象
         */
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        String access_token = "";

        try {
            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(requestUrl);
            // 设置请求头信息，鉴权
            httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");

// 设置配置请求参数
            RequestConfig requestConfig =
                    RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity,"UTF-8");

            JSONObject jsonObject= JSON.parseObject(result);
            access_token =jsonObject.getString("access_token");  //取出json数组中的某一个属性
            System.out.println("access_token :" + access_token);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (access_token != null || access_token.equals("")) {
            return access_token;
        } else {
            return null;
        }
    }




    /*
     * 7内部调用函数 : (String)Buffer转jpg
     *
     */

    public static void savebase64ToImage(String base64String){
        try {
            byte[] bytes1 = decoder.decodeBuffer(base64String);

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            File w2 = new File("E://1.jpg");//可以是jpg,png格式
            ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
//    本文链接：https://blog.csdn.net/m0_37739193/article/details/78619987


    /*
     * 6内部调用函数 : 用access_token,page和scene
     * 下载一个二维码的二进制文本
     */
    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request) throws Exception {
//////////////////////////////////
        ResourceBundle resource = ResourceBundle.getBundle("wechat");
        //wechat为属性文件名，放在包resource下，如果是放在src下，直接用config即可
        String iid ="2";
        String page="pages/DetailsWZ/DetailsWZ";

        APP_ID = resource.getString("appid");
        APP_SECRET = resource.getString("appsecret");
//////////////////////////////////
        getToken();
//////////////////////////////////
        String token = _token;   // 得到token


        Map<String, Object> params = new HashMap<>();
        params.put("scene", iid);  //参数
        params.put("page", page); //位置
        params.put("width", 430);
//////////////////////////////////
        CloseableHttpClient  httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;

        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+token);  // 接口
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        String body = JSON.toJSONString(params);           //必须是json模式的 post

        StringEntity entity;
        entity = new StringEntity(body);
        entity.setContentType("*/*");

        httpPost.setEntity(entity);
        //调用接口
        response = httpClient.execute(httpPost);
        //请求参数,实现post
        InputStream inputStream = response.getEntity().getContent();
        String name = iid + ".png";
        saveToImgByInputStream(inputStream,"E:\\",name);  //保存图片
//        savebase64ToImage();
    }


    /**
     * 将二进制转换成文件保存
     * @param instreams 二进制流
     * @param imgPath 图片的保存路径
     * @param imgName 图片的名称
     * @return
     *      1：保存正常
     *      0：保存失败
     */
    public static int saveToImgByInputStream(InputStream instreams,String imgPath,String imgName){
        int stateInt = 1;
        if(instreams != null){
            try {
                File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
                FileOutputStream fos=new FileOutputStream(file);
                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = instreams.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;
    }


    /**
     * IO流保存图片
     * @param instreams
     * @param imagePath
     * @param fileName
     * @return
     */
    public static boolean uploadImages( InputStream instreams,String imagePath,String fileName) {
        File f = new File(imagePath);
        f.setWritable(true, false);
        boolean flag = false;
        try {
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File file = new File(imagePath,fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                try {
                    // 创建新文件
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("创建新文件时出现了错误。。。");
                    e.printStackTrace();
                }
            }
            OutputStream os = new FileOutputStream(imagePath+File.separator+fileName);
            // 开始读取
            while ((len = instreams.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            instreams.close();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }
//    版权声明：本文为CSDN博主「Architect_csdn」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/Architect_CSDN/article/details/98874379
}