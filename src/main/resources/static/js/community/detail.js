const $detailBtn = $(".jJfiPr");
const $postdetail = $(".DescriptionSection__DescriptionContainer-sc-1vvzceq-0 ")

$detailBtn.click(function () {
    if($postdetail.hasClass("edqUZK")){
        $postdetail.removeClass("edqUZK").addClass("PvHzG");
        $(this).find("span").text("간략히");
        $(this).find("div").removeClass("QiNdM").addClass("dOJyMf")
    }else{
        $postdetail.removeClass("PvHzG").addClass("edqUZK");
        $(this).find("span").text("상세정보 더보기");
        $(this).find("div").removeClass("dOJyMf").addClass("QiNdM")
    }

})

const $updateAndDeleteModal = $(".loungeCardContentsComponents_loungeContentsMoreButtonBox__2jERo");
let modalCheck = false
$(".fOXNjA").on("click",function () {
    if(!modalCheck){
        $updateAndDeleteModal.css("display","inline-flex");
        modalCheck = true;
    }else{
        $updateAndDeleteModal.hide();
        modalCheck=false;
    }
})


// 모임 페이지 수정 페이지로 이동
$(".goUpdate").on("click", function(e){
    e.preventDefault();
    location.href = "/host/update?groupId=" + $(this).attr("href");
})

// 모임 페이지 삭제
$(".goDelete").on("click", function(e){
    e.preventDefault();
    location.href ="/host/deleteGroup?groupId=" + $(this).attr("href");
})

// 모임 페이지 다른 글로 이동
// 모임 상세 게시글로 화면 이동
$(".recommendDetail").on("click", function(e){
    e.preventDefault();
    location.href = "/host/read?groupId=" + $(this).attr("href");
})


// 커뮤니티 페이지 수정
$(".goUpdateCommunity").on("click", function(e){
    e.preventDefault();
    location.href = "/community/update?communityPostId=" + $(this).attr("href");
})

let thumbnailCheck = 0;

// 커뮤니티 페이지 삭제
$(".goDeleteCommunity").on("click", function(e){
    e.preventDefault();
    location.href ="/community/deleteBoard?communityPostId=" + $(this).attr("href");
})

$("#noticeWrap").on("click", function(){
    if($("#notice").attr("class") == "Accordion__Content-sc-1jd6vdl-3 zRWUs"){
        $("#notice").attr("class", "Accordion__Content-sc-1jd6vdl-3 bJFgwb");
    } else{
        $("#notice").attr("class", "Accordion__Content-sc-1jd6vdl-3 zRWUs");
    }
})
