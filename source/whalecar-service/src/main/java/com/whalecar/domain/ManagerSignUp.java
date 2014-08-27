package com.whalecar.domain;

/**
 * Created by wufei on 14-8-27.
 */
public class ManagerSignUp {
    private Integer id;
    private String name;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAttentionModel() {
        return attentionModel;
    }

    public void setAttentionModel(String attentionModel) {
        this.attentionModel = attentionModel;
    }

    public String getPurposeShop() {
        return purposeShop;
    }

    public void setPurposeShop(String purposeShop) {
        this.purposeShop = purposeShop;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    private String tel;
    private String attentionModel;
    private String purposeShop;
    private String createDate;
    private String sex;
}
