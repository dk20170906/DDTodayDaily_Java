package com.dingding.dddaily.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "properconfig")
public class ProperConfig {

    private String corpId;
    private String corpSecret;
    private String accessTokenUrl;
    private String departmentalListUrl;




    private int dailyDaysBefor;

    public int getDailyDaysBefor() {
        return dailyDaysBefor;
    }

    public void setDailyDaysBefor(int dailyDaysBefor) {
        this.dailyDaysBefor = dailyDaysBefor;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpSecret() {
        return corpSecret;
    }

    public void setCorpSecret(String corpSecret) {
        this.corpSecret = corpSecret;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public String getDepartmentalListUrl() {
        return departmentalListUrl;
    }

    public void setDepartmentalListUrl(String departmentalListUrl) {
        this.departmentalListUrl = departmentalListUrl;
    }
}
