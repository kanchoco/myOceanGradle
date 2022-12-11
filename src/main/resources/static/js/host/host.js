/* host.html */

// 탭에 따라 변경하는 부분들
$('#__BVID__287___BV_modal_outer_').css('display', '');
$('.place').css('display', '');
$('.image-header').css('display', '');
$('.img-box').css('display', '');
$('.explain').css('display', '');
$('.noticeEx').css('display', '');




const date = new Date();

// 캘린더에 들어갈 데이터
let data = [];

$(document).ready(function(){
    showList();

})
function showList(){
    groupSave.listDate(groupId,listDate);

}

// 모임 리스트에 저장 후 groupScheduleDTO에 모임의 날짜를 각각 저장해준다.
// 이후 콜백함수를 통해 리스트를 출력
function listDate(groupScheduleDTO){
    data = [];
    groupScheduleDTO.forEach(schedule => {
        let simpleDate = schedule.groupScheduleStartTime.split("T")[0];
        let simpleTime = schedule.groupScheduleStartTime.split("T")[1].split(":");
        let startTime = simpleTime[0] + ':' + simpleTime[1];

        data.push({
            date : simpleDate,
            startTime : startTime
        });
    });
    makeCalendar(date);
}


// pad method ( 2 -> 02 )
Number.prototype.pad = function() {
    return this > 9 ? this : '0' + this;
}


const makeCalendar = (date) => {

    const calendarList = data.reduce(
        (acc, v) =>
            ({ ...acc, [v.date]: [...(acc[v.date] || []), v.startTime] })
        , {}
    );
    const currentYear = new Date(date).getFullYear();
    const currentMonth = new Date(date).getMonth() + 1;

    const firstDay = new Date(currentYear, currentMonth - 1, 1).getDay();
    const lastDay = new Date(currentYear, currentMonth, 0).getDate();

    const limitDay = firstDay + lastDay;
    const nextDay = Math.ceil(limitDay / 7) * 7;

    let htmlDummy = '';
    let count = 0;

    htmlDummy += `<tr data-v-1e397a0c="" class="week">`;
    for (let i = 0; i < firstDay; i++) {
        htmlDummy += `<td data-v-1e397a0c="">`;
        htmlDummy += `<div data-v-3655f65c="" data-v-1e397a0c="" class="day">`;
        htmlDummy += `<div data-v-3655f65c="" class="clearfix px-2 pt-2">`;
        htmlDummy += `<div data-v-3655f65c="" class="float-left">`;
        htmlDummy += `<div data-v-3655f65c="" class="day-btn" onclick="addSchedule();">+</div>`;
        htmlDummy += `</div>`;
        htmlDummy += `<div data-v-3655f65c="" class="text-gray float-right">`;
        htmlDummy += `</div>`;
        htmlDummy += `</div>`;
        htmlDummy += `</div>`;
        htmlDummy += `<div data-v-3655f65c="" class="px-2"></div>`;
        htmlDummy += `</div>`;
        htmlDummy += `</td>`;
        count = i+1;
    }



    for (let i = 1; i <= lastDay; i++) {
        const date = `${currentYear}-${currentMonth.pad()}-${i.pad()}`;
        if (count % 7 === 0) {
            count = 0;
            htmlDummy += `<tr data-v-1e397a0c="" class="week">`;
        }
        htmlDummy += `<td data-v-1e397a0c="">`;
        htmlDummy += `<div data-v-3655f65c="" data-v-1e397a0c="" class="day">`;
        htmlDummy += `<div data-v-3655f65c="" class="clearfix px-2 pt-2">`;
        htmlDummy += `<div data-v-3655f65c="" class="float-left">`;
        htmlDummy += `<div data-v-3655f65c="" class="day-btn" onclick="addSchedule();" data-date="${date}">+</div>`;
        htmlDummy += `</div>`;
        htmlDummy += `<div data-v-3655f65c="" class="text-gray float-right">`;
        htmlDummy += `<!----> ${i}일`;
        htmlDummy += `</div>`;
        htmlDummy += `</div>`;
        htmlDummy += `<div data-v-3655f65c="" class="px-2">`;

        if(calendarList[date]){
            htmlDummy += `<div data-v-3655f65c="" class="bg-frip-primary-50 text-frip-primary rounded p-1 my-2 wg-100" style="cursor: pointer;">`;
            htmlDummy += `<div class="clearfix">`;
            htmlDummy += `<div class="float-left">${calendarList[date]}</div>`;
            htmlDummy += `<div class="float-right">  </div>`;
            htmlDummy += `</div>`;
            htmlDummy += `</div>`;
        }
        htmlDummy += `</div>`;
        htmlDummy += `</div>`;
        htmlDummy += `</td>`;
        if (count % 7 === 0 && count !== 0) {
            htmlDummy += `</tr>`;
        }

        count++
    }
    for (let i = limitDay; i < nextDay; i++) {
        htmlDummy += `<td data-v-1e397a0c="">`;
        htmlDummy += `<div data-v-3655f65c="" data-v-1e397a0c="" class="day">`;
        htmlDummy += `<div data-v-3655f65c="" class="clearfix px-2 pt-2">`;
        htmlDummy += `<div data-v-3655f65c="" class="float-left">`;
        htmlDummy += `<div data-v-3655f65c="" class="day-btn" onclick="addSchedule();">+</div>`;
        htmlDummy += `</div>`;
        htmlDummy += `<div data-v-3655f65c="" class="text-gray float-right">`;
        htmlDummy += `</div>`;
        htmlDummy += `</div>`;
        htmlDummy += `</div>`;
        htmlDummy += `<div data-v-3655f65c="" class="px-2"></div>`;
        htmlDummy += `</div>`;
        htmlDummy += `</td>`;
    }

    document.querySelector(`.calendar`).innerHTML = htmlDummy;
    document.querySelector(`.dateTitle`).innerText = `${currentYear}년 ${currentMonth}월`;
}


