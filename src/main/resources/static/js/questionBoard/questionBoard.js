
$contents = $(".FAQEntity__TitleWrapper-cpxlp5-0");
$category = $(".TabLabels__Tab-sc-1i2f4oa-1");

/*FAQEntity__TitleWrapper-cpxlp5-0 gvlnCr
FAQEntity__TitleWrapper-cpxlp5-0 ldvBHS*/




//질문 누르면 답변 나오게 하기
$contents.on("click", function(){
    if($(this).attr("class").includes("gvlnCr")){
        $(this).attr("class", "FAQEntity__TitleWrapper-cpxlp5-0 ldvBHS");
        $(this).next().hide();
    } else{
        $(".gvlnCr").next().hide();
        $contents.attr("class", "FAQEntity__TitleWrapper-cpxlp5-0 ldvBHS");
        $(this).attr("class", "FAQEntity__TitleWrapper-cpxlp5-0 gvlnCr");
        $(this).next().show();
    }
})

// //화면 안깨지게..
// function resizeApply() {
//     var minWidth = 769;
//     var body = document.getElementsByTagName('body')[0];
//     if (window.innerWidth < minWidth) { body.style.zoom = (window.innerWidth / minWidth); }
//     else body.style.zoom = 1;
// }
//
// window.onload = function() {
//     window.addEventListener('resize', function() {
//         resizeApply();
//     });
// }

// 카테고리 변경
$category.on("click", function(){
    if($(this).attr("class").includes("eCnPke")){
        $category.attr("class", "TabLabels__Tab-sc-1i2f4oa-1 eCnPke");
        $(this).attr("class", "TabLabels__Tab-sc-1i2f4oa-1 djWtyM");
    }
})