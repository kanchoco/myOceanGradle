
const $filterBtn = $(".childFilter");
const $allBtn = $(".allFilter");

const $cook = $(".cookFilter");
const $exercise = $(".exerciseFilter")
const $movie = $(".movieFilter")
const $book = $(".bookFilter")
const $diary = $(".diaryFilter")

//요리
$cook.on("click",function () {
    if($(this).find("button").hasClass("fFBpBV")){
        $(this).find("button").removeClass('fFBpBV').addClass("kJEnf");
        $(this).find(".gRNDCb").show()
    }else{
        $(this).find("button").removeClass('kJEnf').addClass("fFBpBV");
        $(this).find(".gRNDCb").hide()
    }

    if($filterBtn.find(".kJEnf").length==10){
        allCheck()
    }else{
        allCheckCancel()
    }
})

//운동
$movie.on("click",function () {
    if($(this).find("button").hasClass("fFBpBV")){
        $(this).find("button").removeClass('fFBpBV').addClass("kJEnf");
        $(this).find(".gRNDCb").show()
    }else{
        $(this).find("button").removeClass('kJEnf').addClass("fFBpBV");
        $(this).find(".gRNDCb").hide()
    }
    if($filterBtn.find(".kJEnf").length==10){
        allCheck()
    }else{
        allCheckCancel()
    }
})

//영화
$exercise.on("click",function () {
    if($(this).find("button").hasClass("fFBpBV")){
        $(this).find("button").removeClass('fFBpBV').addClass("kJEnf");
        $(this).find(".gRNDCb").show()
    }else{
        $(this).find("button").removeClass('kJEnf').addClass("fFBpBV");
        $(this).find(".gRNDCb").hide()
    }
    if($filterBtn.find(".kJEnf").length==10){
        allCheck()
    }else{
        allCheckCancel()
    }
})

//책
$book.on("click",function () {
    if($(this).find("button").hasClass("fFBpBV")){
        $(this).find("button").removeClass('fFBpBV').addClass("kJEnf");
        $(this).find(".gRNDCb").show()
    }else{
        $(this).find("button").removeClass('kJEnf').addClass("fFBpBV");
        $(this).find(".gRNDCb").hide()
    }
    if($filterBtn.find(".kJEnf").length==10){
        allCheck()
    }else{
        allCheckCancel()
    }
})
//다이어리
$diary.on("click",function () {
    if($(this).find("button").hasClass("fFBpBV")){
        $(this).find("button").removeClass('fFBpBV').addClass("kJEnf");
        $(this).find(".gRNDCb").show()
    }else{
        $(this).find("button").removeClass('kJEnf').addClass("fFBpBV");
        $(this).find(".gRNDCb").hide()
    }
    if($filterBtn.find(".kJEnf").length==10){
        allCheck()
    }else{
        allCheckCancel()
    }
})

function allCheck(){
    $allBtn.find("button").removeClass('fFBpBV').addClass("kJEnf");
    $allBtn.find(".gRNDCb").show()
}

function allCheckCancel(){
    $allBtn.find("button").removeClass('kJEnf').addClass("fFBpBV");
    $allBtn.find(".gRNDCb").hide()
}



$allBtn.on("click",function () {
    
  if($(this).find("button").hasClass("fFBpBV")){
      allCheck();
      $filterBtn.each((i,item)=>{
          $(item).find("button").removeClass('fFBpBV').addClass("kJEnf");
          $(item).find(".gRNDCb").show()
      })
  }else{
      allCheckCancel();
      $filterBtn.each((i,item)=>{
          $(item).find("button").removeClass('kJEnf').addClass("fFBpBV");
          $(item).find(".gRNDCb").hide()
      })
  }

})



// $filterBtn.click(function () {
//     let text= $(this).text().trim();
//     if($(this).find("button").hasClass('fFBpBV')) {
//         $(this).find("button").removeClass('fFBpBV').addClass("kJEnf");
//         $('.gRNDCb').show();
//     }else{
//         $(this).empty();
//         $(this).append(`<div class="FilterOpenButton__Wrapper-sc-91gci-0 NVKNq">
//                     <button height="auto" color="initial" font-size="14px" font-weight="500" class="Button-bqxlp0-0 fFBpBV">`+text+`</button>
//                 </div>`)
//     }
// })






//모달

const $modal = $("#modal-root")
const $modalCancelBtn = $(".kXWQPc");
$(".lbwitP").click(function () {
    $("#modal-root").css("display","block");
})

$modalCancelBtn.click(function () {
    $modal.css("display","none");
})


//페이지 소개 모달

const $pageIntroduceModal = $("#modal-page");
const $pageIntroduceBtn = $(".cUtFFV");

$pageIntroduceBtn.click(function () {
    $pageIntroduceModal.css("display","block")
})


