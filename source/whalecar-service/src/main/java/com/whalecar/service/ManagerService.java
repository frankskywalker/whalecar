package com.whalecar.service;

import com.whalecar.domain.*;
import com.whalecar.persistence.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruihuang on 14-2-26.
 */
@Controller
public class ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    /**
     * 查询优惠价格购买表格
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/queryUserOffTicket")
    public @ResponseBody
    List<UserOffTicketManager> queryUserOffTicket(){
        List<UserOffTicketManager> uotmList = managerMapper.queryUserOffTicket();
        for(UserOffTicketManager uotm:uotmList){
            uotm.setFullName(uotm.getCnameCb()+"-"+uotm.getCnameLv1()+"  "+uotm.getFullName());
        }
        return uotmList;
    }


    /**
     * 查询用户订单表格
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/queryUserOrder")
    public @ResponseBody
    List<UserOrderManager> queryUserOrder(){
        return managerMapper.queryUserOrder();
    }


    /**
     * 查找用户提交价格表格
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/queryUserSubmitPrice")
    public @ResponseBody
    List<UserSubmitPriceManager> queryUserSubmitPrice(){
        List<UserSubmitPriceManager> uspmList = managerMapper.queryUserSubmitPrice();
        for(UserSubmitPriceManager uspm:uspmList){
            uspm.setFullName(uspm.getCnameCb()+"-"+uspm.getCnameLv1()+"  "+uspm.getFullName());
        }
        return uspmList;
    }


    /**
     * 查找所有用户
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/managerUser")
    public @ResponseBody
    List<User> queryUser(){
        return managerMapper.queryUser();
    }


    /**
     * 查找carBrand表详细信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/managerGetCarBrand")
    public @ResponseBody
    List<CarBrand> managerGetCarBrand(){
        return managerMapper.queryManagerGetCarBrand();
    }


    /**
     * 更新CarBrand的数据
     * @param map
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/manager/updateManagerGetCarBrand")
    public @ResponseBody
    Boolean updateManagerGetCarBrand(@RequestBody Map<String,Object> map){
        int i = managerMapper.updateManagerGetCarBrand(map);
        if(i != 0){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 查找Car_model_Lv1表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/managerGetCarModelLv1")
    public @ResponseBody
    List<CarModelLv1> managerGetCarModelLv1(){
        List<CarModelLv1> cml1List = managerMapper.queryManagerGetCarModelLv1();
        for(CarModelLv1 cml1 : cml1List){
            CarBrand cb = managerMapper.queryCarBrandById(cml1.getCarBrand());
            CarSubBrand csb = managerMapper.queryCarSubBrandById(cml1.getCarSubBrand());
            cml1.setCarBrandName(cb.getCname());
            if(csb != null){
                cml1.setCarSubBrandName(csb.getCname());
            }
        }
        return cml1List;
    }


    /**
     * 查找Car_Sub_Brand表的所有信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/managerGetCarSubBrand")
    public @ResponseBody
    List<CarSubBrand> managerGetCarSubBrand(){
        return managerMapper.queryCarSubBrand();
    }


    /**
     * 更新Car_Model_Lv1表
     * @param map
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/manager/updateManagerGetCarModelLv1")
    public @ResponseBody
    Boolean updateManagerGetCarModelLv1(@RequestBody Map<String,Object> map){
        int i = managerMapper.updateManagerGetCarModelLv1(map);
        if(i != 0){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 查找Car_Model_Lv1_IMAGE表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/managerGetCarModelLv1Image")
    public @ResponseBody
    List<CarModelLv1Image> managerGetCarModelLv1Image(){
        List<CarModelLv1Image> cml1iList =  managerMapper.queryManagerGetCarModelLv1Image();
        for(CarModelLv1Image cml1i:cml1iList){
            CarModelLv1 cml1 = managerMapper.queryCarModelLv1ById(cml1i.getCarModelLv1());
            if(cml1 != null){
                cml1i.setCarModelLv1Name(cml1.getCname());
            }
        }
        return cml1iList;
    }


    /**
     * 更新Car_Model_Lv1_IMAGE表
     * @param map
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/manager/updateManagerGetCarModelLv1Image")
    public @ResponseBody
    Boolean updateManagerGetCarModelLv1Image(@RequestBody Map<String,Object> map){
        int i = managerMapper.updateManagerGetCarModelLv1Image(map);
        if(i != 0){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 查询Car_Model_Lv2表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/managerGetCarModelLv2")
    public @ResponseBody
    List<CarModelLv2> managerGetCarModelLv2(){
        List<CarModelLv2> cml2iList =  managerMapper.queryManagerGetCarModelLv2();
        for(CarModelLv2 cml2:cml2iList){
            CarModelLv1 cml1 = managerMapper.queryCarModelLv1ById(cml2.getCarModelLv1());
            if(cml1 != null){
                cml2.setCarModelLv1Name(cml1.getCname());
            }
        }
        return cml2iList;
    }


    /**
     * 更新Car_Model_Lv2表
     * @param map
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/manager/updateManagerGetCarModelLv2")
    public @ResponseBody
    Boolean updateManagerGetCarModelLv2(@RequestBody Map<String,Object> map){
        int i = managerMapper.updateManagerGetCarModelLv2(map);
        if(i != 0){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 查找Car_Model_Lv3表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/managerGetCarModelLv3")
    public @ResponseBody
    List<CarModelLv3> managerGetCarModelLv3(){
        List<CarModelLv3> cml3List = managerMapper.queryManagerGetCarModelLv3();
        for(CarModelLv3 cml3:cml3List){
            CarModelLv2 cml2 = managerMapper.queryCarModelLv2ById(cml3.getCarModelLv2());
            String[] colorOutsideCollection = cml3.getColorOutsideCollection().split(",");
            String[] colorInsideCollection = cml3.getColorInsideCollection().split(",");
            String[] tmpOutsideArray = new String[colorOutsideCollection.length];
            String[] tmpInsideArray = new String[colorInsideCollection.length];
            for(int i = 0;i<colorOutsideCollection.length;i++){
                DicColor dc = managerMapper.queryDicColorById(Integer.valueOf(colorOutsideCollection[i]));
                if(dc != null){
                    tmpOutsideArray[i] = dc.getColorCname();
                }
            }
            for(int i = 0;i<colorInsideCollection.length;i++){
                DicColor dc = managerMapper.queryDicColorById(Integer.valueOf(colorInsideCollection[i]));
                if(dc != null){
                    tmpInsideArray[i] = dc.getColorCname();
                }
            }
            cml3.setColorOutsideCollectionName(Arrays.toString(tmpOutsideArray).trim().substring(1,Arrays.toString(tmpOutsideArray).lastIndexOf("]")));
            cml3.setColorInsideCollectionName(Arrays.toString(tmpInsideArray).trim().substring(1,Arrays.toString(tmpInsideArray).lastIndexOf("]")));
            cml3.setCarModelLv2Name(cml2.getShortName());
            cml3.setCarModelLv1Name(managerMapper.queryCarModelLv1ById(cml2.getCarModelLv1()).getCname());
        }
        return cml3List;
    }


    /**
     * 查找Dic_Color表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/managerGetDicColor")
    public @ResponseBody
    List<DicColor> managerGetDicColor(){
        return managerMapper.queryManagerGetDicColor();
    }


    /**
     * 更新Car_Model_Lv3表
     * @param map
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/manager/updateManagerGetCarModelLv3")
    public @ResponseBody
    Boolean updateManagerGetCarModelLv3(@RequestBody Map<String,Object> map){
        List<Object> colorOutsideCollectionList = (List<Object>)map.get("colorOutsideCollection");
        List<Object> colorInsideCollection = (List<Object>)map.get("colorInsideCollection");
        String tmpOutsideCollection = "";
        String tmpInsideCollection = "";
        for(Object o:colorOutsideCollectionList){
           tmpOutsideCollection += o + ",";
        }
        for(Object o:colorInsideCollection){
            tmpInsideCollection += o + ",";
        }
        map.put("colorOutsideCollection",tmpOutsideCollection.substring(0,tmpOutsideCollection.lastIndexOf(",")));
        map.put("colorInsideCollection",tmpInsideCollection.substring(0,tmpInsideCollection.lastIndexOf(",")));
        Map<String,Object> tmpMap = new HashMap<String,Object>();
//        tmpMap.put("id",map.get("carModelLv2"));
//        tmpMap.put("shortName",map.get("carModelLv2Name"));
 //        managerMapper.updateCarModelLv2ShortName(tmpMap);
        int i = managerMapper.updateManagerGetCarModelLv3(map);
        if(i != 0){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 添加Car_Brand数据
     * @param map
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/manager/addManagerGetCarBrand")
    public @ResponseBody
    Boolean addManagerGetCarBrand(@RequestBody Map<String,Object> map) {
        int i = managerMapper.addManagerGetCarBrand(map);
        if(i != 0){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 添加Car_Model_Lv1数据
     * @param map
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/manager/addManagerGetCarModelLv1")
    public @ResponseBody
    Boolean addManagerGetCarModelLv1(@RequestBody Map<String,Object> map) {
        map.put("imgPath","noData");
        int i = managerMapper.addManagerGetCarModelLv1(map);
        if(i != 0){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 添加Car_Model_Lv1_IMAGE数据
     * @param map
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/manager/addManagerGetCarModelLv1Image")
    public @ResponseBody
    Boolean addManagerGetCarModelLv1Image(@RequestBody Map<String,Object> map) {
        int i = managerMapper.addManagerGetCarModelLv1Image(map);
        if(i != 0){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 添加Car_Model_Lv2数据
     * @param map
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/manager/addManagerGetCarModelLv2")
    public @ResponseBody
    Boolean addManagerGetCarModelLv2(@RequestBody Map<String,Object> map) {
        int i = managerMapper.addManagerGetCarModelLv2(map);
        if(i != 0){
            return true;
        }else {
            return false;
        }
    }



    /**
     * 添加Car_Model_Lv3数据
     * @param map
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/manager/addManagerGetCarModelLv3")
    public @ResponseBody
    Boolean addManagerGetCarModelLv3(@RequestBody Map<String,Object> map) {
        int i = managerMapper.addManagerGetCarModelLv3(map);
        if(i != 0){
            return true;
        }else {
            return false;
        }
    }
}