// 이전달 이동
$(`.prevDay`).on('click',function(){
    makeCalendar(date);
    makeCalendar(new Date(date.setMonth(date.getMonth() - 1)));
});


// 다음달 이동
$(`.nextDay`).on('click', function(){
    makeCalendar(date);
    makeCalendar(new Date(date.setMonth(date.getMonth() + 1)));
});

//일정 등록 날짜
$('input[type=date]').on('change', function(){
    new Date(this.value) < date ? $('.dateNotice').css('display', ''): $('.dateNotice').css('display', 'none');
});

//신청가능 날짜
$('input[type=number].period').bind('keyup mouseup', function (){
    let selDate = new Date($('input[type=date]').val());
    console.log(selDate);
    selDate = new Date(selDate.setDate(selDate.getDate()-this.value));
    $('.periodNotice').text(`${selDate.getFullYear()}년 ${selDate.getMonth()+1}월 ${selDate.getDate()}일까지 모임을 신청할 수 있습니다.`);
    console.log($('.periodNotice').text());
});


// 기본정보/프립설명 탭 이동
$(".nav-item a").on('click', function(e){
    e.preventDefault();
    if ($(this).attr('id') == '__BVID__562___BV_tab_button__'){
        $('.basic-info').show();
        $('.noticeInfo').show();
        $('.explain').hide();
        $('.noticeEx').hide();
    }else if($(this).attr('id') == '__BVID__696___BV_tab_button__'){
        $('.explain').show();
        $('.noticeEx').show();
        $('.noticeInfo').hide();
        $('.basic-info').hide();
    }

    if($('.nav-item a').attr('aria-selected')){
        $('.nav-item a').attr('aria-selected', 'false');
        $('.nav-item a').attr('class', 'nav-link');
        $('.nav-item a').attr('tabindex', '-1');
    }
    if(!$(this).attr('aira-selected')){
        $(this).attr('aria-selected', 'ture');
        $(this).attr('class', 'nav-link active active-tab');
        $(this).attr('tabindex', '');
    }
});

// focus됐을 때 border 보라색으로 변경
$('.input-group .form-control').on('focus', function(){
    $(this).next().children('span').css('border-color', '#83aad1');
    $(this).css('border-color', '#83aad1');
});

//주소 찾기 버튼클릭 시
$('.findBtn').on('click', function(){
    find();
});

//다음 주소찾기 서비스 실행
function find(){
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("placeAddress").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("placeAddress").focus();
        }
    }).open();
}


// 글자 수 세기
$('.input-group input[type=text].form-control').on('input', function(){
    $(this).next().children('span').children('small').text($(this).val().length + ' / 40');
});

