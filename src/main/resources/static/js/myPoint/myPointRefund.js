function refundPay() {
    $.ajax({
        url: "refundRequest",
        type: "post",
        headers: {"Content-Type": "application/json"},
        data: $("input[name='requestRefund']").val(),
        dataType: "text",
        success: function (result) {
            if (result == "success") {
                console.log(result);
                alert("포인트 환불신청이 완료되었습니다.");
                location.href = "/myPoint/index";
            }
        }, error(status, error) {
            console.log(status, error);
        }
    })
}