let filterCheck = false;

//모달창열기
$(".badgeList").on("click", function(){
    if(!filterCheck){
        $(".BottomSheet__Container-e2nchk-0.cMKqKB").show();
        filterCheck = true;
    }
})

//x버튼 눌러서 모달창 닫기
$(".BottomSheet__CloseButton-e2nchk-6.fTfJGA").on("click", function(){
    $(".BottomSheet__Container-e2nchk-0.cMKqKB").css("display", "none");
    filterCheck = false;
})


