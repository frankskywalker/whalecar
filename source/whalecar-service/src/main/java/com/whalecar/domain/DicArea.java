package com.whalecar.domain;

/**
 * Created by sf on 14-2-20.
 * 宁波市区，县市
 */
public class DicArea {
    private int id;
    private String areaCname;
    private String areaEname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAreaCname() {
        return areaCname;
    }

    public void setAreaCname(String areaCname) {
        this.areaCname = areaCname;
    }

    public String getAreaEname() {
        return areaEname;
    }

    public void setAreaEname(String areaEname) {
        this.areaEname = areaEname;
    }
}
