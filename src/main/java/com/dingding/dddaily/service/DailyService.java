package com.dingding.dddaily.service;

import com.dingding.dddaily.common.ResultEnum;
import com.dingding.dddaily.common.ToolUtil;
import com.dingding.dddaily.domain.Daily;
import com.dingding.dddaily.domain.Department;
import com.dingding.dddaily.domain.Employee;
import com.dingding.dddaily.domain.RequestParameters_dd;
import com.dingding.dddaily.properties.ProperConfig;
import com.dingding.dddaily.repository.DailyRepository;
import com.dingding.dddaily.repository.DepRepository;
import com.dingding.dddaily.repository.EmployeeRepository;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RestController
public class DailyService {

    @Autowired
    private ToolUtil toolUtil;

    @Autowired
    private ProperConfig properConfig;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepRepository depRepository;

    @Autowired
    private DailyRepository dailyRepository;


    /**
     * 获取键值Userid Username
     * @param userId
     * @return
     * @throws JSONException
     * @throws ApiException
     */
    public  Map<String,String> getUserIdAndUserNameMap(String userId) throws JSONException, ApiException {
        Map<String,String> userMap=new HashMap <>();
      if (userId!=null)
      {
          String val = employeeRepository.findByEmpUserId(userId).getEmpName();
          if (userMap.keySet().contains(userId)==false){
              userMap.put(userId,val);
          }
      }
        return userMap;
    }


    public Map<String,String> getAllMapUserIdAndUserName(List<String>userids){
       List<Employee>emps= employeeRepository.findAllByEmpUserIdIn(userids);
       Map<String,String>userIdAndName=new HashMap <>();
       if (emps.size()>0){
           emps.forEach(u->{
               if (userIdAndName.containsKey(u.getEmpUserId())==false){
                   userIdAndName.put(u.getEmpUserId(),u.getEmpName());
               }
           });
       }
       return userIdAndName;
    }

    /**
     * 当月已提交日志数量
     * @param dateStr 日期前缀2018-06
     * @param userId
     * @return
     */
    public int getDailyByCreatorTimeContain(String dateStr,String userId){
       return dailyRepository.countAllByDaiDateStartingWithAndDaiCreatorId(dateStr,userId);
    }

