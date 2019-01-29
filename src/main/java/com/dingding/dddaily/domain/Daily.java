package com.dingding.dddaily.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 钉钉日报返回日报列表
 */
@Entity
@Table(name = "dd_daily")
public class Daily implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int daiId;

    /**
     * 日志内容(json格式 ：)
     * String sort "1"排序
     * String type "0"类型
     * String value "\开发工作"用户填写的内容
     * String key "今日工作"模板内容
     */
    @Column(length = 2000)
    private String daiContents;
    //日志日期
    private String daiDate;
    //办公地点
    @Column(length = 500)
    private String daiWorkPlace;
    //所在地
    @Column(length = 500)
    private String daiLocation;
    //今日完成工作
    @Column(length = 1000)
    private String daiFinishWork;
    //未完成工作
    @Column(length = 1000)
    private String daiUnfinishedWork;
    //需协调工作
    @Column(length = 1000)
    private String daiNeedHelpWord;
    //明日工作
    @Column(length = 1000)
    private String daiTomorrowWork;

    //String sort "1"排序
    private String daiSort;
    //* String type "0"类型
    private String daiType;
    //  * String value "\开发工作"用户填写的内容
    private String daiValue;
    //  * String key "今日工作"模板内容
    private String daiKey;

    public Long daiSize;
    private Long daiNextCursor;
    private boolean daiHasMore;


    /**
     * 备注
     */
    private String daiRemark;
    /**
     * 日志模板名
     */
    private String daiTemplateName;
    /**
     * 部门名称
     */
    private String daiDeptName;
    /**
     * 日志创建人
     */
    private String daiCreatorName;
    /**
     * 日志创建人userid
     */
    private String daiCreatorId;
    /**
     * 日志创建时间
     */
    private Long daiCreateTime;
    /**
     * 日志唯一id
     */
    private String daiReportId;

    public int getDaiId() {
        return daiId;
    }

    public void setDaiId(int daiId) {
        this.daiId = daiId;
    }

    public String getDaiContents() {
        return daiContents;
    }

    public void setDaiContents(String daiContents) {
        this.daiContents = daiContents;
    }

    public String getDaiDate() {
        return daiDate;
    }

    public void setDaiDate(String daiDate) {
        this.daiDate = daiDate;
    }

    public String getDaiWorkPlace() {
        return daiWorkPlace;
    }

    public void setDaiWorkPlace(String daiWorkPlace) {
        this.daiWorkPlace = daiWorkPlace;
    }

    public String getDaiLocation() {
        return daiLocation;
    }

    public void setDaiLocation(String daiLocation) {
        this.daiLocation = daiLocation;
    }

    public String getDaiFinishWork() {
        return daiFinishWork;
    }

    public void setDaiFinishWork(String daiFinishWork) {
        this.daiFinishWork = daiFinishWork;
    }

    public String getDaiUnfinishedWork() {
        return daiUnfinishedWork;
    }

    public void setDaiUnfinishedWork(String daiUnfinishedWork) {
        this.daiUnfinishedWork = daiUnfinishedWork;
    }

    public String getDaiNeedHelpWord() {
        return daiNeedHelpWord;
    }

    public void setDaiNeedHelpWord(String daiNeedHelpWord) {
        this.daiNeedHelpWord = daiNeedHelpWord;
    }

    public String getDaiTomorrowWork() {
        return daiTomorrowWork;
    }

    public void setDaiTomorrowWork(String daiTomorrowWork) {
        this.daiTomorrowWork = daiTomorrowWork;
    }

    public String getDaiSort() {
        return daiSort;
    }

    public void setDaiSort(String daiSort) {
        this.daiSort = daiSort;
    }

    public String getDaiType() {
        return daiType;
    }

    public void setDaiType(String daiType) {
        this.daiType = daiType;
    }

    public String getDaiValue() {
        return daiValue;
    }

    public void setDaiValue(String daiValue) {
        this.daiValue = daiValue;
    }

    public String getDaiKey() {
        return daiKey;
    }

    public void setDaiKey(String daiKey) {
        this.daiKey = daiKey;
    }

    public Long getDaiSize() {
        return daiSize;
    }

    public void setDaiSize(Long daiSize) {
        this.daiSize = daiSize;
    }

    public Long getDaiNextCursor() {
        return daiNextCursor;
    }

    public void setDaiNextCursor(Long daiNextCursor) {
        this.daiNextCursor = daiNextCursor;
    }

    public boolean isDaiHasMore() {
        return daiHasMore;
    }

    public void setDaiHasMore(boolean daiHasMore) {
        this.daiHasMore = daiHasMore;
    }

    public String getDaiRemark() {
        return daiRemark;
    }

    public void setDaiRemark(String daiRemark) {
        this.daiRemark = daiRemark;
    }

    public String getDaiTemplateName() {
        return daiTemplateName;
    }

    public void setDaiTemplateName(String daiTemplateName) {
        this.daiTemplateName = daiTemplateName;
    }

    public String getDaiDeptName() {
        return daiDeptName;
    }

    public void setDaiDeptName(String daiDeptName) {
        this.daiDeptName = daiDeptName;
    }

    public String getDaiCreatorName() {
        return daiCreatorName;
    }

    public void setDaiCreatorName(String daiCreatorName) {
        this.daiCreatorName = daiCreatorName;
    }

    public String getDaiCreatorId() {
        return daiCreatorId;
    }

    public void setDaiCreatorId(String daiCreatorId) {
        this.daiCreatorId = daiCreatorId;
    }

    public Long getDaiCreateTime() {
        return daiCreateTime;
    }

    public void setDaiCreateTime(Long daiCreateTime) {
        this.daiCreateTime = daiCreateTime;
    }

    public String getDaiReportId() {
        return daiReportId;
    }

    public void setDaiReportId(String daiReportId) {
        this.daiReportId = daiReportId;
    }

    @Override
    public String toString() {
        return "Daily{" +
                "daiId=" + daiId +
                ", daiContents='" + daiContents + '\'' +
                ", daiDate='" + daiDate + '\'' +
                ", daiWorkPlace='" + daiWorkPlace + '\'' +
                ", daiLocation='" + daiLocation + '\'' +
                ", daiFinishWork='" + daiFinishWork + '\'' +
                ", daiUnfinishedWork='" + daiUnfinishedWork + '\'' +
                ", daiNeedHelpWord='" + daiNeedHelpWord + '\'' +
                ", daiTomorrowWork='" + daiTomorrowWork + '\'' +
                ", daiSort='" + daiSort + '\'' +
                ", daiType='" + daiType + '\'' +
                ", daiValue='" + daiValue + '\'' +
                ", daiKey='" + daiKey + '\'' +
                ", daiSize=" + daiSize +
                ", daiNextCursor=" + daiNextCursor +
                ", daiHasMore=" + daiHasMore +
                ", daiRemark='" + daiRemark + '\'' +
                ", daiTemplateName='" + daiTemplateName + '\'' +
                ", daiDeptName='" + daiDeptName + '\'' +
                ", daiCreatorName='" + daiCreatorName + '\'' +
                ", daiCreatorId='" + daiCreatorId + '\'' +
                ", daiCreateTime=" + daiCreateTime +
                ", daiReportId='" + daiReportId + '\'' +
                '}';
    }
}