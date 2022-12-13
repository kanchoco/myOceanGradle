
$("article div.LinkAccordion__AccordionContainer-sc-1o112j5-1.cJPEO").on("click", "#noticeWrap", function(){
    if($("#notice").attr("class")== "Accordion__Content-sc-1jd6vdl-3 zRWUs"){
        $("#notice").attr("class", "Accordion__Content-sc-1jd6vdl-3 bJFgwb");
    } else{
        $("#notice").attr("class", "Accordion__Content-sc-1jd6vdl-3 zRWUs");
    }
})

$("#refundWrap").on("click", function(){
    if($("#refund").attr("class")== "Accordion__Content-sc-1jd6vdl-3 zRWUs"){
        console.log("열림");
        $("#refund").attr("class", "Accordion__Content-sc-1jd6vdl-3 bJFgwb");
    }else{
        console.log("닫힘");
        $("#refund").attr("class", "Accordion__Content-sc-1jd6vdl-3 zRWUs");
    }
})


// 참여하기
$(".joinGroup").on("click", function(){
    $.ajax({
        url: "/host/group-member/" + $(".goUpdate").attr("href"),
        type: "post",
        async: false,
        success: function(result, status, xhr) {
            console.log("멤버 등록 성공");
        },
        error: function(xhr, status, err){
            if(error){
                error(err);
            }
        }
    });

    alert("참여 완료되었습니다.");
    location.href= "/host/group-list";
})

/*모임 신청 취소*/
$(".cancelGroup").on("click", function(){
    $.ajax({
        url: "/host/group-member-delete/" + $(".goUpdate").attr("href"),
        type: "post",
        async: false,
        success: function(result, status, xhr) {
            console.log("멤버 삭제 완료");
        },
        error: function(xhr, status, err){
            if(error){
                error(err);
            }
        }
    })

    alert("모임 참여가 취소되었습니다.");
    location.href= "/host/group-list";
})