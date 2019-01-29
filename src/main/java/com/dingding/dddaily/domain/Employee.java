package com.dingding.dddaily.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;


/**
 * 员工列表
 */
@Entity
@Table(name = "dd_emplayee")
public class Employee {
    //员工表id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;
    //员工唯一标识ID（不可修改）
    private String empUserId;
//在当前isv全局范围内唯一标识一个用户的身份，用户无法修改
    private String empUnionid;
    //钉钉中的成员名称
    private String empName;
    //表示人员在此部门中的排序，列表是按order的倒序排列输出的，即从大到小排列输出的
    //（钉钉管理后台里面调整了顺序的话order才有值）
    private Long empOrder;
    //手机号（ISV不可见）
    private String empMobile;
    //wv分机号（ISV不可见）
    private String empTel;
    //办公地点（ISV不可见）
    private String empWorkPlace;
    //备注（ISV不可见）
    private String empdepUserRemark;
    //是否是企业的管理员,
    //true表示是
    //false表示不是
    private boolean empIsAdmin;
    //是否为企业的老板（不能通过接口进行设置，可以通过钉钉管理后台进行设置），
    //true表示是
    //false表示不是
    private boolean empIsBoss;
    //是否隐藏号码，
    //true表示是
    //false表示不是
    private boolean empIsHide;
    //是否是部门的主管，
    //true表示是，
    //false表示不是
    private boolean empIsLeader;
//表示该用户是否激活了钉钉
    private boolean empActive;
    //成员所属部门id列表
//private List<Long>dep_user_list;
//职位信息
private String empPosition;
//员工的邮箱
private String empEmail;
//员工工号
private String empJobnumber;
//扩展属性
//可以设置多种属性(但手机上最多只能显示10个扩展属性，具体显示哪些属性，
//请到OA管理后台->设置->通讯录信息设置和OA管理后台->设置->手机端显示信息设置)
private String empExtattr;
    //签到时间
    private Long empTimestamp;
    //头像url
    private String empAvatar;
    //签到地址
    private String empPlace;
    //签到详细地址
    private String empDetailPlace;
    //签到备注
    private String empRemark;
    //纬度
    private String empLatitude;
    //经度
    private String empLongitude;
    //签到照片url列表
    private String empImageDate;
//员工的部门id集合str
private String empDepartmentIdsStr;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpUserId() {
        return empUserId;
    }

    public void setEmpUserId(String empUserId) {
        this.empUserId = empUserId;
    }

    public String getEmpUnionid() {
        return empUnionid;
    }

    public void setEmpUnionid(String empUnionid) {
        this.empUnionid = empUnionid;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Long getEmpOrder() {
        return empOrder;
    }

    public void setEmpOrder(Long empOrder) {
        this.empOrder = empOrder;
    }

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public String getEmpTel() {
        return empTel;
    }

    public void setEmpTel(String empTel) {
        this.empTel = empTel;
    }

    public String getEmpWorkPlace() {
        return empWorkPlace;
    }

    public void setEmpWorkPlace(String empWorkPlace) {
        this.empWorkPlace = empWorkPlace;
    }

    public String getEmpdepUserRemark() {
        return empdepUserRemark;
    }

    public void setEmpdepUserRemark(String empdepUserRemark) {
        this.empdepUserRemark = empdepUserRemark;
    }

    public boolean isEmpIsAdmin() {
        return empIsAdmin;
    }

    public void setEmpIsAdmin(boolean empIsAdmin) {
        this.empIsAdmin = empIsAdmin;
    }

    public boolean isEmpIsBoss() {
        return empIsBoss;
    }

    public void setEmpIsBoss(boolean empIsBoss) {
        this.empIsBoss = empIsBoss;
    }

    public boolean isEmpIsHide() {
        return empIsHide;
    }

    public void setEmpIsHide(boolean empIsHide) {
        this.empIsHide = empIsHide;
    }

    public boolean isEmpIsLeader() {
        return empIsLeader;
    }

    public void setEmpIsLeader(boolean empIsLeader) {
        this.empIsLeader = empIsLeader;
    }

    public boolean isEmpActive() {
        return empActive;
    }

    public void setEmpActive(boolean empActive) {
        this.empActive = empActive;
    }

    public String getEmpPosition() {
        return empPosition;
    }

    public void setEmpPosition(String empPosition) {
        this.empPosition = empPosition;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpJobnumber() {
        return empJobnumber;
    }

    public void setEmpJobnumber(String empJobnumber) {
        this.empJobnumber = empJobnumber;
    }

    public String getEmpExtattr() {
        return empExtattr;
    }

    public void setEmpExtattr(String empExtattr) {
        this.empExtattr = empExtattr;
    }

    public Long getEmpTimestamp() {
        return empTimestamp;
    }

    public void setEmpTimestamp(Long empTimestamp) {
        this.empTimestamp = empTimestamp;
    }

    public String getEmpAvatar() {
        return empAvatar;
    }

    public void setEmpAvatar(String empAvatar) {
        this.empAvatar = empAvatar;
    }

    public String getEmpPlace() {
        return empPlace;
    }

    public void setEmpPlace(String empPlace) {
        this.empPlace = empPlace;
    }

    public String getEmpDetailPlace() {
        return empDetailPlace;
    }

    public void setEmpDetailPlace(String empDetailPlace) {
        this.empDetailPlace = empDetailPlace;
    }

    public String getEmpRemark() {
        return empRemark;
    }

    public void setEmpRemark(String empRemark) {
        this.empRemark = empRemark;
    }

    public String getEmpLatitude() {
        return empLatitude;
    }

    public void setEmpLatitude(String empLatitude) {
        this.empLatitude = empLatitude;
    }

    public String getEmpLongitude() {
        return empLongitude;
    }

    public void setEmpLongitude(String empLongitude) {
        this.empLongitude = empLongitude;
    }

    public String getEmpImageDate() {
        return empImageDate;
    }

    public void setEmpImageDate(String empImageDate) {
        this.empImageDate = empImageDate;
    }

    public String getEmpDepartmentIdsStr() {
        return empDepartmentIdsStr;
    }

    public void setEmpDepartmentIdsStr(String empDepartmentIdsStr) {
        this.empDepartmentIdsStr = empDepartmentIdsStr;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empUserId='" + empUserId + '\'' +
                ", empUnionid='" + empUnionid + '\'' +
                ", empName='" + empName + '\'' +
                ", empOrder=" + empOrder +
                ", empMobile='" + empMobile + '\'' +
                ", empTel='" + empTel + '\'' +
                ", empWorkPlace='" + empWorkPlace + '\'' +
                ", empdepUserRemark='" + empdepUserRemark + '\'' +
                ", empIsAdmin=" + empIsAdmin +
                ", empIsBoss=" + empIsBoss +
                ", empIsHide=" + empIsHide +
                ", empIsLeader=" + empIsLeader +
                ", empActive=" + empActive +
                ", empPosition='" + empPosition + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", empJobnumber='" + empJobnumber + '\'' +
                ", empExtattr='" + empExtattr + '\'' +
                ", empTimestamp=" + empTimestamp +
                ", empAvatar='" + empAvatar + '\'' +
                ", empPlace='" + empPlace + '\'' +
                ", empDetailPlace='" + empDetailPlace + '\'' +
                ", empRemark='" + empRemark + '\'' +
                ", empLatitude='" + empLatitude + '\'' +
                ", empLongitude='" + empLongitude + '\'' +
                ", empImageDate='" + empImageDate + '\'' +
                ", empDepartmentIdsStr='" + empDepartmentIdsStr + '\'' +
                '}';
    }
}
