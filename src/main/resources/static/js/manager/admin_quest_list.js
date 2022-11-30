let Target = document.getElementById("clock");
let temp = 0;
// html dom 이 다 로딩된 후 실행된다.
$(document).ready(function () {
    // menu 클래스 바로 하위에 있는 a 태그를 클릭했을때
    $("#side_menubar>li").click(function () {
        var submenu = $(this).children("ul");


        // submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
        if (submenu.is(":visible")) {
            submenu.slideUp();
        } else if (!submenu) {
        } else {
            submenu.slideDown();

        }
    });
});




function clock() {
    var time = new Date();

    var month = time.getMonth();
    var date = time.getDate();
    var day = time.getDay();
    var week = ['일', '월', '화', '수', '목', '금', '토'];

    var hours = time.getHours();
    var minutes = time.getMinutes();
    var seconds = time.getSeconds();

    Target.innerText =
        `${month + 1}월 ${date}일 ${week[day]}요일 ` +
        `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;

}

clock();
setInterval(clock, 1000); // 1초마다

$('#quest-register-wrapper').hide()

$('.filter span').click(function (){
    if(temp === 0) {
        temp++;
        $('#quest-register-wrapper').show()
    }else{
        temp--;
        $('#quest-register-wrapper').hide();

    }

})

let moreButtonTemp = 0;
let $tr = $(".info_table").find('tr');
console.log($tr)
console.log($tr.find('.more'))

$tr.find('.more').click(function () {
    if (moreButtonTemp === 1) {
        moreButtonTemp--;
        $(this).parent().children('.account-modal').remove();
    } else {
        moreButtonTemp++;
        //수정 모달 버튼 띄우기
        $(this).parent().append("<div class=\"account-modal\">\n" +
            "    <button class=\"account-modal-button\">수정</button>\n" +
            "</div>")

        //수정 모달 버튼 클릭시
        $(this).next().find('.account-modal-button').click(function () {
            //상단에 수정창 만들기
            $('#quest-register-wrapper').show();
            //수정창에 수정할 정보 넣기
            // $('.register td:nth-child(1)').attr("value", $(this).closest('tr').children('td:eq(1)').text())
            console.log($(this).closest('tr').children('td:eq(1)').text())
            $('.register td:nth-child(1) input').val($(this).closest('tr').children('td:eq(1)').text())
            $('.register td:nth-child(2) input').val($(this).closest('tr').children('td:eq(2)').text())
            $('.register td:nth-child(3) input').val($(this).closest('tr').children('td:eq(3)').text())
            $('.register td:nth-child(4) input').val($(this).closest('tr').children('td:eq(4)').text())
            $('.register td:nth-child(5) input').val($(this).closest('tr').children('td:eq(5)').text())
            $('#preview').attr('src', $(this).closest('tr').children('td:eq(6)').find('img').attr('src'))
            //수정 모달창과 버튼 삭제
            $(this).closest('.account-modal').remove()
            $(this).remove()
            moreButtonTemp--;

        })
    }
})

$('.close').click(function (){
    $('#quest-register-wrapper').hide();
    $('.register td:nth-child(1) input').val('')
    $('.register td:nth-child(2) input').val('')
    $('.register td:nth-child(3) input').val('')
    $('.register td:nth-child(4) input').val('')
    $('#preview').attr('src', '/imgin/admin_file.png')
    if(temp === 1){
        temp--;}else if (moreButtonTemp === 1){
        moreButtonTemp--;
    }
})
$('.regist').click(function (){
    alert("퀘스트가 등록됐습니다.")
    $('#quest-register-wrapper').hide();
    $('.register td:nth-child(1) input').val('')
    $('.register td:nth-child(2) input').val('')
    $('.register td:nth-child(3) input').val('')
    $('.register td:nth-child(4) input').val('')
    $('#preview').attr('src', '/imgin/admin_file.png')
    if(temp === 1){
        temp--;}else if (moreButtonTemp === 1){
        moreButtonTemp--;
    }

})

// 첨부파일 이미지 클릭시 파일 찾기 창 열림
function onClickUpload(){
    let fileInput = $('input[type = file]')
    fileInput.click();
}


// 뱃지 이미지 썸네일 띄우기
$(".badge").on("change", function(event) {
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.onload = function(e) {
        $("#preview").attr("src", e.target.result);
    }
    reader.readAsDataURL(file);
});