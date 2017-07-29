package com.tension.util;

import java.util.Random;

/**
 * 生成商品ID工具类
 */
public class IDUtil {
    public static long getItemId(){
        long currentMillis = System.currentTimeMillis();
        Random random = new Random();
        int end = random.nextInt(99);
        //end是个位数时，十位补0
        String str = currentMillis + String.format("%02d",end);
        long id = new Long(str);
        return id;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++){
            System.out.println(getItemId());
        }
    }
}
