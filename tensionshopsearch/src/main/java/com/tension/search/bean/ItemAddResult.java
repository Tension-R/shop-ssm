package com.tension.search.bean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * 添加商品后的响应结构
 */
public class ItemAddResult {

    //定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //响应状态
    private Integer status;

    private String msg;

    private Object data;


    public ItemAddResult(Integer status, String message, Object data) {
        this.status = status;
        this.msg = message;
        this.data = data;
    }

    public ItemAddResult() {
    }

    public ItemAddResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }


    public static ItemAddResult build(Integer status, String message, Object data){
        return new ItemAddResult(status,message,data);
    }

    public static ItemAddResult build(Integer status, String message){
        return new ItemAddResult(status,message,null);
    }

    public static ItemAddResult ok(Object data){
        return new ItemAddResult(data);
    }

    public static ItemAddResult ok(){
        return new ItemAddResult(null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将JSON结果集转化为ItemAddResult对象
     * @param jsonData
     * @param clazz
     * @return
     */
    public static ItemAddResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ItemAddResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 没有object对象的转化
     */
    public static ItemAddResult format(String json){
        try {
            return MAPPER.readValue(json,ItemAddResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     */
    public static ItemAddResult formatToList(String jsonData, Class<?> clazz){
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0){
                obj = MAPPER.readValue(data.traverse(),MAPPER.getTypeFactory().constructCollectionType(List.class,clazz));

            }
            return build(jsonNode.get("status").intValue(),jsonNode.get("msg").asText(),obj);
        } catch (Exception e) {
            return null;
        }
    }
}
