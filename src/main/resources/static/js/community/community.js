
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

let modalCheck = true;
function modalOpen(){
    if(modalCheck){
        $("#modal-root").css("display","block");
        modalCheck = false;
    } else{
        $modal.css("display","none");
        modalCheck = true;
    }
}

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


let communityService = (function() {
    function getList(param, callback, error) {
        $.ajax({
            url: "/community/list/" + (param.page || 0),
            type: "get",
            async: false,
            success: function (communityPostDTO, status, xhr) {
                if (callback) {
                    callback(communityPostDTO);
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



// 세부 게시글로 들어가기
// $(".view__FeedListWrapper-sc-1fff32g-0.ciajVR").on("click", $(".goCommunity"), function(e){
//     e.preventDefault();
//     console.log($(this));
//     // location.href = "/community/read?communityPostId=" + $(this).attr("href");
// })



