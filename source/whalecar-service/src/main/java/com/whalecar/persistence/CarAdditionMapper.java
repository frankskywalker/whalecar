package com.whalecar.persistence;

import com.whalecar.domain.CarAddition;

import java.util.List;

/**
 * Created by ruihuang on 13-11-28.
 */
public interface CarAdditionMapper {

    public List<CarAddition> selectAll();

    public CarAddition selectById(String id);
}
