$chatDateLine = $(".chattingWrap .chattingContentWrap .chattingSection .chattingRoom .chattingRoomWrap .dayInfo");



checkChatMedia();

/*반응형*/
function checkChatMedia() {
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

    } else {
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

$(window).resize(function () {
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


    } else {
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
$(".already_chat_btn").click(function () {
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


$(".mentions__control").on("keyup", function (key) {
    if (key.keyCode == 13) {
        $(".mentions--multiLine").attr("style",)
    }
});


/*==============================================================================================================================================*/
/*==============================================================================================================================================*/
/*==============================================================================================================================================*/
/*==============================================================================================================================================*/
let webSocket;
function connect(){
    webSocket = new WebSocket("ws://localhost:15000/myOceanProject");
    webSocket.onopen = onOpen;
    webSocket.onclose = onClose;
    webSocket.onmessage = onMessage;
}

let userNickName = document.getElementById("userId").innerText;
let userId = document.getElementById("userId").getAttribute("href");
let myGroupId;

// db에서 해당 그룹의 채팅 내용을 모두 가져온다.
function getList(param, callback, error) {
    $.ajax({
        url: "/chatting/groupId/" + param.groupId,
        type: "get",
        async:false,
        success: function (chattingDTOList, status, xhr) {
            if (callback) {
                callback(chattingDTOList);
            }
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });

}

// 채팅 db에서 해당 그룹의 채팅 내용을 모두 가져온 후 다시
function show(id) {
    getList({
        groupId: id
    }, getChattingContentList)
}

let temp = 0;
/* 왼쪽 대화목록에서 선택될 때마다 css 바꾸는 부분 */


$("li.active").on("click", function (e) {
        e.preventDefault()
        myGroupId = $(this).attr("href");
        show(myGroupId)
        if ($(this).attr('class') !== "select") {
            $("li.active").removeClass("select");
            $(this).addClass("select");
            connect();
        } else if ($(this).attr('class') === "select") {
            console.log("선택됐던 리스트")
        } else {
            disconnect();
            connect();
        }
    }
)



// $("#sendButton").on("click", function(){
//     console.log(myGroupId);
//     console.log()
//     let chat = document.getElementById("msg").value;
//     // send();
//     // e.data = chat;
//     // webSocket.onmessage(e);
//     // add({
//     //     chattingContent: chat,
//     //     groupId : myGroupId,
//     //     senderUserId : userId
//     // })
// });

document.getElementById("sendButton").addEventListener("click",function(e){
    let chat = document.getElementById("msg").value;

    // connect
    add({
        chattingContent: chat,
        groupId : myGroupId,
        senderUserId : userId,
        messageType : "대화"
    })
    // send();
    // // e.data = chat;
    // // webSocket.onmessage(e);
})


function getChattingContentList(chattingDTOList) {
    let text = "";
    var time;
    chattingDTOList.forEach(chatting => {
        if (chatting.chattingCreateDate !== time) {
            time = chatting.chattingCreateDate
            text += "<div class=\"dayInfo\">"
            text += "<p>" + chatting.chattingCreateDate + "</p>"
            text += "</div>"
            text += "<div class=\"opponent\">";
            text += "<div class=\"thumb\">"
            text += "<a href=\"/people/ROSA\" target=\"_blank\">"
            text += "<img src=\"/imgin/chat/logo.png\" alt=\"chat_image\"></a>"
            text += "</div>"
            text += "<div class=\"userIdChatTxt\">"
            text += "<span class=\"userId\">" + chatting.senderUserNickName + "</span>"
            text += "<div class=\"chatTxt\">"
            text += "<span class=\"chatTxtContents\">"
            text += "<a style=\"color: rgb(51, 51, 51);\">" + chatting.chattingContent + "</a>"
            text += "</span>"
            text += "<div class=\"timeWrap\">"
            text += "<span class=\"time\">" + chatting.chattingCreateTime + "</span>"
            text += "</div>"
            text += "</div>"
            text += "</div>"
            text += "</div>"
        } else {
            text += "<div class=\"opponent\">";
            text += "<div class=\"thumb\">"
            text += "<a href=\"/people/ROSA\" target=\"_blank\">"
            text += "<img src=\"/imgin/chat/logo.png\" alt=\"chat_image\"></a>"
            text += "</div>"
            text += "<div class=\"userIdChatTxt\">"
            text += "<span class=\"userId\">" + chatting.senderUserNickName + "</span>"
            text += "<div class=\"chatTxt\">"
            text += "<span class=\"chatTxtContents\">"
            text += "<a style=\"color: rgb(51, 51, 51);\">" + chatting.chattingContent + "</a>"
            text += "</span>"
            text += "<div class=\"timeWrap\">"
            text += "<span class=\"time\">" + chatting.chattingCreateTime + "</span>"
            text += "</div>"
            text += "</div>"
            text += "</div>"
            text += "</div>"
        }

    })
    $(".chattingRoomWrap").html(text)

}

function add(chatting, error){
    $.ajax({
        url: "/chatting/new",
        type: "post",
        data: JSON.stringify(chatting),
        contentType: "application/json; charset=utf-8",
        success: function (status, xhr) {
            send();
        },
        error: function(xhr, status, err){
            if(error){
                error(err);
            }
        }
    });
}







function disconnect(){
    webSocket.send(JSON.stringify({groupId : myGroupId, messageType:'퇴장',senderUserNickName:userNickName,senderUserId : userId}));
    webSocket.close();
}
function send(){
    console.log("=============================자바스크립트 send들어옴======================================")
    console.log(userNickName);
    console.log(userId);
    let msg = document.getElementById("msg").value;
    webSocket.send(JSON.stringify({
        groupId : myGroupId,
        messageType:"대화",
        senderUserNickName:userNickName,
        senderUserId : userId,
        chattingContent : msg
    }))
}
function onOpen(){
    console.log("=============================자바스크립트 onOpen들어옴======================================")
    // webSocket.send(JSON.stringify({groupId : myGroupId, messageType:'ENTER',senderUserNickName:userNickName, senderUserId : userId}));
}
function onMessage(e){
    console.log("=============================자바스크립트 onMessage들어옴======================================")
    chatdata = e.data;
    chattingRoom =document.getElementById("chattingRoom");
    let datas = chatdata.split(':');
    console.log(datas[0])
    console.log(datas[1])
    chattingRoom.innerHTML =chattingRoom.innerHTML
        + "<div class=\"opponent\">"
        + "<div class=\"thumb\">"
        + "<a href=\"/people/ROSA\" target=\"_blank\">"
        + "<img src=\"/imgin/chat/logo.png\" alt=\"chat_image\"></a>"
        + "</div>"
        + "<div class=\"userIdChatTxt\">"
        + "<span class=\"userId\">" + datas[0] + "</span>"
        + "<div class=\"chatTxt\">"
        + "<span class=\"chatTxtContents\">"
        + "<a style=\"color: rgb(51, 51, 51);\">" + datas[1] + "</a>"
        + "</span>"
        + "<div class=\"timeWrap\">"
        + "<span class=\"time\">" + new Date().getHours()+":"+new Date().getMinutes() + "</span>"
        + "</div>"
        + "</div>"
        + "</div>"
        + "</div>"
}
function onClose(){
    disconnect();
}






