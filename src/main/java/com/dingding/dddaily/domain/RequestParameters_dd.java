package com.dingding.dddaily.domain;

import java.util.List;

public class RequestParameters_dd {
    //API接口名称。https://oapi.dingtalk.com/checkin/record
    private String apiMethod;
    //钉钉提供的授权Token
    private String session;
   // 时间戳，
    private String timestamp;
    //		响应格式。默认为xml格式，可选值：xml，json。
    private String format;
    //	API协议版本，可选值：2.0。
    private String v;
   // 合作伙伴身份标识。
    private String partner_id;
    //是否采用精简JSON返回格式，仅当format=json时有效，默认值为：false。
    private String simplify;


//群组id
    private String open_comversation;
   // 群成员列表偏移量
    private Long offset;
    //本次请求获取群成员的大小，最大为100
    private Long count;
//查询起始时间
private Long start_time;
//	查询截止时间
private Long end_time;
//要查询的模板名称
private String template_name;
//员工的userid
private String userid;
//用户id集合
private List<String>user_id_list;
//	查询游标，初始传入0，后续从上一次的返回值中获取
private Long cursor;
//	每页数据量
private Long size;


    //部门id（1 表示根部门）str
private String department_id;
//部门id Long
private Long dep_id;

    public Long getDep_id() {
        return dep_id;
    }

    public void setDep_id(Long dep_id) {
        this.dep_id = dep_id;
    }

    //部门id集合
private List<Long>dep_id_list;


//asc 为正序
//desc 为倒序
private String order;
//访问get /post...
private String httpMethodType;

    public List <String> getUser_id_list() {
        return user_id_list;
    }

    public void setUser_id_list(List <String> user_id_list) {
        this.user_id_list = user_id_list;
    }

    public List <Long> getDep_id_list() {
        return dep_id_list;
    }

    public void setDep_id_list(List <Long> dep_id_list) {
        this.dep_id_list = dep_id_list;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String getSimplify() {
        return simplify;
    }

    public void setSimplify(String simplify) {
        this.simplify = simplify;
    }

    public String getOpen_comversation() {
        return open_comversation;
    }

    public void setOpen_comversation(String open_comversation) {
        this.open_comversation = open_comversation;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
        if (offset<=0){
            this.offset=0L;
        }
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
        if (count<=10){
            this.count = 10L;
        }
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Long end_time) {
        this.end_time = end_time;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Long getCursor() {
        return cursor;
    }

    public void setCursor(Long cursor) {
        this.cursor = cursor;
        if (cursor<=0){
            this.cursor=0L;
        }
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
        if (size<=0){
            this.size=10L;
        }
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getHttpMethodType() {
        return httpMethodType;
    }

    public void setHttpMethodType(String httpMethodType) {
        this.httpMethodType = httpMethodType;
    }
}
