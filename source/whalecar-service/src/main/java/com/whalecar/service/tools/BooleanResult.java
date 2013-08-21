package com.whalecar.service.tools;

 /**
 * User: ruihuang
 * Date: 13-8-21
 * Time: 下午1:35
 */
public class BooleanResult {

     public BooleanResult(Boolean processResult){
         this.processResult = processResult;
     }

     private Boolean processResult;

     public Boolean getProcessResult() {
         return processResult;
     }

     public void setProcessResult(Boolean processResult) {
         this.processResult = processResult;
     }
 }
