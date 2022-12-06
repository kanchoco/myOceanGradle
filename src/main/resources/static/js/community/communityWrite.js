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

let text ="";

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


/*썸머노트*/
// // 서머노트에 text 쓰기
// $('#summernote').summernote('insertText', 써머노트에 쓸 텍스트);

$(document).ready(function() {
    function sendFile(file){
        var data = new FormData();
        data.append("file",file);
        $.ajax({
            url: "/host/summernote/",
            type: "POST",
            enctype: 'multipart/form-data',
            data: data,
            cache: false,
            contentType : false,
            processData : false,
            success: function(image){
                /*컨트롤러를 통해 절대경로로 이미지가 저장되면, 서머노트에 해당 이미지가 출력된다.*/
                $('#summernote').summernote('insertImage',image);
            },
            error: function(e){console.log(e);}
        });
    }

    // summernote
    $('#summernote').summernote({
        height :500,
        minHeight:null,
        maxHeight:1000,
        toolbar: [
            ['fontname', ['fontname']],
            ['fontsize', ['fontsize']],
            ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
            ['color', ['forecolor','color']],
            ['table', ['table']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['insert',['picture']],
        ],
        fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
        fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
        focus:true,
        lang : "ko-KR",
        callbacks: {
            /*이미지가 업로드될 때 sendFile 함수를 실행한다.*/
            onImageUpload : function(files){
                sendFile(files[0]);
            }
        }
    });

    /*서머노트 api가 켜지면 생기는 클래스*/
    $(".note-editable").html($('input[name=groupContent]').val());

    /*서머노트에 작성된 값이 변할 때*/
    $('#summernote').on('summernote.change', function(we, contents, $editable) {
        $('input[name=groupContent]').attr('value', $(".note-editable").html());
    });
}); //ready


let communitySave = (function(){
    function add(communityContents, callback, error){
        $.ajax({
            url: "/community/index",
            async: false,
            type: "post",
            data: JSON.stringify(communityContents),
            contentType: "application/json; charset=utf-8",
            success: function(result, status, xhr){
                if(callback){
                    callback(result);
                }
                alert("등록 완료되었습니다.");
                location.href="/community/index";
            },
            error: function(xhr, status, err)
            {
                if(error){
                    error(err);
                }
            }
        });
    }
    return {add: add}
})();

// 게시글 작성 진행 후 등록 버튼 눌렀을 때
$(".Button-bqxlp0-0.fFBpBV").on("click", function(e){
    e.preventDefault();

    // 카테고리 설정
    let category = $("input[name='post_filter_input']").val();
    switch(category) {

        case "영화":
            category="MOVIE";
            break;
        case "요리":
            category="COOK";
            break;
        case "책":
            category="BOOK";
            break;
        case "고민":
            category="COUNSELING";
            break;
        case "영화":
            category="MOVIE";
            break;
        case "운동":
            category="EXERCISE";
            break;
    }

    /*작성 내용*/
    if(category != "DIARY"){
        communitySave.add({
            communityCategory : category,
            communityContent : $(".note-editable").html(),
            communityTitle: $(".SocialFeedPage__Title-ky5ymg-2.gVPyuz").val()
        })
    }

});