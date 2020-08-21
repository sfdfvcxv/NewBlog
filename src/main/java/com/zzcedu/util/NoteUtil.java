package com.zzcedu.util;

import com.alibaba.druid.util.Base64;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @Author: Evan
 * @Date: 2020/8/21 9:29
 * 工具类
 * 封装主键的生成方式UUID
 * 封装MD5加密算法
 */
public class NoteUtil {
    public static String createId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-","");
    }
    //非对称加密算法
    public static String md5(String src) throws Exception {
        //将字符串信息采用MD5处理
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(src.getBytes());
        //将MD5处理结果利用Base64转换成字符串
        String s = Base64.byteArrayToBase64(digest);
        return s;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(createId());
        System.out.println(md5("123456"));
    }
}