$(".pageIntroduce_modalContainer_1h").click(function (e) {
    if($(e.target).parents(".pageIntroduce_Container_1h").length < 1){
        $pageIntroduceModal.css("display","none");
    }
})


// 반응형
checkMedia()
$(window).resize(function(){
    if(window.innerWidth<785){
        $(".view__FeedListWrapper-sc-1fff32g-0").attr("class", "view__FeedListWrapper-sc-1fff32g-0 media");
        $(".Filter__FilterButtonWrapper-sc-1buujtg-1").attr("class", "Filter__FilterButtonWrapper-sc-1buujtg-1 categoryMedia");
        $("a.down785px").show();
        $("a.until785px").hide();
        // $(".writeBtn").parent().css("position", "relative");
        // $(".writeBtn").parent().css("right", "0px");
        if(window.innerWidth<594){
            $(".filter_wrapper_3l").css("content","center");
            $(".jIjcTX").css("margin", "");
            $(".categoryMedia >div").css("justify-content", "start");
        } else{
            $(".filter_wrapper_3l").css("content","start");
            $(".jIjcTX").css("margin", "auto");
            $(".categoryMedia >div").css("justify-content", "center");
        }
    } else{
        $(".view__FeedListWrapper-sc-1fff32g-0").attr("class", "view__FeedListWrapper-sc-1fff32g-0 ciajVR");
        $(".Filter__FilterButtonWrapper-sc-1buujtg-1").attr("class", "Filter__FilterButtonWrapper-sc-1buujtg-1 gRnEmw");
        $(".jIjcTX").css("margin", "");
        $("a.down785px").hide();
        $("a.until785px").show();
        $(".categoryMedia >div").css("justify-content", "start");
        // $(".writeBtn").parent().css("right", "-196px");
        // $(".writeBtn").parent().css("right", "29.5%");
        // $(".writeBtn").parent().css("position", "absolute");
    }
})

function checkMedia(){

    if(window.innerWidth<785){
        $(".view__FeedListWrapper-sc-1fff32g-0").attr("class", "view__FeedListWrapper-sc-1fff32g-0 media");
        $(".Filter__FilterButtonWrapper-sc-1buujtg-1").attr("class", "Filter__FilterButtonWrapper-sc-1buujtg-1 categoryMedia");
        $("a.down785px").show();
        $("a.until785px").hide();
        // $(".writeBtn").parent().css("position", "relative");
        // $(".writeBtn").parent().css("right", "0px");
        if(window.innerWidth<594){
            $(".filter_wrapper_3l").css("content","center");
            $(".jIjcTX").css("margin", "");
            $(".categoryMedia >div").css("justify-content", "start");
        } else{
            $(".filter_wrapper_3l").css("content","start");
            $(".jIjcTX").css("margin", "auto");
            $(".categoryMedia >div").css("justify-content", "center");
        }
    } else{
        $(".view__FeedListWrapper-sc-1fff32g-0").attr("class", "view__FeedListWrapper-sc-1fff32g-0 ciajVR");
        $(".Filter__FilterButtonWrapper-sc-1buujtg-1").attr("class", "Filter__FilterButtonWrapper-sc-1buujtg-1 gRnEmw");
        $(".jIjcTX").css("margin", "");
        $("a.down785px").hide();
        $("a.until785px").show();
        $(".categoryMedia >div").css("justify-content", "start");
        // $(".writeBtn").parent().css("right", "-196px");
        // $(".writeBtn").parent().css("right", "29.5%");
        // $(".writeBtn").parent().css("position", "absolute");
    }
}



/*화면 출력*/
let page = 1;
let pages= 0;

show();

// 화면 출력 함수 호출
function show() {
    communityService.getList({
        page: page
    }, getList);
}

