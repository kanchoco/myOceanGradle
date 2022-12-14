let filterCheck = false;
function show(){
    console.log("show 들어옴");
    getQuestList(getQuestListContent)
}
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


function getQuestList(callback, error){
    console.log("getQuestList 들어옴");

    $.ajax({
        url: "/myCompleteQuest/myCollection",
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

function getQuestListContent(questDTOList){
    let text = "";
    console.log("getQuestListContent 들어옴");

    questDTOList.forEach(questDTO => {
        questFileName = questDTO.questFileName;
        questFilePath = questDTO.questFilePath;
        questFileUuid = questDTO.questFileUuid;
        questFileSize = questDTO.questFileSize;
        let imageSrc = "/quest/display?fileName=" + questFilePath + "/" + questFileUuid + "_" + questFileName;
        text += `<div class="MyPage__FripBadgeContainer-sc-8hrgc7-2 jBoTV">`
        text += `<button class="FripBadge__Container-sc-1symmx5-0 juuNtL">`
        text += `<div class="FripBadge__BadgeMark-sc-1symmx5-1 eYNaUp">`
        text += `<img src="data:image/svg+xml,%3Csvg width='12' height='16' viewBox='0 0 12 16' fill='none' xmlns='http://www.w3.org/2000/svg'%3E %3Cpath d='M7.66667 8.39317L8.08667 10.2132C8.15333 10.4998 7.84 10.7265 7.58667 10.5732L6 9.61317L4.40667 10.5732C4.15333 10.7265 3.84 10.4998 3.90667 10.2132L4.33333 8.39984L2.93333 7.19317C2.70667 6.99984 2.82667 6.63317 3.12 6.6065L4.97333 6.4465L5.69333 4.73984C5.80667 4.4665 6.19333 4.4665 6.30667 4.73984L7.02667 6.43984L8.88 6.59984C9.17333 6.6265 9.29333 6.99317 9.06667 7.1865L7.66667 8.39317ZM0.793333 2.97984C0.313333 3.19317 0 3.67317 0 4.19984V7.33317C0 11.0332 2.56 14.4932 6 15.3332C9.44 14.4932 12 11.0332 12 7.33317V4.19984C12 3.67317 11.6867 3.19317 11.2067 2.97984L6.54 0.906504C6.19333 0.753171 5.8 0.753171 5.46 0.906504L0.793333 2.97984Z' fill='white'/%3E %3C/svg%3E" alt="badge icon">`
        text += `<strong class="FripBadge__BadgeTitle-sc-1symmx5-2 ibIiPD">`+questDTO.questCategory+`</strong>`
        text += `</div>`
        text += `<div class="FripBadge__TitleContainer-sc-1symmx5-3 ksIvnE">`
        text += `<strong class="FripBadge__Title-sc-1symmx5-4 hOVumI">`+questDTO.questName+`</strong>`
        text += `<div class="FripBadge__SubTitleContainer-sc-1symmx5-5 ezRosO">`
        text += `<span class="FripBadge__SubTitle-sc-1symmx5-10 kdLIth">`+questDTO.questContent+`</span>`
        text += `</div>`
        text += `</div>`
        text += `<img width="55" height="55" src=`+ imageSrc +`>`
        text += `</button>`
        text += `</div>`
    })
    $("#questWrap").html(text)
    }

