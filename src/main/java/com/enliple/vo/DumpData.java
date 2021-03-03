package com.enliple.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lcroms on 2017. 2. 24..
 * userId : 광고주 ID
 * adType : 광고 구분
 * siteCode : 캠패인 코드
 * statDate : 날짜 yyyyMMddHHmm
 * sendDate : 전송받은 시간
 * scriptNo : 지면번호(s)
 * mediaId : 매체 ID
 * bannerType : 배너 타임
 * platform : Web, Mobile 구분
 * viewCnt : 총 노출
 * areaCnt : 구좌 노출
 * mediaCnt : 매체 구좌 노출
 * offset : kafka offset정보
 */
public class DumpData {

    private String userId;
    private String adType;
    private String siteCode;
    private String sendDate;
    private String statDate;
    private String scriptNo;
    private String mediaId;
    private String bannerType;
    private String platform;

    private int viewCnt;
	private int areaCnt;
    private int mediaCnt;    

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAdType() {
		return adType;
	}

	public void setAdType(String adType) {
		this.adType = adType;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	public String getScriptNo() {
		return scriptNo;
	}

	public void setScriptNo(String scriptNo) {
		this.scriptNo = scriptNo;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getBannerType() {
		return bannerType;
	}

	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public int getAreaCnt() {
		return areaCnt;
	}

	public void setAreaCnt(int areaCnt) {
		this.areaCnt = areaCnt;
	}

	public int getMediaCnt() {
		return mediaCnt;
	}

	public void setMediaCnt(int mediaCnt) {
		this.mediaCnt = mediaCnt;
	}

	@Override
    public String toString() {
        return "DumpData{" +
                "userId='" + userId + '\'' +
                ", adType='" + adType + '\'' +
                ", siteCode='" + siteCode + '\'' +
                ", statDate='" + statDate + '\'' +
                ", sendDate='" + sendDate + '\'' +
                ", scriptNo='" + scriptNo + '\'' +
                ", mediaId='" + mediaId + '\'' +
                ", bannerType='" + bannerType + '\'' +
                ", platform='" + platform + '\'' +
                ", viewCnt=" + viewCnt +
                ", areaCnt=" + areaCnt +
                ", mediaCnt=" + mediaCnt +                
                '}';
    }

    public Map<String, Object> convertMap() {
        Map<String, Object> dumpData = new HashMap<>();
        dumpData.put("siteCode", getSiteCode());
        dumpData.put("userId", getUserId());
        dumpData.put("adType", getAdType());
        dumpData.put("statDate", getStatDate());
        dumpData.put("sendDate", getSendDate());
        dumpData.put("scriptNo", getScriptNo());
        dumpData.put("mediaId", getMediaId());
        dumpData.put("viewCnt", getViewCnt());
        dumpData.put("areaCnt", getAreaCnt());
        dumpData.put("mediaCnt", getMediaCnt());
        dumpData.put("bannerType", getBannerType());
        dumpData.put("platform", getPlatform());        
        return dumpData;
    }
}