    /**
     * 获取部门并插入部门数表返回部门ID集合
     * @return
     */
    public List<Long>  getDepartmentalIds(RequestParameters_dd requestParameters_dd) throws JSONException, ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setId(requestParameters_dd.getDepartment_id());
        request.setHttpMethod(requestParameters_dd.getHttpMethodType());
        OapiDepartmentListResponse response = client.execute(request, toolUtil.getAccessToken());
        List<Long>depIds=new ArrayList <>();
        if (    response!=null){
            response.getDepartment().forEach(u->{
                Long depId=u.getId();

                depIds.add(u.getId());
                boolean bool=depRepository.existsByDepId(depId);
                if (bool==false) {
                    Department dep = new Department();
                    dep.setDepId(u.getId());
                    dep.setDepName(u.getName());
                    dep.setParentId(u.getParentid());
                    dep.setCreateDeptGroup(u.getCreateDeptGroup());
                    dep.setAutoAddUser(u.getAutoAddUser());
                    depRepository.save(dep);
                }
            });
        }
        return depIds;
    }


    /**
     * 获取一个部门用户并插入员工表返回用户id集合
     * @param requestParameters_dd
     * @return
     */
    public    Map<String,String> getDepUserIds(RequestParameters_dd requestParameters_dd) throws JSONException, ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/simplelist");
        OapiUserListRequest request = new OapiUserListRequest();
        request.setDepartmentId( requestParameters_dd.getDep_id());
        request.setOffset(requestParameters_dd.getOffset());
        request.setSize(requestParameters_dd.getSize());
        request.setHttpMethod(requestParameters_dd.getHttpMethodType());
        OapiUserListResponse  response = client.execute(request, toolUtil.getAccessToken());


        Map<String,String>userMap=new HashMap <>();
        if (response!=null&&response.getUserlist()!=null){
            try {
                List <OapiUserListResponse.Userlist> obj=response.getUserlist();
                response.getUserlist().forEach(u-> {
                    String empId=u.getUserid();
                    boolean bool=employeeRepository.existsByEmpUserId(empId);
                    userMap.put(u.getUserid(),u.getName());
                    if (bool==false) {
                        Employee employee = new Employee();
                        employee.setEmpUserId(u.getUserid());
                        employee.setEmpName(u.getName());
                        employee.setEmpUnionid(u.getUnionid());
                        employee.setEmpMobile(u.getMobile());
                        employee.setEmpTel(u.getTel());
                        employee.setEmpWorkPlace(u.getWorkPlace());
                        employee.setEmpdepUserRemark(u.getRemark());
                        employee.setEmpOrder(u.getOrder());
                        if (u.getIsAdmin() != null) {
                            employee.setEmpIsAdmin(u.getIsAdmin());
                        }
                        if (u.getIsBoss() != null) {
                            employee.setEmpIsBoss(u.getIsBoss());
                        }
                        if (u.getIsHide() != null) {
                            employee.setEmpIsHide(u.getIsHide());
                        }
                        if (u.getIsLeader() != null) {
                            employee.setEmpIsLeader(u.getIsLeader());
                        }
                        if (u.getActive() != null) {
                            employee.setEmpActive(u.getActive());
                        }
                        if (u.getPosition() != null) {
                            employee.setEmpPosition(u.getPosition());
                        }
                        if (u.getEmail() != null) {
                            employee.setEmpEmail(u.getEmail());
                        }

                        if (u.getAvatar() != null) {
                            employee.setEmpAvatar(u.getAvatar());
                        }
                        employee.setEmpJobnumber(u.getJobnumber());
                        employee.setEmpExtattr(u.getExtattr());
                        employee.setEmpDepartmentIdsStr(u.getDepartment());
                        employeeRepository.save(employee);
                    }
                });
            }
            catch (Exception e){}

        }
        return userMap;
    }

    /**
     * 获取所有部门用户并插入员工表返回用户id集合
     * @param requestParameters_dd
     * @return
     */
    public List<String>getAllDepUserIds(RequestParameters_dd requestParameters_dd) throws JSONException, ApiException {
        List<String>userids=new ArrayList <>();
          if (requestParameters_dd.getDep_id_list().size()>0){
              for (int i=0;i<requestParameters_dd.getDep_id_list().size();i++){
                  long depid=requestParameters_dd.getDep_id_list().get(i);
                  RequestParameters_dd requestParameters_dd1=new RequestParameters_dd();
                  requestParameters_dd1.setDep_id(depid);
                 requestParameters_dd1.setOffset(requestParameters_dd.getOffset());
                 requestParameters_dd1.setSize(requestParameters_dd.getSize());
                 requestParameters_dd1.setHttpMethodType(requestParameters_dd.getHttpMethodType());

           List<String>  userid= new ArrayList<String>(getDepUserIds(requestParameters_dd1).keySet());
                  userids.removeAll(userid);//无重重复并集
                  userids.addAll(userid);
              }
          }
          return userids;
}




    /**
     * 获取全公司所有员工的的id集合
     * @param equestParameters_dd
     * @return
     */
    public List <String> allCompanyUsersId(RequestParameters_dd equestParameters_dd) throws JSONException, ApiException {

        List<String>useridstrs=null;
        if (equestParameters_dd==null){
            RequestParameters_dd requestParameters=new RequestParameters_dd();
            requestParameters.setHttpMethodType("GET");
            requestParameters.setOffset(0L);
            requestParameters.setSize(1000L);
            useridstrs= getAllDepUserIds(requestParameters);
            return useridstrs;
        }
        useridstrs= getAllDepUserIds(equestParameters_dd);
        return useridstrs;
    }

    /**
     * 通过用户id获取用户的日志列表，并插入数据表
     * @return
     */
    public int getUserDailyByUserId(RequestParameters_dd requestParameters_dd) throws JSONException, ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/report/list");
        OapiReportListRequest request = new OapiReportListRequest();
        request.setUserid(requestParameters_dd.getUserid());
        request.setStartTime(requestParameters_dd.getStart_time());
        request.setEndTime(requestParameters_dd.getEnd_time());
        request.setCursor(requestParameters_dd.getCursor());
        request.setSize(requestParameters_dd.getSize());
        OapiReportListResponse response = client.execute(request, toolUtil.getAccessToken());
        final boolean[] isHasMore = {false};
        final Long[] nextCursor = {0L};
        int dailySize=0;
        if (response!=null&&response.getResult().getDataList().size()>0)
        {
            dailySize+=response.getResult().getDataList().size();
            response.getResult().getDataList().forEach(u -> {

                String repId = u.getReportId();

                boolean bool = dailyRepository.existsByDaiReportId(repId);

                if (bool == false) {


                    Daily daily = new Daily();
                    /**
                     *
                     Example<Daily>ex=Example.of(daily);
                     boolean b=dailyRepository.exists(ex);

                     if (dailyRepository.exists(ex)){
                     return;
                     }

                     Daily daily1=dailyRepository.findByDai_report_id(u.getReportId());
                     if (daily1!=null){return;}
                     */
                    if (u.getContents().size() > 0) {
                        u.getContents().forEach(m -> {
                            //    String mm = ResultEnum.DAI_FINISH_WORK.getMsg();
                            if (m.getKey().equals(ResultEnum.DAI_FINISH_WORK.getMsg())) {
                                daily.setDaiFinishWork(m.getValue());
                            }
                            if (m.getKey().equals(ResultEnum.DAI_UNFINSHED_WORK.getMsg())) {
                                daily.setDaiUnfinishedWork(m.getValue());
                            }
                            if (m.getKey().equals(ResultEnum.DAI_NEED_HELP_WORK.getMsg())) {
                                daily.setDaiNeedHelpWord(m.getValue());
                            }
                            if (m.getKey().equals(ResultEnum.DAI_TOMORROW_WORK.getMsg())) {
                                daily.setDaiTomorrowWork(m.getValue());
                            }
                            if (m.getKey().equals(ResultEnum.DAI_DATE.getMsg())) {
                                daily.setDaiDate(m.getValue());
                            }
                            if (m.getKey().equals(ResultEnum.DAI_WORK_PLACE.getMsg())) {
                                daily.setDaiWorkPlace(m.getValue());
                            }
                            if (m.getKey().equals(ResultEnum.DAI_LOCATION.getMsg())) {
                                daily.setDaiLocation(m.getValue());
                            }
                            daily.setDaiContents(u.getContents().toString());
                        });
                        daily.setDaiRemark(u.getRemark());
                        daily.setDaiTemplateName(u.getTemplateName());
                        daily.setDaiDeptName(u.getDeptName());
                        daily.setDaiCreatorName(u.getCreatorName());
                        daily.setDaiCreatorId(u.getCreatorId());
                        daily.setDaiCreateTime(u.getCreateTime());
                        daily.setDaiReportId(u.getReportId());
                        daily.setDaiSize(response.getResult().getSize());
                        if (response.getResult().getNextCursor() > 0) {
                            nextCursor[0] =response.getResult().getNextCursor();
                            daily.setDaiNextCursor(response.getResult().getNextCursor());
                        }


                        if (response.getResult().getHasMore() != null) {
                            isHasMore[0] = response.getResult().getHasMore();
                            daily.setDaiHasMore(response.getResult().getHasMore());
                        }
                        dailyRepository.save(daily);
                    }
                }
            });
        }

        if (isHasMore[0]==true){
            RequestParameters_dd requestParametersDd=new RequestParameters_dd();
            requestParametersDd=requestParameters_dd;
            requestParametersDd.setCursor(nextCursor[0]);
            getUserDailyByUserId(requestParametersDd);
        }
