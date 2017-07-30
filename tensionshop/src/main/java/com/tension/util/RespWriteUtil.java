package com.tension.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应输出工具类
 */
public class RespWriteUtil {
    public static void respWrite(HttpServletResponse resp, JSONObject jsonObject) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject.toJSONString());
        writer.flush();
        writer.close();
    }
}
