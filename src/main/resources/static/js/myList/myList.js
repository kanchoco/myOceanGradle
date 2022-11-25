const $categoryMedia = $(".list_filter_wrapper");
const $categoryOriginal = $("#list_filter_wrapper");

// 카테고리 크기 미디어쿼리
checkWidth();

$(window).resize(function(){
    if(window.innerWidth<867){
        $categoryOriginal.hide();
        $categoryMedia.show();
    } else{
        $categoryOriginal.show();
        $categoryMedia.hide();
    }
})

function checkWidth(){
    if(window.innerWidth<867){
        $categoryOriginal.hide();
        $categoryMedia.show();
    } else{
        $categoryOriginal.show();
        $categoryMedia.hide();
    }
}