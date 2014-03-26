package com.whalecar.service.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * User: ruihuang
 * Date: 13-8-21
 * Time: 下午1:35
 */
public class BooleanResult {

     public BooleanResult(Boolean processResult){
         this.processResult = processResult;
     }

     public BooleanResult(Boolean processResult,String errorMsg){
         this.processResult = processResult;
         this.errorMsg = errorMsg;
     }

     /**
      * 处理是否成功
      */
     private Boolean processResult;

     /**
      * 错误信息
      */
     private String errorMsg;

     /**
      * 处理结果上下文
      *
      * @return
      */
     private Map<String,Object> resultMap = new HashMap<String,Object>();

     public Boolean getProcessResult() {
         return processResult;
     }

     public void setProcessResult(Boolean processResult) {
         this.processResult = processResult;
     }

     public String getErrorMsg() {
        return errorMsg;
    }

     public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

     public Map<String, Object> getResultMap() {
        return resultMap;
    }
}
