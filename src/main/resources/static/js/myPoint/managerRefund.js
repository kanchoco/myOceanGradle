function refundOkPay(){
    var refundPointId=$("input[name='requestRefundPointId']").val();
    var refundUserId=$("input[name='requestRefundUser']").val();
    let formData={"requestRefundPointId":refundPointId,"requestRefundUser":refundUserId};
    $.ajax({
        url:"managerRefund",
        type:"post",
        headers:{"Content-Type":"application/json"},
        data:JSON.stringify(formData),
        dataType:"text",
        success:function(result){
            console.log(result);
            if(result=="success");
            location.reload(true);
        },
        error:function(status,error){
            console.log(status,error);
        }
    })
}