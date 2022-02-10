package org.dahlson.spinemedical.model;

public class SpineDataModel {
    int idex;
    double temperature;
    double pressure;
    int wear;
    String date_dt;

    public SpineDataModel(int idex, double temperature, double pressure, int wear, String date_dt) {
        this.idex = idex;
        this.temperature = temperature;
        this.pressure = pressure;
        this.wear = wear;
        this.date_dt = date_dt;
    }

    public int getIdex() {
        return idex;
    }

    public void setIdex(int idex) {
        this.idex = idex;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getWear() {
        return wear;
    }

    public void setWear(int wear) {
        this.wear = wear;
    }

    public String getDate_dt() {
        return date_dt;
    }

    public void setDate_dt(String data_dt) {
        this.date_dt = date_dt;
    }
}
