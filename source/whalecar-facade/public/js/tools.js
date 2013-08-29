/**
 * User: ruihuang
 * Date: 13-8-20
 * Time: 上午10:32
 */
function formValidate(formId,type,callback){
    $.post("/validator?type="+type + "&formId=" + formId, $("#" + formId).serialize())
        .done(function(ValidateResult) {
            callback(processResult(formId,type,ValidateResult));
        });
}

function processResult(formId,type,ValidateResult){
    //1.清理之前的提示
    cleanAllTip(formId);
    //2.根据结果添加提示
    if(!ValidateResult){
        //没有验证错误，返回true
        return true;
    }
    else{
        //验证有错误，返回false，并添加提示
        $.each(ValidateResult,function(index,value){
            addTip(formId,value);
        });
        return false;
    }
}

function cleanAllTip(formId){
    $("#" + formId + " span.help-inline").remove();
    $("#" + formId + " div.control-group").removeClass("error");
}

function addTip(formId,error){
    $("#" + formId + " #" + error.param).after("<span class='help-inline'>" + error.msg + "</span>");
    $("#" + formId + " #" + error.param).parent().parent().addClass("error");
}