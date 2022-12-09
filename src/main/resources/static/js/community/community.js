
const $filterBtn = $(".childFilter");
const $allBtn = $(".allFilter");

const $cook = $(".cookFilter");
const $exercise = $(".exerciseFilter")
const $movie = $(".movieFilter")
const $book = $(".bookFilter")
const $diary = $(".diaryFilter")

let communityAr = new Array();
let communities = ['EXERCISE', 'COOK', 'MOVIE', 'BOOK', 'COUNSELING'];


$(document).on("change", function(){
    console.log("들어왔음");
    if($filterBtn.find(".kJEnf").length==0){
        communityAr.push(communities);
    }
})

//요리
$cook.on("click",function () {
    if($(this).find("button").hasClass("fFBpBV")){
        $(this).find("button").removeClass('fFBpBV').addClass("kJEnf");
        $(this).find(".gRNDCb").show();
        communityAr.push("COOK");
    }else{
        $(this).find("button").removeClass('kJEnf').addClass("fFBpBV");
        $(this).find(".gRNDCb").hide()
        communityAr = communityAr.filter((element) => element !=="COOK");
    }

    if($filterBtn.find(".kJEnf").length==10){
        allCheck();
    }else{
        allCheckCancel();
    }
})

//영화
$movie.on("click",function () {
    if($(this).find("button").hasClass("fFBpBV")){
        $(this).find("button").removeClass('fFBpBV').addClass("kJEnf");
        $(this).find(".gRNDCb").show();
        communityAr.push("MOVIE");
    }else{
        $(this).find("button").removeClass('kJEnf').addClass("fFBpBV");
        $(this).find(".gRNDCb").hide();
        communityAr = communityAr.filter((element) => element !=="MOVIE");
    }
    if($filterBtn.find(".kJEnf").length==10){
        allCheck();
    }else{
        allCheckCancel();
    }
})

//운동
$exercise.on("click",function () {
    if($(this).find("button").hasClass("fFBpBV")){
        $(this).find("button").removeClass('fFBpBV').addClass("kJEnf");
        $(this).find(".gRNDCb").show()
        communityAr.push("EXERCISE");
    }else{
        $(this).find("button").removeClass('kJEnf').addClass("fFBpBV");
        $(this).find(".gRNDCb").hide()
        communityAr = communityAr.filter((element) => element !=="EXERCISE");
    }
    if($filterBtn.find(".kJEnf").length==10){
        allCheck();
    }else{
        allCheckCancel();
    }
})

//책
$book.on("click",function () {
    if($(this).find("button").hasClass("fFBpBV")){
        $(this).find("button").removeClass('fFBpBV').addClass("kJEnf");
        $(this).find(".gRNDCb").show();
        communityAr.push("BOOK");
    }else{
        $(this).find("button").removeClass('kJEnf').addClass("fFBpBV");
        $(this).find(".gRNDCb").hide();
        communityAr = communityAr.filter((element) => element !=="BOOK");
    }
    if($filterBtn.find(".kJEnf").length==10){
        allCheck();
    }else{
        allCheckCancel();
    }
})
//다이어리 => 이걸 고민상담으로 바꿈
$diary.on("click",function () {
    if($(this).find("button").hasClass("fFBpBV")){
        $(this).find("button").removeClass('fFBpBV').addClass("kJEnf");
        $(this).find(".gRNDCb").show();
        communityAr.push("COUNSELING");
    }else{
        $(this).find("button").removeClass('kJEnf').addClass("fFBpBV");
        $(this).find(".gRNDCb").hide();
        communityAr = communityAr.filter((element) => element !=="COUNSELING");
    }
    if($filterBtn.find(".kJEnf").length===10){
        allCheck();

    }else{
        allCheckCancel();
    }
})

function allCheck(){
    $allBtn.find("button").removeClass('fFBpBV').addClass("kJEnf");
    $allBtn.find(".gRNDCb").show();
    console.log("들어오니?");
    communityAr = [];
    communityAr.push(communities);
}

function allCheckCancel(){
    $allBtn.find("button").removeClass('kJEnf').addClass("fFBpBV");
    $allBtn.find(".gRNDCb").hide();
}



$allBtn.on("click",function () {
    
  if($(this).find("button").hasClass("fFBpBV")){
      // console.log("들어오니?");
      allCheck();
      $filterBtn.each((i,item)=>{
          $(item).find("button").removeClass('fFBpBV').addClass("kJEnf");
          $(item).find(".gRNDCb").show();
      })
  }else{
      allCheckCancel();
      $filterBtn.each((i,item)=>{
          $(item).find("button").removeClass('kJEnf').addClass("fFBpBV");
          $(item).find(".gRNDCb").hide();
      })
      communityAr = [];
  }
})



//모달

const $modal = $("#modal-root")
const $modalCancelBtn = $(".kXWQPc");
$(".lbwitP").click(function () {
    $("#modal-root").css("display","block");
})

$modalCancelBtn.click(function () {
    $modal.css("display","none");
})