// 인풋태그 블러 이벤트
$('.input-group .form-control').on('blur', function(){
    $(this).next().children('span').css('border-color', '');
    $(this).css('border-color', '');
    if($(this).attr('class') == 'number1 form-control' || $(this).attr('class') == 'number2 form-control'){
        return;
    }
    if(!$(this).val()){
        $(this).attr('class', 'form-control is-invalid');
        $(this).next().children('span').attr('class', 'input-group-text is-invalid');
        $(this).closest('.input-group').next().css('display','block');
    }else{
        $(this).attr('class', 'form-control');
        $(this).next().children('span').attr('class', 'input-group-text');
        $(this).closest('.input-group').next().css('display','none');
    }
});

// 프립 유형 선택
$('.selectBox.mx-2').on('click', function(){
    $('.selectBox.mx-2').attr('class', 'selectBox mx-2');
    if($(this).attr('class') != 'selectBox mx-2 selected'){
        $(this).attr('class', 'selectBox mx-2 selected');
        ($(this).attr('data-type') == 'offline'? $('.place').show() : $('.place').hide());
    }
});

// 모달창 닫기
function closeModal(){
    $('#__BVID__287___BV_modal_outer_').hide();
}


// 진행 장소 추가 버튼 클릭 -> 모달창 열기
function findPlace(){
    if($("input[name='groupName']").val()=="" || $("input[name='groupCategory']").val()==""){
        alert("모임명과 카테고리를 먼저 입력해주세요.");
        return;
    }

    $('#__BVID__287___BV_modal_outer_').show();
    $('#__BVID__287___BV_modal_content_').show();
    $('#__BVID__1216___BV_modal_content_').hide();
    $('#__BVID__21___BV_modal_content_').hide();
    $('#__BVID__123___BV_modal_content_').hide();
}

let checkedLegion = false;
//국내 || 해외 선택
$('.countryOption .custom-radio').on('click', function(){
    checkedLegion = true;
        $('.countryOption .custom-radio').attr('value', 'false');
        $(this).attr('value', 'true');
    if($(this).attr('data-value') != 'country'){
        $('.findBtn').hide();
    }else{
        $('.findBtn').show();
    }
});

//장소 등록 삭제
$('.clearPlace').on('click', function(){
    $('.placeTableBtn').hide();
    $('.my-2.placeTable').hide();
    $('.findPlace').show();
});

// 진행 장소 등록 버튼 클릭 이벤트막기
$('.createPlace').on('click', function(e){
    if($('input[data-v-72dffd28]').eq(12).val() == ""){
        $('input[data-v-72dffd28]').eq(12).blur();
    }

    if(!checkedLegion){
        alert('해외장소 여부를 선택해주세요');
    }

    if($('#placeAddress').val() == ""){
        e.preventDefault();
        $('#placeAddress').blur();
    }

    if($('input[data-v-72dffd28]').eq(14).val() != "" && $('#placeAddress').val() != ""){
        e.preventDefault();
        $('.placeTable .placeName').text($("input[name='locationName']").val());
        $('.placeTable .placeAddr').text($('#placeAddress').val());
        $('.my-2.placeTable').show();
        $('.placeTableBtn').show();
        $('.findPlace').hide();

        closeModal();
    }
});
$('#placeAddress').on('blur', function(){
    if(!$(this).val()){
        $(this).attr('class', 'form-control is-invalid');
    }else{
        $(this).attr('class', 'form-control');
    }
});



