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



let myBadgeService = (function(){

    function getBadgeList(param, callback, error){
        $.ajax({
            url: encodeURI("/myCompleteQuest/" + (param.page || 0)),
            type: "get",
            success: function (questDTOList,status, xhr) {
                if(callback){
                    callback(questDTOList);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    function getMonthlyBadgeList(param, callback, error){
        $.ajax({
            url: "/myCompleteQuest/badge",
            type: "get",
            success: function (questAchievementCountList,status, xhr) {
                if(callback){
                    console.log(questAchievementCountList);
                    callback(questAchievementCountList);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }
    return {getBadgeList: getBadgeList, getMonthlyBadgeList:getMonthlyBadgeList}
}) ();