function getList(communityPostDTO){
    let text ="";
    console.log(communityPostDTO.postList);
    communityPostDTO.postList.forEach(post => {
        // 작성자 프로필 사진, 닉네임
        text +=`<div><div class="Profile__Wrapper-sc-14xkqhl-0 ePpOcl CardFeed__StyledProfile-sc-1hnsnr8-0 kHXXhk">`;
        text +=`<div class="Profile__UserProfileWrapper-sc-14xkqhl-1 jHdfwa">`;
        text += `<div class="Profile__ProfileImageWrapper-sc-14xkqhl-2 kHBCa-D">`;
        text +=`<div class="Image__Wrapper-v97gyx-0 gDuKGF">`;

        <!--프로필 사진-->
        text +=`<div class="Fade__Wrapper-sc-1s0ipfq-0 koasSX" style="opacity: 1; display: block;">`;
        text +=`<div class="Ratio " style="display: block;">`;
        text +=`<div class="Ratio-ratio " style="height: 0px; position: relative; width: 100%; padding-top: 100%;">`;
        text+=`<div class="Ratio-content " style="height: 100%; left: 0px; position: absolute; top: 0px; width: 100%;">`;
        text +=`<img class="Image__StyledImage-v97gyx-1 hPRDQO" width="36" height="36" src="`;
        text +=`https://k.kakaocdn.net/dn/ofLuF/btrI2xhlGKt/iEB7z3GQsgboUCtneKHqSk/img_110x110.jpg`; // 프로필 사진 이미지 부분(나중에 교체)
        text +=`"></div></div></div></div></div></div>`;
        <!--작성자 아이디, 날짜-->
        text+=`<div class="Profile__UserTextWrapper-sc-14xkqhl-4 hopp"><p class="Profile__Name-sc-14xkqhl-3 jeiGkC">`;
        // 아이디
        text+= post.userNickName;
        text+=`</p><div class="Profile__Time-sc-14xkqhl-5 gqDWoy">`;
        //날짜
        text+= post.updatedDate;
        text+=`</div></div></div><button class="Profile__MoreButton-sc-14xkqhl-6 lbwitP">`;
        <!--도시락 버튼 + 신고 버튼-->
        text+=`<img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='4' height='17' viewBox='0 0 4 17'%3E %3Cpath fill='%23999' fill-rule='evenodd' d='M1.57 14a1.5 1.5 0 110 3 1.5 1.5 0 010-3zm0-7a1.5 1.5 0 110 3 1.5 1.5 0 010-3zm0-7a1.5 1.5 0 110 3 1.5 1.5 0 010-3z'/%3E %3C/svg%3E" alt=""></button></div>`;

        text+=`<div class="Ratio ImageBanner__Wrapper-dpw3a4-0 jZxoCa" style="display: block;">`;
        text+=`<div className = "Ratio-ratio " style = "height: 0px; position: relative; width: 100%; padding-top: 100%;">`;
        text+=`<div class="Ratio-content " style="height: 100%; left: 0px; position: absolute; top: 0px; width: 100%;">`
        text+=`<div class="FeedImage__Wrapper-sc-1g1oaf4-0 jGjBCL">`;
        text+=`<div class="Fade__Wrapper-sc-1s0ipfq-0 koasSX" style="opacity: 1; display: block;"><div><img src="`;
        // 썸네일 이미지
        text+=`C:/upload/community/` + post.communityFilePath + "/" + post.communityFileUuid + "_" + post.communityFileName;
        text+=`"></div></div></div></div></div></div>`;
        <!--본문 내용-->
        text+=`<a th:href="@{/templates/app/community/detail}">`; // 상세페이지로 가는 메소드인데 아직 없음
        text+=`<div class="Description__Wrapper-ogh69p-0 lhIpIh CardFeed__StyledDescription-sc-1hnsnr8-2 eOhuaj">`;
        text+=`<span class="SpanLineClamp-my36n9-0 Description__StyledSpanLineClamp-ogh69p-2 dwGwRt">`;
        // 본문 텍스트
        text+=post.communityTitle;
        text+=`</span><button class="Description__ExpandButton-ogh69p-1 djffyn"></button></div></a>`;
        // 좋아요, 댓글
        text+=`<div class="ActionGroup__Wrapper-sg91np-0 cXiDYn">`;
        text+=`<button type="button" class="ActionGroup__LikeButton-sg91np-1 jxmvcs">`;
        text+=`<img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='21' height='18' viewBox='0 0 21 18'%3E %3Cpath fill='%23F32D2D' d='M19.437 1.589C18.427.564 17.085 0 15.657 0s-2.77.564-3.78 1.589L10.5 2.986 9.124 1.59C8.114.564 6.77 0 5.344 0c-1.429 0-2.771.564-3.78 1.589-2.085 2.115-2.085 5.557 0 7.673l8.379 8.504c.148.15.348.234.557.234.209 0 .41-.084.557-.234l8.38-8.504c2.084-2.116 2.084-5.558 0-7.673z'/%3E %3C/svg%3E" alt="좋아요~"><span>`;
        // 좋아요 개수
        text+=post.communityLikeNumber;
        text+=`</span></button>`;
        text+=`<a class="ActionGroup__ReplyLink-sg91np-2 grCiwo" src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='21' height='18' viewBox='0 0 21 18'%3E %3Cpath fill='none' stroke='%234E5968' stroke-width='1.5' d='M20.25.75H.75v11.818h3.842v4.015l5.9-4.015h9.758V.75z'/%3E %3C/svg%3E" alt="댓글">`;
        text+=`<span>`;
        // 댓글
        text+=`0`;
        text+=`</span></a></div></div>`;
    })
    $(".view__FeedListWrapper-sc-1fff32g-0.ciajVR").html(text);
}

let communityService = (function() {
    function getList(param, callback, error) {
        $.ajax({
            url: "/community/list/" + (param.page || 0),
            type: "get",
            async: false,
            success: function (postDTO, status, xhr) {
                if (callback) {
                    callback(postDTO);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }
    return {getList: getList}
})();