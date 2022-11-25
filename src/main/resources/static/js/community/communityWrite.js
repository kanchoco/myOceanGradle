//첫번째 셀렉트 박스의 리스트들 의 모임
const $postFilterLi = $(".post_filter li");
//다이어리 필터
const $diaryFilter = $(".diary_filter_1h");
//다이어리 필터안 리스트들의 모임
const $diaryFilterLi = $(".diary_filter_1h li");
//교환 필터
const $exchangeFilter = $(".exchange_filter_1h");
//교환 필터안 리스트들의 모임
const $exchangeFilterLi = $(".exchange_filter_1h li");


$postFilterLi.click(function () {
    var text = $(this).text();
    $(this).parent().find(".post_filter_input").val(text);
    $(this).parent().parent().removeClass('active');
    $(this).parent().prev().text(text);

    if(text=='일기'){
        $diaryFilter.show()
    }else{
        $diaryFilter.hide()
        $exchangeFilter.hide();
        $(".diary_filter_1h").find(".label").text("공개")
        $(".diary_filter_1h").find(".diary_filter_input").val("공개")
    }
})


$diaryFilterLi.click(function(){
    var text = $(this).text();
    $(this).parent().find(".diary_filter_input").val(text);
    $(this).parent().parent().removeClass('active');
    $(this).parent().prev().text(text);
    if(text=='비공개'){
        $exchangeFilter.show();
    }else{
        $exchangeFilter.hide();
    }
})


$exchangeFilterLi.click(function () {
    var text = $(this).text();
    $(this).parent().find(".exchange_filter_input").val(text);
    $(this).parent().parent().removeClass('active');
    $(this).parent().prev().text(text);

})

checkMedia()
$(window).resize(function(){
    if(window.innerWidth<650){
      $(".registerBtn_1h").hide()
        $(".until650px").show()
    } else{
        $(".registerBtn_1h").show()
        $(".until650px").hide()
    }
})

function checkMedia(){

    if(window.innerWidth<650){
        $(".registerBtn_1h").hide()
        $(".until650px").show()
    } else{
        $(".registerBtn_1h").show()
        $(".until650px").hide()
    }
}
