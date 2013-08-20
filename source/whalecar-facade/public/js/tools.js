/**
 * User: ruihuang
 * Date: 13-8-20
 * Time: 上午10:32
 */
function formValidate(formId,type){
    $.post("/validator?type="+type, $("#formId").serialize())
        .done(function(result) {
           alert(result);
        });
}