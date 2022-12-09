

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
$(".ProductList__ProductWrapper-sc-1lo0q2y-0").on("click", ".detailGroup", function(e){
    e.preventDefault();
    location.href = "/host/read?groupId=" + $(this).attr("href");
})


let groupService = (function(){
    function getList(param, callback, error){
        $.ajax({
            url: encodeURI("/group/group/" + (param.page || 0) + "/"+ param.keyword),
            type: "get",
            async: false,
            success: function (groupDTO, status, xhr){
                if(callback){
                    callback(groupDTO);
                }
            },
            error: function (xhr, status, err){
                console.log("완전 실패");
                if(error){
                    error(err);
                }
            }
        });
    }
    return {getList: getList}
})();


// 페이징처리
$(".Wrapper__PaginationWrapper-fk4uv4-0").on("click", ".iItkLq", function(){

})