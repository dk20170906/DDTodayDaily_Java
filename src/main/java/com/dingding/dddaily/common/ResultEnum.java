package com.dingding.dddaily.common;

public enum ResultEnum {
    UNKONW_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    ERROR(1,"失败"),
    ACCESSTOKEN_ERROR(400,"获取企业授权的access_token失败"),
    DAILYTYPE_(101,"日报"),
    WEEKLYPUBLICATIONA(102,"周报"),
    DAI_DATE(304,"日志日期"),
    DAI_WORK_PLACE(305,"办公地点"),
    DAI_LOCATION(306,"所在地"),
    DAI_FINISH_WORK(300,"今日完成工作"),
    DAI_UNFINSHED_WORK(301,"未完成工作"),
    DAI_NEED_HELP_WORK(302,"需协调工作"),
    DAI_TOMORROW_WORK(303,"明日工作"),


   ;


    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
