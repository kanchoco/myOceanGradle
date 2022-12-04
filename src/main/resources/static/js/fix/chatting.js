
$chatDateLine = $(".chattingWrap .chattingContentWrap .chattingSection .chattingRoom .chattingRoomWrap .dayInfo");

checkChatMedia();
/*반응형*/
function checkChatMedia(){
    if (window.innerWidth < 769) {
        //채팅창 썸네일 이미지 삭제
        $(".chatThumb").css("display", "none");
        //채팅창 활성화 시작 시간 삭제
        $(".beforeTime").css("display", "none");
        //채팅창 활성화 종료 시간 삭제
        $(".endTime").css("display", "none");
        //채팅창 설명 삭제
        $(".chatInfoTxt").css("display", "none");
        //채팅 내역에서 날짜 옆 선 길이 조정
        $chatDateLine.attr("class", "dayInfo media");

    } else{
        //채팅창 썸네일 이미지 살리기
        $(".chatThumb").css("display", "block");
        //채팅창 활성화 시작 시간 삭제
        $(".beforeTime").css("display", "block");
        //채팅창 활성화 종료 시간 삭제
        $(".endTime").css("display", "block");
        //채팅창 설명 삭제
        $(".chatInfoTxt").css("display", "block");
        //채팅 내역에서 날짜 옆 선 길이 조정
        $chatDateLine.attr("class", "dayInfo");
    }
}

$(window).resize(function() {
    if (window.innerWidth < 769) {
        //채팅창 썸네일 이미지 삭제
        $(".chatThumb").css("display", "none");
        //채팅창 활성화 시작 시간 삭제
        $(".beforeTime").css("display", "none");
        //채팅창 활성화 종료 시간 삭제
        $(".endTime").css("display", "none");
        //채팅창 설명 삭제
        $(".chatInfoTxt").css("display", "none");
        //채팅 내역에서 날짜 옆 선 길이 조정
        $chatDateLine.attr("class", "dayInfo media");


    } else{
        //채팅창 썸네일 이미지 살리기
        $(".chatThumb").css("display", "block");
        //채팅창 활성화 시작 시간 삭제
        $(".beforeTime").css("display", "block");
        //채팅창 활성화 종료 시간 삭제
        $(".endTime").css("display", "block");
        //채팅창 설명 삭제
        $(".chatInfoTxt").css("display", "block");
        //채팅 내역에서 날짜 옆 선 길이 조정
        $chatDateLine.attr("class", "dayInfo");
    }
})


/* 오른쪽 하단 아이콘 누르면 채팅창 보이기 */
$(".chattingWrap2").on("click", function () {
    $(".chattingWrap2").css("display", "none");
    $("#chattingList").css("display", "block");


})


/* 채팅창 닫기 누르면 다시 원상복귀 */
$(".foldChatBtn").on("click", function () {
    $("#chattingList").css("display", "none");
    $(".chattingWrap2").css("display", "block");
})


/* 대화방 나가기 버튼을 누르면 나오는 모달 */
$(".moreBtn").on("click", function () {
    let name = $(this).closest("#chattingList").find(".chattingRoomName").text();
    let text = "";
    text += `<p class="modalTit">` + name + `</p>`
    text += `<p class="modalTit">채팅방을 나가시겠습니까?</p>`
    $(".chatExitModal .chatExit .inputChattingName").html(text);
    $(".chatExitModal").css("display", "block");
})


/* 나가기 누르면 나오는 모달에서 예, 아니오, 엑스를 누르면 모달창 닫기 */
$(".closeBtn").on("click", function () {
    $(".chatExitModal").css("display", "none");
})

$(".whiteBtn").on("click", function () {
    $(".chatExitModal").css("display", "none");
})

$(".redBtn").on("click", function () {
    $(".chatExitModal").css("display", "none");
})


/* 왼쪽 대화목록에서 선택될 때마다 css 바꾸는 부분 */
$("li.active").on("click", function () {
    $("li.active").removeClass("select");
    $(this).addClass("select");
})


/* 처음 대화를 시도하는 상대방의 1:1 대화 버튼을 누르면 나오는 모달 */
$(".pinkBtn").on("click", function () {
    let name = $(this).closest(".profileInfoTxt").find(".name").text();
    let text = "";
    text += `<p>예를 누르시면,</p>`
    text += `<p>` + name + `님과 대화를 시작합니다!` + `</p>`
    $(".request_chat .commonModal .commonModalContent").html(text);
    $(".request_chat").css("display", "block");
})


/* 에, 아니오, 엑스 누르는 부분. 예를 누르면 채팅창이 열린다. */
$(".closeBtn").on("click", function () {
    $(".request_chat").css("display", "none");
})

$(".whiteBtn").on("click", function () {
    $(".request_chat").css("display", "none");
})

$(".chatStart").on("click", function () {
    $(".request_chat").css("display", "none");
    $(".chattingWrap2").css("display", "none");
    $("#chattingList").css("display", "block");
})


