package com.dingding.dddaily.common;



import com.dingding.dddaily.properties.ProperConfig;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
public class ToolUtil {

    private static Logger logger = LoggerFactory.getLogger(ToolUtil.class);
    @Autowired
    private  ProperConfig properConfig;

    /**
     * 通过平台id和密钥生成accessToken
     * @return
     * https://oapi.dingtalk.com/gettoken?corpid=id&corpsecret=secrect
     */
    public String getAccessToken() throws JSONException {
        //String s=  HttpHandler.httpsRequest("https://kyfw.12306.cn/","GET",null);
        String accessToken=null;
        String url=properConfig.getAccessTokenUrl()+"?corpid="+properConfig.getCorpId()+"&corpsecret="+properConfig.getCorpSecret();
        String accessTokenData = HttpHandler.httpsRequest(url,"GET",null);
        if (accessTokenData!=null&&accessTokenData.length()>0&&accessTokenData!=""){
            JSONObject obj = new JSONObject(accessTokenData);
            accessToken= (String) obj.get("access_token");
        }
        return accessToken;
    }


    /**
     * 获取当前月的工作日天数
     * @return
     */
    public  int getWorkDaysAmount(){
        Map<String, Integer> map = getMonthInfo(Calendar.getInstance());
        return map.get("workDaysAmount");
    }

    //上一个月
    public String  getBeforAcountMonth(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1); //当前月的上个月  （-1改为1的话，为取当前月  的下个月）
        return format.format( cal.getTime());//2014-12


    }

    /**
     * 获取指定年月有多少个工作日）
     *
     * @param year
     * @param month
     * @return
     */
    public  int countWorkDay(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        // 月份是从0开始计算，所以需要减1
        c.set(Calendar.MONTH, month - 1);

        // 当月最后一天的日期
        int max = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 开始日期为1号
        int start = 1;
        // 计数
        int count = 0;
        while (start <= max) {
            c.set(Calendar.DAY_OF_MONTH, start);
            if (isWorkDay(c)) {
                count++;
            }
            start++;
        }
        return count;
    }

    // 判断是否工作日（未排除法定节假日，由于涉及到农历节日，处理很麻烦）
    public  boolean isWorkDay(Calendar c) {
        // 获取星期,1~7,其中1代表星期日，2代表星期一 ... 7代表星期六
        int week = c.get(Calendar.DAY_OF_WEEK);
        // 不是周六和周日的都认为是工作日
        return week != Calendar.SUNDAY && week != Calendar.SATURDAY;
    }


    /**
     * 计算当前月有多少自然日、有多少工作日、有几周
     */
    private static Map getMonthInfo(Calendar calendar) {
        Map<Object, Integer> map = new HashMap<>();
        int workDays = 0;
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        try {
            calendar.set(Calendar.DATE, 1);//从每月1号开始
            for (int i = 0; i < days; i++) {
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
                    workDays++;
                }
                calendar.add(Calendar.DATE, 1);
            }
            map.put("workDaysAmount", workDays);//工作日
            map.put("year", calendar.get(Calendar.YEAR));//实时年份
            map.put("month", calendar.get(Calendar.MONTH));//实时月份
            map.put("daysAmount", days);//自然日
            map.put("weeksAmount", calendar.getActualMaximum(Calendar.WEEK_OF_MONTH));//周
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
