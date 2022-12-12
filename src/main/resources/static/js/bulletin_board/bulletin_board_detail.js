

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