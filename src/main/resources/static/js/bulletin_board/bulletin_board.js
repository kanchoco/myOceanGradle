

checkMedia();

$(window).resize(function(){
    if(window.innerWidth<769){
        $(".ProductList__ProductWrapper-sc-1lo0q2y-0").attr("class", "ProductList__ProductWrapper-sc-1lo0q2y-0 media");
        $(".fFPQpk").css("margin", "20px 10px 30px");

        //채팅 아이콘 위치 조절
        $(".chattingWrap2").css("bottom","95px");
    } else{
        $(".ProductList__ProductWrapper-sc-1lo0q2y-0").attr("class", "ProductList__ProductWrapper-sc-1lo0q2y-0 jjtvVw");
        $(".fFPQpk").css("margin", "20px 0px 30px");

        //채팅 아이콘 위치 조절
        $(".chattingWrap2").css("bottom","30px");
    }
}).resize();

function checkMedia(){
    if(window.innerWidth<769){
        $(".ProductList__ProductWrapper-sc-1lo0q2y-0").attr("class", "ProductList__ProductWrapper-sc-1lo0q2y-0 media");
        $(".fFPQpk").css("margin", "20px 10px 30px");
    } else{
        $(".ProductList__ProductWrapper-sc-1lo0q2y-0").attr("class", "ProductList__ProductWrapper-sc-1lo0q2y-0 jjtvVw");
        $(".fFPQpk").css("margin", "20px 0px 30px");
    }
}


// 모임 상세 게시글로 화면 이동
$(".detailGroup").on("click", function(e){
    e.preventDefault();
    location.href = "/host/read" + "&groupId=" + $(this).attr("href");
})