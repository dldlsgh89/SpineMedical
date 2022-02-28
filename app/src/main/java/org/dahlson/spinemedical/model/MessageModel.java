package org.dahlson.spinemedical.model;

public class MessageModel {
    int idex;
    String img_url;
    String fromName;
    String content;
    String insert_dt;

    public MessageModel(int idex, String img_url, String fromName, String content, String insert_dt) {
        this.idex = idex;
        this.img_url = img_url;
        this.fromName = fromName;
        this.content = content;
        this.insert_dt = insert_dt;
    }

    public int getIdex() {
        return idex;
    }

    public void setIdex(int idex) {
        this.idex = idex;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
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

    public String getInsert_dt() {
        return insert_dt;
    }

    public void setInsert_dt(String insert_dt) {
        this.insert_dt = insert_dt;
    }
}
