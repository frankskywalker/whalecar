package com.whalecar.domain;

import java.util.List;

/**
 * Created by wufei on 14/11/24.
 */
public class WxCarTypeAndColor {
    public List<String> getColorList() {
        return colorList;
    }

    public void setColorList(List<String> colorList) {
        this.colorList = colorList;
    }

    public List<WxLV2> getWxLV2List() {
        return wxLV2List;
    }

    public void setWxLV2List(List<WxLV2> wxLV2List) {
        this.wxLV2List = wxLV2List;
    }

    private List<String> colorList;
    private List<WxLV2> wxLV2List;
}