return dailySize;

}

    /**
     * 获取多个人员的日志
     * @param requestParameters_dd
     * @return
     */
    public int getAllUsersDailyByUserId(RequestParameters_dd requestParameters_dd){
        if (requestParameters_dd==null||requestParameters_dd.getUser_id_list().size()<=0){
            return 0;
        }
        int dailySize=0;
        for (String u : requestParameters_dd.getUser_id_list()) {
            RequestParameters_dd requestParametersDd = new RequestParameters_dd();
            requestParametersDd = requestParameters_dd;
            requestParametersDd.setUser_id_list(null);
            requestParametersDd.setUserid(u.toString());
            try {
                int dailmint = getUserDailyByUserId(requestParametersDd);
                dailySize +=dailmint;
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
        return dailySize;
}






    /**
     * 获取部门用户签到记录
     * @return
     */

    public OapiCheckinRecordResponse getDepartmentalUsersToSign(RequestParameters_dd requestParameters_dd) throws JSONException, ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/checkin/record");
        OapiCheckinRecordRequest request = new OapiCheckinRecordRequest();
        request.setDepartmentId(requestParameters_dd.getDepartment_id()); //为1时为根部门，一般为企业
        request.setStartTime(System.currentTimeMillis()-TimeUnit.DAYS.toMillis(1));
        request.setEndTime(System.currentTimeMillis());
        request.setOffset(requestParameters_dd.getOffset());
        request.setOrder(requestParameters_dd.getOrder());
        request.setSize(requestParameters_dd.getSize());
        request.setHttpMethod(requestParameters_dd.getHttpMethodType());
        OapiCheckinRecordResponse response = client.execute(request, toolUtil.getAccessToken());
        return response;
    }

    /**
     * 获取部门用户签到记录并插入到用户表中
     * @param requestParameters_dd
     * @throws JSONException
     * @throws ApiException
     */
    public OapiCheckinRecordResponse insertDepUsersToEmp(RequestParameters_dd requestParameters_dd) throws JSONException, ApiException {
        OapiCheckinRecordResponse oapiCheckinRecordResponse=getDepartmentalUsersToSign(requestParameters_dd);
        if (oapiCheckinRecordResponse!=null&&oapiCheckinRecordResponse.getData().size()>0)
        oapiCheckinRecordResponse.getData().forEach(u->{
            String emdId=u.getUserId();
            boolean bool=employeeRepository.existsByEmpUserId(emdId);
            if (bool==false) {
                Employee employee = new Employee();
                employee.setEmpName(u.getName());
                employee.setEmpUserId(u.getUserId());
                employee.setEmpTimestamp(u.getTimestamp());
                employee.setEmpAvatar(u.getAvatar());
                employee.setEmpPlace(u.getPlace());
                employee.setEmpDetailPlace(u.getDetailPlace());
                employee.setEmpRemark(u.getRemark());
                employee.setEmpLatitude(u.getLatitude());
                employee.setEmpLongitude(u.getLongitude());
                employee.setEmpImageDate(u.getImageList().toString());
                employeeRepository.save(employee);
            }
        });
        return oapiCheckinRecordResponse;
    }

}
