package org.qingqiao.vhr.utils;


public class ResponseBean {
    private Integer status;//返回的状态码
    private String msg;//响应给客户端的消息
    private Object obj;//登录的用户信息,查询出来的数据


    //私有化构造方法
    private ResponseBean(){

    }
    //声明一个方法
    //请求成功时的方法
    public static ResponseBean ok(String msg){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus(200);
        responseBean.setMsg(msg);
        return responseBean;
    }
    public static ResponseBean ok(String msg,Object obj){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus(200);
        responseBean.setMsg(msg);
        responseBean.setObj(obj);
        return responseBean;
    }
    public static ResponseBean error(String msg){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus(500);
        responseBean.setMsg(msg);
        return responseBean;
    }

    public static ResponseBean error(String msg,Object obj){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus(500);
        responseBean.setMsg(msg);
        responseBean.setObj(obj);
        return responseBean;
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

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