// 일정 추가 버튼 클릭 -> 모달창 열기
function addSchedule() {
    // 일정 추가 버튼 유효성 검사
    if ($(".cancelRecruitment").css("display") == "none") {
        alert("일정 설정은 저장 후 가능합니다.");
        return;
    }

    $(".table").on("click", ".day-btn", function(){
        console.log($(this).parent().parent().next().children().text());
        if($(this).parent().parent().next().children().text()){
            let scheduleDeleteMsg = confirm($(".mx-3.dateTitle").text() + $(this).parent().next().text() + " " +  $(this).parent().parent().next().children().text().trim() + "에 진행 예정인 모임 일정을 삭제하시겠습니까?");
            if(scheduleDeleteMsg){
                let scheduleDate = $(this).attr("data-date");
                let groupId = $('input[name=groupId]').val();
                // 삭제
                $.ajax({
                    url: "/host/delete-schedule/" + groupId + "/" + scheduleDate,
                    type: "post",
                    success: function(){
                        showList();
                    }
                });
            }else{;}
            return;
        }
    })


    $('#__BVID__287___BV_modal_outer_').show();
    $('#__BVID__287___BV_modal_content_').hide();
    $('#__BVID__21___BV_modal_content_').hide();
    $('#__BVID__123___BV_modal_content_').hide();
    $('#__BVID__1216___BV_modal_content_').show();

    $('input[type=date]').val($(this).attr('data-date'));
    let selDate = new Date($('input[type=date]').val());
    selDate < date ? $('.dateNotice').css('display', '') : $('.dateNotice').css('display', 'none');
    if ($('input[type=date]').val()) {
        $('.periodNotice').text(`${selDate.getFullYear()}년 ${selDate.getMonth() + 1}월 ${selDate.getDate()}일까지 대원이 신청할 수 있습니다.`);
    } else {
        $('.periodNotice').text(`날짜를 입력해주세요`);
    }


}

//일정 시간 유효성 검사
$('.openTime').on('change', function (){
    if(!$('.closeTime').val()){return;}
    let open = $('.openTime').val().split(':');
    let close = $('.closeTime').val().split(':');
    let openTime = new Date();
    let closeTime = new Date();

    openTime.setHours(open[0]);
    openTime.setMinutes(open[1]);
    closeTime.setHours(close[0]);
    closeTime.setMinutes(close[1]);

    let differ = (closeTime.getTime() - openTime.getTime()) / (1000*60*60)

    console.log("openTime" + openTime);
    console.log("closeTime" + closeTime);
    console.log(openTime > closeTime);

    if(openTime > closeTime){
        console.log('dd');
        $('.timeNotice').text('종료시간은 시작시간보다 빠를 수 없습니다.');
        $('.timeNotice').css('display', '');
    }else if(differ > 5){
        $('.timeNotice').text('모임시간은 최대 5시간을 초과할 수 없습니다.');
        $('.timeNotice').css('display', '');
    }else{
        $('.timeNotice').css('display', 'none');
    }

});


//일정 등록
$('.createPlan').on('click', function(){
    //이거 백엔드에서 DB일정 등록하시고 rest로 달력이랑 같이 로딩하시면 됨,

    if(!$('input[type=date]').val()){
        $('input[type=date]').focus();
        return;
    }

    if(!$('.openTime').val() || !$('.closeTime').val()){
        $('.openTime').focus();
        return;
    }

    if(!$('input[type=number].period').val()){
        $('input[type=number].period').focus();
        return;
    }

    if($('.dateNotice').css('display') != 'none'){
        return;
    }

    if($('.timeNotice').css('display') != 'none'){
        return;
    }

    $('#__BVID__287___BV_modal_outer_').hide();
    $('#__BVID__1216___BV_modal_content_').hide();

    //rest로 일정 등록해서 집어 넣을 공간
    groupSave.addDate({
        groupScheduleDate :new Date($('input[type=date]').val()),
        groupScheduleStartTime : new Date($('input[type=date]').val() + " " + $('.openTime').val() + ":00"),
        groupScheduleEndTime : new Date($('input[type=date]').val() + " " + $('.closeTime').val() + ":00")
    }, showList);
});



// 주차장 옵션 버튼 눌렀을때, 주차옵션이 있다면 메모를 할 수 있게함
$('#parkingOption .custom-radio').on('click', function(){
    if($(this).children('input').attr('id') != 'parkingOption_BV_option_0'){
        $('#parkingOption_BV_option_0').val('false')
        $('#textarea').val('');
        $('#textarea').prop('disabled', true);
    }else{
        $('#parkingOption_BV_option_0').val('true')
        $('#textarea').prop('disabled', false);
    }
});

// 옵션 선택 박스 선택시
$('.col-lg-5.selectBox').on('click', function(){
    if($(this).attr('class') == 'col-lg-5 selectBox selected'){
        $(this).attr('class', 'col-lg-5 selectBox');
    }
    $(this).attr('class', 'col-lg-5 selectBox selected');
});


