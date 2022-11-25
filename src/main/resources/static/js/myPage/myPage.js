const $save = $("button.jMUPmW");
const $nicknameInput = $("input[name=nickname]");
const $nickname = $(".gGztpV");
const $profilePicture = $(".iOgbWN");

// 카테고리 크기 미디어쿼리
checkWidth();

$(window).resize(function(){
    if(window.innerWidth<610){
        $(".dduMk").css({"gap": "0px", "justify-content": "space-between"});

    } else{
        $(".dduMk").css({"gap": "60px", "justify-content": "left"});
    }
})

function checkWidth(){
    if(window.innerWidth<610){
        $(".dduMk").css({"gap": "0px", "justify-content": "space-between"});
    } else{
        $(".dduMk").css({"gap": "60px", "justify-content": "left"});
    }
}


//닉네임 변경 input 클릭 시
$nickname.on("click", function(){
    $(this).css("border", "1px solid #b3d4fc");
})
//닉네임 변경 input 벗어났을 때
$nickname.on("blur", function(){
    $(this).css("border", "1px solid rgb(238, 238, 238)");
})

//닉네임 유효성 검사
$save.on("click", function(){
    //닉네임 입력하지 않았을 경우
    if($nicknameInput.val()==0){
        alert("닉네임을 입력해주세요.");
        return;
    }
})


// 프로필 사진 변경
$profilePicture.on("click", function(){
    $(".fSxFPc").click();
})

// 프로필 사진 썸네일 띄우기
$(".fSxFPc").on("change", function(event) {
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.onload = function(e) {
        $(".gFQPgt").attr("src", e.target.result);
    }
    reader.readAsDataURL(file);
});

// 내 정보 저장
$(".jMUPmW").on("click", function(){
    alert("회원 정보가 저장되었습니다.");
})