/* 여러 부분에서 1:1 채팅을 누르면 이름을 가져와서 모달창 뜨게 하는 부분 */
$(".chat").on("click", function () {
    let name = $(this).closest(".letspler_Re").find(".name").text();
    let text = "";
    text += `<p>예를 누르시면,</p>`
    text += `<p>` + name + `님과 대화를 시작합니다!` + `</p>`
    $(".request_chat .commonModal .commonModalContent").html(text);
    $(".request_chat").css("display", "block");
})

$(".letspler_Re .bottom .chatBtn").on("click", function () {
    let name = $(this).closest(".letspler_Re").find(".name").text();
    let text = "";
    text += `<p>예를 누르시면,</p>`
    text += `<p>` + name + `님과 대화를 시작합니다!` + `</p>`
    $(".request_chat .commonModal .commonModalContent").html(text);
    $(".request_chat").css("display", "block");
})

$(".chatMini").on("click", function () {
    let name = $(this).closest(".right").find(".nameMini").text();
    let text = "";
    text += `<p>예를 누르시면,</p>`
    text += `<p>` + name + `님과 대화를 시작합니다!` + `</p>`
    $(".request_chat .commonModal .commonModalContent").html(text);
    $(".request_chat").css("display", "block");
})



/* 이미 대화중일 때 뜨는 모달 */
$(".already_chat_btn").click(function(){
    let name = $(this).closest(".profileInfoTxt").find(".name").text();
    let text = "";
    text += `<p class="modalTit"><span>` + name + `</span>님과</p>`
    text += `<p class="modalTit">대화가 진행중입니다.</p>`

    $(".already_chat .commonModal .tit").html(text);
    $(".already_chat").css("display", "block");
});

$(".chatStart").on("click", function () {
    $(".already_chat").css("display", "none");
    $(".chattingWrap2").css("display", "none");
    $("#chattingList").css("display", "block");
})

$(".closeBtn").on("click", function () {
    $(".already_chat").css("display", "none");
})

$(".whiteBtn").on("click", function () {
    $(".already_chat").css("display", "none");
})



$(".mentions__control").on("keyup",function(key){
    if(key.keyCode==13) {
        $(".mentions--multiLine").attr("style",)
    }
});

function showGroupList(param, callback, error){
    $.ajax({
        url: "/main/index/",
        type: "get",
        success: function(status, xhr){
            if(callback){
                callback();
            }
        },
        error: function(xhr, status, err){
            if(error){
                error(err);
            }
        }
    });

}
///////////////////////////////////////////////////////// Socket ////////////////////////////////////////////////////////////////////////
// $(document).ready(  function() {
//     //connectWS();
//     //connectSockJS();
//     connectStomp();
//
//     $('.buttonComponents_lgImg__2-hZO').on('click', function(evt) {
//         evt.preventDefault();
//         if (!isStomp && socket.readyState !== 1) return;
//
//         let msg = $('input#msg').val();
//         console.log("mmmmmmmmmmmm>>", msg)
//         if (isStomp)
//             socket.send('/TTT', {}, JSON.stringify({roomid: 'message', id: 124, msg: msg}));
//         else
//             socket.send(msg);
//     });
// });
//
// var socket = null;
// var isStomp = false;
//
// function connectStomp() {
//     var sock = new SockJS("/stompTest"); // endpoint
//     var client = Stomp.over(sock);
//     isStomp = true;
//     socket = client;
//
//     client.connect({}, function () {
//         console.log("Connected stompTest!");
//         // Controller's MessageMapping, header, message(자유형식)
//         client.send('/TTT', {}, "msg: Haha~~~");
//
//         // 해당 토픽을 구독한다!
//         client.subscribe('/topic/message', function (event) {
//             console.log("!!!!!!!!!!!!event>>", event)
//         });
//     });
//
// }
//
// function connectSockJS() {
//     console.log("eeeeeeeeeeeeeeeeeeeee")
//     var sock = new SockJS("/replyEcho");
//     socket = sock;
//     sock.onopen = function () {
//         console.log('Info: connection opened.');
//         sock.send("hi~");
//
//         sock.onmessage = function (event) {
//             console.log("ReceivedMessage:", event.data);
//         };
//         sock.onclose = function (event) {
//             console.log('Info: connection closed.');
//         };
//     };
// }
//
// // pure web-socket
// function connectWS() {
//     console.log("tttttttttttttt")
//     var ws = new WebSocket("ws://localhost:9090/replyEcho");
//     socket = ws;
//
//     ws.onopen = function () {
//         console.log('Info: connection opened.');
//     };
//
//     ws.onmessage = function (event) {
//         console.log("ReceiveMessage:", event.data+'\n');
//         /* let $socketAlert = $('div#socketAlert');
//         $socketAlert.html(event.data);
//         $socketAlert.css('display', 'block'); */
//
//         /* setTimeout( function() {
//             $socketAlert.css('display', 'none');
//         }, 3000); */
//     };
//
//     ws.onclose = function (event) {
//         console.log('Info: connection closed.');
//         //setTimeout( function(){ connect(); }, 1000); // retry connection!!
//     };
//     ws.onerror = function (err) { console.log('Error:', err); };
// }
//