//썸네일 이미지
$(".plusThumb").on("change", function(){

    let arrayFile =[];

    let formData = new FormData();
    let $inputFile = $("input[name='plusThumb']");
    let files = $inputFile[0].files;

    Array.from(files).forEach(file => arrayFile.push(file));
    const dataTransfer = new DataTransfer();

    arrayFile.forEach(file => dataTransfer.items.add(file));
    $(this)[0].files = dataTransfer.files;

    $(files).each(function(i, file){
        formData.append("upload", file);
    });

    $.ajax({
        url: "/host/thumbnail",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function(result) {
            $('input[name=groupFileName]').attr('value', Object.values(result[0])[20]);
            $('input[name=groupFilePath]').attr('value', Object.values(result[0])[19]);
            $('input[name=groupFileSize]').attr('value', Object.values(result[0])[22]);
            $('input[name=groupFileUuid]').attr('value', Object.values(result[0])[21]);
            let imageSrc = "/host/display?fileName=" + Object.values(result[0])[19] + "/" + Object.values(result[0])[21] + "_" + Object.values(result[0])[20];
            console.log(result[0].groupFilePath);
            console.log(Object.values(result[0]));
            console.log(imageSrc);

            if($('input[name=groupFileSize]').val()>100000){
                alert("파일 사이즈는 10MB 이하여야합니다.");
                return;
            }

            $('.image-header').show();
            $('.img-box').show();

            let text = "";
            text += `<li id="thumbnailImage" data-file-size="` + Object.values(result[0])[22] + `" data-file-name="` + Object.values(result[0])[20] + `" data-file-upload-path="` + Object.values(result[0])[19] + `" data-file-uuid="` + Object.values(result[0])[21] + `" style="list-style: none;width:100%;">`;
            text += `<img src=` + imageSrc + ` style="width:100%;" height="auto">`;
            text += `</li>`;

            $(".imgInputBox").append(text);

            $(".text-center.container.thumbnailPlus").hide();
        }
    });
});



//썸네일 삭제
$('.removeImg').on('click', function(){
    $('.image-header').hide();
    $('.img-box').attr('src', '');
    let uploadPath = $("#thumbnailImage").data("file-upload-path");
    let fileName = $("#thumbnailImage").data("file-uuid") + "_" + $("#thumbnailImage").data("file-name");

    $.ajax({
        url: "/host/delete",
        type: "post",
        data: {uploadPath: uploadPath, fileName: fileName},
        success: function(){
            $("#thumbnailImage").remove();
            $(".text-center.container.thumbnailPlus").show();
        }
    });
});

//검수 요청 버튼
$('.fixed-bottom .frip-button').on('click', function(){
    $('#__BVID__287___BV_modal_outer_').show();
    $('#__BVID__287___BV_modal_content_').hide();
    $('#__BVID__21___BV_modal_content_').show();
    $('#__BVID__1216___BV_modal_content_').hide();
    $('#__BVID__123___BV_modal_content_').hide();
});

//검수 요청 확인
$('.checkRequest').on('click', function (){

    if($('.image-header').css('display') == 'none'){
        alert('이미지를 등록해주세요');
        $('#__BVID__287___BV_modal_outer_').hide();
        return;
    }

    if($('input[name=groupContent]').val() === ''){
        alert('모임 설명을 확인해주세요');
        $('#__BVID__287___BV_modal_outer_').hide();
        return;
    }

    if($('.cancelRecruitment').css('display') == 'none'){
        alert('좌측 저장버튼을 먼저 눌러주세요');
        $('#__BVID__287___BV_modal_outer_').hide();
        return;
    }

    if($('input[type=text].form-control').get(0).value == '' || $('input[type=text].form-control').get(1).value == '' || $('input[type=text].form-control').get(2).value == ''){
        alert('필수입력 항목을 확인해주세요');
        $('#__BVID__287___BV_modal_outer_').hide();
        return;
    }

    if($('.number1').val() == '' || $('.number2').val() == '' || $('.recruitment').next().css('display') != 'none'){
        alert('모집인원 항목을 확인해주세요');
        $('#__BVID__287___BV_modal_outer_').hide();
        return;
    }

    if(!$('.selectBox.mx-2.selected').attr('data-type')){
        alert('모임 유형을 선택해주세요');
        $('#__BVID__287___BV_modal_outer_').hide();
        return;
    }

    if($('.selectBox.mx-2').attr('data-type') == 'offline'){
        //장소등록 안되있으면 return
        if($('.my-2.placeTable').css('display')=='none'){
            return;
        }
    }


//    누르면 승인대기 상태로 바꾸기
    alert('검수 요청이 완료되었습니다');
    $('#__BVID__287___BV_modal_outer_').hide();

});

