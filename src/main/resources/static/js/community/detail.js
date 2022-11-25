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



