package org.dahlson.spinemedical.model;

public class DoctorModel {

    int idex;
    String doctorName;
    String hospitalName;
    String userId;

    public DoctorModel(int idex, String doctorName, String hospitalName, String userId) {
        this.idex = idex;
        this.doctorName = doctorName;
        this.hospitalName = hospitalName;
        this.userId = userId;
    }

    public int getIdex() {
        return idex;
    }

    public void setIdex(int idex) {
        this.idex = idex;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
