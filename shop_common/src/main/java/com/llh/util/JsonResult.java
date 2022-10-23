package com.llh.util;

import springfox.documentation.spring.web.json.Json;

public class JsonResult {

    //状态码
    private Integer status;

    // 响应消息
    private String msg;

    // 响应数据
    private Object data;

    public JsonResult(){

    }

    public JsonResult(Object data){
        this.status = 200;
        this.msg = "ok";
        this.data = data;
    }


    public JsonResult(Integer statusCode, String msg, Object data) {
        this.status = statusCode;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult ok(){
        return new JsonResult(null);
    }

    public static JsonResult ok(Object data){
        return new JsonResult(data);
    }


    public static JsonResult errMsg(String msg) {
        return new JsonResult(500, msg ,null);
    }
    public static JsonResult errMap(String msg) {
        return new JsonResult(501, msg ,null);
    }
    public static JsonResult errTokenMsg(String msg) {
        return new JsonResult(502, msg ,null);
    }
    public static JsonResult errException(String msg) {
        return new JsonResult(555, msg ,null);
    }
    public static JsonResult errUser(String msg) {
        return new JsonResult(556, msg ,null);
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
}
