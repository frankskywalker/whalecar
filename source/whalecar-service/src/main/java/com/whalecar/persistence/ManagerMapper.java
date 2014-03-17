package com.whalecar.persistence;

import com.whalecar.domain.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ruihuang on 14-2-26.
 */
public interface ManagerMapper {

    /**
     * 查询优惠价格购买表格
     * @return
     */
    public List<UserOffTicketManager> queryUserOffTicket();


    /**
     * 查询用户订单表格
     * @return
     */
    public List<UserOrderManager> queryUserOrder();


    /**
     * 查询用户直接提交的表格
     * @return
     */
    public List<UserSubmitPriceManager> queryUserSubmitPrice();


    /**
     * 查找所有用户
     * @return
     */
    public List<User> queryUser();


    /**
     * 查找CarBrand详细信息
     * @return
     */
    public List<CarBrand> queryManagerGetCarBrand();


    /**
     * 更新CarBrand表的数据
     * @param map
     * @return
     */
    public int updateManagerGetCarBrand(Map<String,Object> map);


    /**
     * 查找Car_model_Lv1表
     * @return
     */
    public List<CarModelLv1> queryManagerGetCarModelLv1();


    /**
     * 根据id查找
     * @param id
     * @return
     */
    public CarBrand queryCarBrandById(Integer id);


    /**
     * 根据id查找Car_Sub_Brand
     * @param id
     * @return
     */
    public CarSubBrand queryCarSubBrandById(Integer id);


    /**
     * 查找Car_Sub_Brand表的所有信息
     * @return
     */
    public List<CarSubBrand> queryCarSubBrand();


    /**
     * 更新Car_Model_Lv1表
     * @param map
     * @return
     */
    public int updateManagerGetCarModelLv1(Map<String,Object> map);


    /**
     * 查找Car_Model_Lv1_IMAGE表
     * @return
     */
    public List<CarModelLv1Image> queryManagerGetCarModelLv1Image();


    /**
     * 根据id查找Car_Model_Lv1表
     * @param id
     * @return
     */
    public CarModelLv1 queryCarModelLv1ById(Integer id);


    /**
     * 更新Car_Model_Lv1_IMAGE表
     * @param map
     * @return
     */
    public int updateManagerGetCarModelLv1Image(Map<String,Object> map);


    /**
     * 查询Car_Model_Lv2表
     * @return
     */
    public List<CarModelLv2> queryManagerGetCarModelLv2();


    /**
     * 更新Car_Model_Lv2表
     * @param map
     * @return
     */
    public int updateManagerGetCarModelLv2(Map<String,Object> map);


    /**
     * 查找Car_Model_Lv3表
     * @return
     */
    public List<CarModelLv3> queryManagerGetCarModelLv3();


    /**
     * 根据id查询Car_Model_Lv2表
     * @param id
     * @return
     */
    public CarModelLv2 queryCarModelLv2ById(Integer id);


    /**
     * 查找Dic_Color表
     * @return
     */
    public List<DicColor> queryManagerGetDicColor();


    /**
     * 根据id查询Dic_Color表
     * @param id
     * @return
     */
    public DicColor queryDicColorById(Integer id);


    /**
     * 更新Car_Model_Lv3表
     * @param map
     * @return
     */
    public int updateManagerGetCarModelLv3(Map<String,Object> map);


    /**
     * 更细Car_Model_Lv2ShortName
     * @param map
     * @return
     */
    public int updateCarModelLv2ShortName(Map<String,Object> map);


    /**
     * 添加Car_Brand数据
     * @param map
     * @return
     */
    public int addManagerGetCarBrand(Map<String,Object> map);


    /**
     * 添加Car_Model_Lv1数据
     * @param map
     * @return
     */
    public int addManagerGetCarModelLv1(Map<String,Object> map);


    /**
     * 添加Car_Model_Lv1_IMAGE数据
     * @param map
     * @return
     */
    public int addManagerGetCarModelLv1Image(Map<String,Object> map);


    /**
     * 添加Car_Model_Lv2数据
     * @param map
     * @return
     */
    public int addManagerGetCarModelLv2(Map<String,Object> map);


    /**
     * 添加Car_Model_Lv3数据
     * @param map
     * @return
     */
    public int addManagerGetCarModelLv3(Map<String,Object> map);
}