function modalOpen(){
    $("#modal-root").css("display","block");
    modalCheck = false;
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
$(document).ready(function(){
    checkMedia();
})

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


let communityLikeCheck = false;

let communityService = (function() {
    function getList(param, callback, error) {
        console.log(param);
        $.ajax({
            url: "/community/list/",
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
    function getListNotUser(param, callback, error) {
        console.log(param);
        $.ajax({
            url: "/community/list-not-user/",
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

    function infiniteScroll(page, callback, error){
        $.ajax({
            url: "/community/scroll/" + page,
            type: "get",
            success: function(boards, status, xhr){
                if(callback){
                    callback(boards);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function addLike(communityPostId, callback, error){
        $.ajax({
            url: "/like/addLike/"  + communityPostId,
            type: "post",
            success: function(result, status, xhr){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function minusLike(communityPostId, callback, error){
        $.ajax({
            url: "/like/minusLike/"  + communityPostId,
            type: "post",
            success: function(result, status, xhr){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function checkLike(communityPostId, callback, error){
        $.ajax({
            url: "/like/checkLike/" + communityPostId,
            type: "post",
            success: function(result, status, xhr){
                if(callback){
                    callback(result);
                }
                communityLikeCheck = result;
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function filterCheck(communityCategories, callback, error){
        $.ajax({
            url: "/community/list-filter/" + communityCategories,
            type: "get",
            success: function(communityCategories, status, xhr){
                if(callback){
                    callback(communityCategories);
                }
            }, error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }


    return {getList: getList, getListNotUser: getListNotUser, infiniteScroll: infiniteScroll, addLike: addLike, minusLike: minusLike, checkLike: checkLike, filterCheck: filterCheck}
})();



let page = 1;

$(window).scroll(function(){
    if($(window).scrollTop() * 1.001 >= $(document).height() - $(window).height()){
        showCheck=true;
        communityService.infiniteScroll(page, getList);
        page++;
    }
});


/*댓글 창 이동하기*/
$(document).ready(function(){
    $(".goReplyBoard").on("click", function(e){
        e.preventDefault();
        location.href = "/reply/index?communityPostId=" + $(this).attr("href");
    })
})



/*좋아요 누를 때*/
$(document).ready(function(){
    $(".ActionGroup__LikeButton-sg91np-1.jxmvcs").on("click", function(e){
        e.preventDefault();
        if($("input[name='userId']").val()==""){
            alert("로그인 후 이용하실 수 있습니다.")
            return;
        }
        let communityPostId = $(this).attr("href");

        if($(".checkLike").attr("class").includes("likePlus")){
            communityService.addLike(
                communityPostId,
                function(){
                    console.log("좋아요 성공");
                }
            )
            $(".checkLike").attr("src", "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='21' height='18' viewBox='0 0 21 18'%3E %3Cpath fill='%23F32D2D' d='M19.437 1.589C18.427.564 17.085 0 15.657 0s-2.77.564-3.78 1.589L10.5 2.986 9.124 1.59C8.114.564 6.77 0 5.344 0c-1.429 0-2.771.564-3.78 1.589-2.085 2.115-2.085 5.557 0 7.673l8.379 8.504c.148.15.348.234.557.234.209 0 .41-.084.557-.234l8.38-8.504c2.084-2.116 2.084-5.558 0-7.673z'/%3E %3C/svg%3E");
            $(".checkLike").attr("class", "checkLike likeCancel");

            let likeNumber = $(".likeNumber").text();

            $(".likeNumber").text(++likeNumber);
        } else{
            communityService.minusLike(
                communityPostId,
                function(){
                    console.log("좋아요 취소 성공");
                }
            )

            $(".checkLike").attr("src", "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='21' height='18' viewBox='0 0 21 18'%3E %3Cpath fill='none' stroke='%234E5968' stroke-width='1.5' d='M15.657.75c-1.226 0-2.379.485-3.246 1.365l-1.91 1.94-1.912-1.94C7.722 1.235 6.57.75 5.343.75s-2.378.485-3.245 1.365C1.198 3.028.75 4.227.75 5.425c0 1.199.448 2.398 1.348 3.31l8.425 8.504 8.379-8.504c.9-.912 1.348-2.111 1.348-3.31 0-1.198-.448-2.397-1.347-3.31-.867-.88-2.02-1.365-3.246-1.365z'/%3E %3C/svg%3E");
            $(".checkLike").attr("class", "checkLike likePlus");

            let likeNumber = $(".likeNumber").text();
            console.log("들어옴");
            $(".likeNumber").text(--likeNumber);
        }
    });
});

let showCheck = true;

// 커뮤니티 카테고리 동적쿼리
$(".jJIWoq").on("click", function(){
    showCheck = false;
    console.log("communityAr: " + communityAr);
    console.log($filterBtn.find(".kJEnf").length);
    // communityService.filterCheck(
    //     communityAr,
    //     function(){
    //         checkUser();
    //     });

    filteringShow();
});

function filteringShow(){
    communityService.filterCheck(
        communityAr, getList);
}
