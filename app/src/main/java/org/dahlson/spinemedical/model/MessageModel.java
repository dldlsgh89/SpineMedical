package org.dahlson.spinemedical.model;

import org.dahlson.spinemedical.PassFragment;

public class MessageModel {
    int idex = 0;
    String imgUrl = "";
    String fromName = "";
    String content = "";
    String insertDt = "";
    int viewType = 0;  //1, 2, 3, 4 = message view
    //5 = message list
    // 6 = 신규 메시지를 보낼 회원 조회 리스트

    String userId;
    String doctorName;
    String hospitalName;
    String speciality;

    public MessageModel() {

    }

    public MessageModel(int idex, String imgUrl, String fromName, String content, String insertDt, int viewType) {
        this.idex = idex;
        this.imgUrl = imgUrl;
        this.fromName = fromName;
        this.content = content;
        this.insertDt = insertDt;
        this.viewType = viewType;
    }

    public int getIdex() {
        return idex;
    }

    public void setIdex(int idex) {
        this.idex = idex;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInsertDt() {
        return insertDt;
    }

    public void setInsertDt(String insertDt) {
        this.insertDt = insertDt;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
