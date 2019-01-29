package com.dingding.dddaily.controller;

import com.dingding.dddaily.common.ToolUtil;
import com.dingding.dddaily.domain.*;
import com.dingding.dddaily.properties.ProperConfig;
import com.dingding.dddaily.service.DailyService;
import com.taobao.api.ApiException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping(value = "/daily")
public class DailyController {

    @Autowired
    private  ToolUtil toolUtil;

    @Autowired
    private DailyService dailyService;

    @Autowired
    private ProperConfig properConfig;




    @GetMapping(value = "/countdailysview")
    public String dailys() throws JSONException, ApiException {
      return "dd_daily.html";
    }



    /**
     * 上个月日志
     * @throws JSONException
     * @throws ApiException
     */
    @ResponseBody
    @GetMapping(value = "/beformonthdailys")
   public   List<DailyCount>  getBeforMonthDailys() throws JSONException, ApiException {
        getAllCompanyUsersDailyByUserId();
     List<String>userid=   getAllUserId();
    Map<String,String>userIdAndName=dailyService.getAllMapUserIdAndUserName(userid);
      String beforMon=toolUtil.getBeforAcountMonth();//2014-12
      String[] strs=beforMon.split("-");
      int workDays=toolUtil.countWorkDay(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]));
      List<DailyCount> dailyList=new ArrayList <>();
      if (userIdAndName.size()>0)
      {
          userIdAndName.forEach((k,v)->{
              int mint=dailyService.getDailyByCreatorTimeContain(beforMon,k);
              DailyCount dailyCount=new DailyCount();
              dailyCount.setEmpName(v);
              dailyCount.setBeforMonth(Integer.parseInt(strs[1]));
              dailyCount.setAccountYear(Integer.parseInt(strs[0]));
              dailyCount.setYjsl(workDays);
              dailyCount.setYtjsl(mint);
              dailyCount.setWtjsl(workDays-mint);
              dailyList.add(dailyCount);
          });

      }

       return  dailyList;
   }

    /**
     *钉钉部门列表
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/deplist")
    public  List<Long>  depList() throws JSONException, ApiException {
        RequestParameters_dd equestParameters_dd=new RequestParameters_dd();
        equestParameters_dd.setHttpMethodType("GET");
        //equestParameters_dd.setDepartment_id("1");
      List<Long> depIds=  dailyService.getDepartmentalIds(equestParameters_dd);
      if (depIds.size()>0){
return depIds;
      }
return null;

    }

    /**
     * 所有用户id(全公司)
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/alluserid")
    public List<String>getAllUserId() throws JSONException, ApiException {
       List<Long>deps= depList();
        RequestParameters_dd equestParameters_dd=new RequestParameters_dd();
        equestParameters_dd.setHttpMethodType("GET");
        equestParameters_dd.setDep_id_list(deps);
        equestParameters_dd.setOffset(0L);
        equestParameters_dd.setSize(10L);
     List<String>useridstrs=  dailyService.getAllDepUserIds(equestParameters_dd);
     return  useridstrs;
    }



    /**
     * 一个部门时原所有用户id
     * @return
     * @throws JSONException
     * @throws ApiException
     */
    @ResponseBody
    @GetMapping(value = "/depusersid")
    public   Map<String,String>getDepUserId() throws JSONException, ApiException {
        RequestParameters_dd equestParameters_dd=new RequestParameters_dd();
        equestParameters_dd.setHttpMethodType("GET");
        equestParameters_dd.setDep_id(1L);
        equestParameters_dd.setOffset(0L);
        equestParameters_dd.setSize(100000L);
       Map<String,String>useridstrs=  dailyService.getDepUserIds(equestParameters_dd);
        return useridstrs;
    }

    /**
     * 返回全员的日志列表
     * @return
     * @throws JSONException
     * @throws ApiException
     */
    @ResponseBody
    @GetMapping(value = "/allusersdaily")
  public String getAllCompanyUsersDailyByUserId() throws JSONException, ApiException {
        RequestParameters_dd requestParametersDd=new RequestParameters_dd();
 try {
     requestParametersDd.setHttpMethodType("GET");
     List<Long> depIds=  dailyService.getDepartmentalIds(requestParametersDd);
     requestParametersDd.setDep_id_list(depIds);
     requestParametersDd.setOffset(0L);
     requestParametersDd.setSize(100000L);
     List<String>userids=dailyService.allCompanyUsersId(requestParametersDd);
     requestParametersDd.setStart_time(System.currentTimeMillis()-TimeUnit.DAYS.toMillis(properConfig.getDailyDaysBefor()));
     requestParametersDd.setEnd_time(System.currentTimeMillis());
     requestParametersDd.setCursor(0L);
    requestParametersDd.setUser_id_list(userids);
    int mm=dailyService.getUserDailyByUserId(requestParametersDd);
    return "更新完成！！！";
 }catch (Exception e ){}
        return null;
  }




    /**
     * 获取部门用户签到记录
     * @return
     * @throws JSONException
     * @throws ApiException
     */
    @ResponseBody
    @GetMapping(value = "/depusersign")
    public Object[] departmentalUsersSign() throws JSONException, ApiException {
        RequestParameters_dd requestParameters_dd=new RequestParameters_dd();
requestParameters_dd.setDepartment_id("1");
requestParameters_dd.setStart_time(System.currentTimeMillis()-TimeUnit.DAYS.toMillis(1));
        requestParameters_dd.setEnd_time(System.currentTimeMillis());
        requestParameters_dd.setOffset(0L);
        requestParameters_dd.setOrder("asc");
        requestParameters_dd.setSize(100000L);
        requestParameters_dd.setHttpMethodType("GET");
        dailyService.getDepartmentalUsersToSign(requestParameters_dd);
      Object obj=  dailyService.getDepartmentalUsersToSign(requestParameters_dd).getData();
        return dailyService.getDepartmentalUsersToSign(requestParameters_dd).getData().toArray();
    }



    @ResponseBody
    @GetMapping(value = "/test")
    public  String test(){


        return "123456";
    }

}
