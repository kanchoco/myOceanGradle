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


var Target = document.getElementById("clock");

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


// let $accountModalBtn =$(".account-modal");
let temp = 0;
let $tr = $("#info_table").find('tr');
let accountCondition = null;

// 관리 버튼을 클릭했을때
$tr.find('.more').click(function () {
    if(temp == 0){
        temp++;
        //    상태의 html을 보고 계정 정지/계정 복구가 결정됨
        //    만약 상태가 정지라면
        if ($(this).parent().prev().find('.banned').length == 1) {
            //모달창은 계정 복구로 나옴
            $(this).parent().append("<div class=\"account-modal\">\n" +
                "    <button class=\"account-modal-button\">계정 복구</button>\n" +
                "</div>")

            $(this).next().find('.account-modal-button').click(function () {

                $(this).parent().parent().prev().find('.banned').removeClass('banned').addClass('active');
                $(this).parent().parent().prev().find('span').text("정상")
                $(this).closest('.account-modal').remove()
                $(this).remove()
                temp--;
            })
        } else if ($(this).parent().prev().find('.banned').length == 0) {
            $(this).parent().append("<div class=\"account-modal\">\n" +
                "    <button class=\"account-modal-button\">계정 정지</button>\n" +
                "</div>")
            $(this).next().find('.account-modal-button').click(function () {
                $(this).parent().parent().prev().find('.active').removeClass('active').addClass('banned');
                $(this).parent().parent().prev().find('span').text("정지")
                $(this).closest('.account-modal').remove()
                $(this).remove()
                temp--;
            })
        }

    }
    else{
        $(this).find('.account-modal-button').remove();
        $(this).next().remove()
        temp--;
    }
})

// 하나 클릭하면 나머지는 색 없어짐
// 상단 필터 클릭시 색 변화

$('.filter').children('span').click(function(){
    $(this).siblings().removeClass('active-filter')
    $(this).addClass('active-filter');
})