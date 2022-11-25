
//모달 취소 버튼
const $modalCancelBtn = $(".kXWQPc");
//모달 창
const $modal = $("#modal-root")
// 댓글 삭제 버튼
const $deleteBtn = $(".kosXvh");
//텍스트에리아
const $textarea = $(".iHQYSd");
//등록 버튼
const $registerBtn = $(".fWXEgN");

//모달창 끄기
$modalCancelBtn.click(function () {
    $modal.css("display","none");
})

//댓글 삭제 버튼 클릭시 모달창 팝업
$deleteBtn.click(function () {
    $modal.css("display","block");
})
$textarea.keyup(function (e) {
    let content = $(this).val();
    if(content.length==0 || content==''){
        $registerBtn.css("cursor","not-allowed")
    }else{
        $registerBtn.css("cursor","pointer")
    }
})