//REST 방식으로 저장하기
let groupSave = (function(){
    function add(groupContents, callback, error){
        $.ajax({
            url: "/host/index",
            type: "post",
            data: JSON.stringify(groupContents),
            contentType: "application/json; charset=utf-8",
            success: function(result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function update(groupContents, callback, error){
        $.ajax({
            url: "/host/update",
            type: "post",
            data: JSON.stringify(groupContents),
            contentType: "application/json; charset=utf-8",
            success: function(result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function addDate(groupContents, callback, error){
        let groupId = $('input[name=groupId]').val();
        $.ajax({
            url: "/host/addDate/" + groupId,
            type: "post",
            data: JSON.stringify(groupContents),
            contentType: "application/json; charset=utf-8",
            success: function(result, status, xhr){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function listDate(param, callback, error){
        let groupId = $('input[name=groupId]').val();
        $.ajax({
            url: "/host/date-list/" + groupId,
            type: "get",
            success: function(groupScheduleDTO, status, xhr){
                if(callback){
                    console.log("success");
                    callback(groupScheduleDTO);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    console.log("error");
                    error(err);
                }
            }
        });
    }

    // 모임 페이지 삭제
        $(".goDelete").on("click", function(e){
            e.preventDefault();
            location.href ="/host/deleteGroup?groupId=" + $(this).attr("href");
        })
    return {add: add, update: update, addDate: addDate, listDate: listDate}
})();


//임시 저장 버튼
$('.saveRecruitment').on('click', function (){

    // 일정 추가 버튼 생성
    $(".btn.frip-button.btn-outline-frip-primary").eq(1).show();
    $('.cancelRecruitment').show();
    $('#__BVID__287___BV_modal_outer_').show();
    $('#__BVID__287___BV_modal_content_').hide();
    $('#__BVID__21___BV_modal_content_').hide();
    $('#__BVID__1216___BV_modal_content_').hide();
    $('#__BVID__123___BV_modal_content_').show();

});

/*그룹 아이디값 지정*/
$('input[name=groupId]').attr('value', $("#__BVID__579 .text-sm").text());

//임시 저장 확인
$('.saveRequest').on('click', function (e){
    e.preventDefault();
    $('#__BVID__287___BV_modal_outer_').hide();
    
    /*추가 유효성검사 필요하면 여기에 기재*/

    //groupLocationType 설정
    if($(".selected .p-3 div")[0].innerText == "오프라인"){
        $('input[name=groupLocationType]').attr('value',"OFFLINE");
    } else{
        $('input[name=groupLocationType]').attr('value',"ONLINE");
    }

    // 장소명
    $('input[name=groupLocationName]').attr('value', $('input[name=locationName]').val());
    // 진행 장소
    $('input[name=groupLocation]').attr('value', $('#placeAddress').val());
    // 진행 장소 상세 내용
    $('input[name=groupLocationDetail]').attr('value', $('#locationDetail').val());
    // 주차 관련 상세 내용
    $('input[name=groupMoreInformation]').attr('value', $('#textarea').val());
    // 주차 가능여부
    if($("#parkingOption_BV_option_0").val()=="true"){
        $('input[name=groupParkingAvailable]').attr('value', "가능");
    } else{
        $('input[name=groupParkingAvailable]').attr('value', "불가");
    }
    // 국내/해외
    if($(".my-2.custom-control.custom-radio").val()==true){
        $('input[name=groupOverSea]').attr('value', "국내");
    } else{
        $('input[name=groupOverSea]').attr('value', "해외");
    }

    //  실제 작성한 내용
    let content = $(".note-editable").html();
    $('input[name=groupContent]').attr('value', content);

    if($(".note-editable").text().length>255){
        alert("글자는 255자 이내로 작성 가능합니다.");
        return;
    }

    if($('input[name=groupId]').val()=="신규등록"){
        // 컨트롤러로 해당 내용 모두 전송
        groupSave.add({
            groupName : $('input[name=groupName]').val(),
            groupCategory : $('input[name=groupCategory]').val(),
            groupContent :$('input[name=groupContent]').val(),
            groupPoint : $('input[name=groupPoint]').val(),
            groupOverSea : $('input[name=groupOverSea]').val(),
            groupLocationName : $('input[name=groupLocationName]').val(),
            groupLocation : $('input[name=groupLocation]').val(),
            groupLocationDetail : $('input[name=groupLocationDetail]').val(),
            groupParkingAvailable : $('input[name=groupParkingAvailable]').val(),
            groupMoreInformation : $('input[name=groupMoreInformation]').val(),
            groupLocationType : $('input[name=groupLocationType]').val(),
            maxMember : $('input[name=maxMember]').val(),
            minMember : $('input[name=minMember]').val(),
            groupFileName : $('input[name=groupFileName]').val(),
            groupFilePath : $('input[name=groupFilePath]').val(),
            groupFileSize : $('input[name=groupFileSize]').val(),
            groupFileUuid : $('input[name=groupFileUuid]').val()
        })
    }
    
    /*게시글 수정일 때*/
    else{
        groupSave.update({
            groupName : $('input[name=groupName]').val(),
            groupCategory : $('input[name=groupCategory]').val(),
            groupContent :$('input[name=groupContent]').val(),
            groupPoint : $('input[name=groupPoint]').val(),
            groupOverSea : $('input[name=groupOverSea]').val(),
            groupLocationName : $('input[name=groupLocationName]').val(),
            groupLocation : $('input[name=groupLocation]').val(),
            groupLocationDetail : $('input[name=groupLocationDetail]').val(),
            groupParkingAvailable : $('input[name=groupParkingAvailable]').val(),
            groupMoreInformation : $('input[name=groupMoreInformation]').val(),
            groupLocationType : $('input[name=groupLocationType]').val(),
            maxMember : $('input[name=maxMember]').val(),
            minMember : $('input[name=minMember]').val(),
            groupFileName : $('input[name=groupFileName]').val(),
            groupFilePath : $('input[name=groupFilePath]').val(),
            groupFileSize : $('input[name=groupFileSize]').val(),
            groupFileUuid : $('input[name=groupFileUuid]').val(),
            groupId : $('input[name=groupId]').val()
        })
    }
});

//인원 설정 시 유효성검사.
$('.number1').bind('keyup mouseup', function (){
    if($('.number1').val() > $('.number2').val()){
        $('.recruitment').next().css('display', '');
        $('.recruitment').next().show();
    }else{
        $('.recruitment').next().hide();
        $('.recruitment').next().css('display', 'none');
    }
});

$('.number2').bind('keyup mouseup', function (){
    if($('.number1').val() > $('.number2').val()){
        $('.recruitment').next().css('display', '');
        $('.recruitment').next().show();
    }else{
        $('.recruitment').next().hide();
        $('.recruitment').next().css('display', 'none');
    }
});

let thumbnailCheck = 0;

// 수정 진행 시 썸머노트에 기존 내용 출력하기
$(".py-2.px-0.container.ex").on("click", function(){

    if($('input[name=groupFilePath]').val()){
        thumbnailCheck++;
        if(thumbnailCheck<=1){
            let imageSrc = "/host/display?fileName=" + $('input[name=groupFilePath]').val() + "/" + $('input[name=groupFileUuid]').val() + "_" + $('input[name=groupFileName]').val();

            $('.image-header').show();
            $('.img-box').show();
            let text = "";
            text += `<li id="thumbnailImage" data-file-size="` + $('input[name=groupFileSize]').val() + `" data-file-name="` + $('input[name=groupFileName]').val() + `" data-file-upload-path="` +$('input[name=groupFilePath]').val() + `" data-file-uuid="` + $('input[name=groupFileUuid]').val() + `" style="list-style: none;width:100%;">`;
            text += `<img src=` + imageSrc + ` style="width:100%;" height="auto">`;
            text += `</li>`;

            $(".imgInputBox").append(text);
            $(".text-center.container.thumbnailPlus").hide();
        }
    }
})


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
        focus:true,
        lang : "ko-KR",
        callbacks: {
            onImageUpload : function(files){
                sendFile(files[0]);
            }
        }
    });

    /*$('#summernote').summernote('insertText', $('input[name=groupContent]').val());*/
    $(".note-editable").html($('input[name=groupContent]').val());

    $('#summernote').on('summernote.change', function(we, contents, $editable) {
        $('input[name=groupContent]').attr('value', $(".note-editable").html());
    });
}); //ready


