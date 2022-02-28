package org.dahlson.spinemedical.model;

public class SpineDataModel {
    int idex;
    double temperature;
    double pressure;
    int wear;
    String insert_dt;

    public SpineDataModel(int idex, double temperature, double pressure, int wear, String insert_dt) {
        this.idex = idex;
        this.temperature = temperature;
        this.pressure = pressure;
        this.wear = wear;
        this.insert_dt = insert_dt;
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

    public String getInsert_dt() {
        return insert_dt;
    }

    public void setInsert_dt(String data_dt) {
        this.insert_dt = insert_dt;
    }
}
