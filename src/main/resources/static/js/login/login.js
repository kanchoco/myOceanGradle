/* 아이디,패스워드 입력값 */
var $emailCheck=loginForm.email;
var $passwordCheck=loginForm.password;
var emailRegex=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
var dummypassword="qweqwe123!@#";

/* 아이디 입력박스 클릭시 */
$(loginForm.email).on("click",function(e){
    $(this).css("border","1px solid blue");
    $("#passwordbox").css("border","");
});

/* 패스워드 입력박스 클릭시 */
$(loginForm.password).on("click",function(e){
    $(this).css("border","1px solid blue");
    $("#emailbox").css("border","");
});

/* 아이디 입력박스 위치에서 다른 곳 클릭시 */
$(loginForm.email).on("blur",function(e){
    $("input[name='email']").css("border","");
});
/* 패스워드 입력박스 위치에서 다른 곳 클릭시 */
$(loginForm.password).on("blur",function(e){
    $("input[name='password']").css("border","");
});

/* 아이디 입력박스 입력값이 입력될 때마다 검사 */
$(loginForm.email).on("change input",function(e){

    /* 이메일 정규표현식 통과시 */
    if(!emailRegex.test($emailCheck.value)){
        $(".bViOzS").text("올바른 이메일 형식이 아닙니다.");
        $(".bViOzS").css("color","rgb(222,28,34)");
        $("input[name='email']").css("border","1px solid rgb(222,28,34)");
    }

    /* 이메일 정규표현식 통과 또는 이메일 입력값이 없을때 */
    if($emailCheck.value=="" || emailRegex.test($emailCheck.value)){
        $(".bViOzS").text("");
        $("input[name='email']").css("border","");
    }
    /* 이메일 입력값이 없을때 */
    if($emailCheck.value==""){
        $("button[name='logindata']").attr("disabled",true);
        $("button[name='logindata']").attr("class","iikbgZ");
    }
});


/* 패스워드 입력박스 입력값이 입력될 때마다 검사 */
$(loginForm.password).on("change input",function(e){
    if(!$passwordCheck.value){
        $("input[name='password']").css("border","1px solid rgb(222,28,34)");
        $("button[name='logindata']").attr("disabled",true);
        $("button[name='logindata']").attr("class","iikbgZ");
        /* 패스워드 입력박스 위치에서 패스워드 값이 없을때 다른 곳 클릭시 */
        $(loginForm.password).on("blur",function(){$("input[name='password']" ).css("border","1px solid rgb(222,28,34)");});
        loginForm.password.focus();
    }
    else{
        $("input[name='password']").css("border","1px solid blue");
        /* 패스워드 입력박스 위치에서 패스워드 값이 있을때 다른 곳 클릭시 */
        $(loginForm.password).on("blur",function(){$("input[name='password']" ).css("border","");});
        $("button[name='logindata']").attr("disabled",false);
        $("button[name='logindata']").attr("class","dDCuWg");
    }
});

/* 이메일 또는 패스워드중 하나라도 일치하지 않으면 뜨는 검은 상자 */
$("button[name='logindata']").on("click",function(){
    if(emailRegex.test($emailCheck.value) && ($passwordCheck.value==dummypassword)){
        $("button[name='logindata']").attr("type","submit");
        loginForm.submit();
    }else{
        $(".bpzBFC").fadeIn(200);
        setTimeout(function(){
            $(".bpzBFC").attr("style","display:flex; opacity:0.9;");
            $(".bpzBFC").text("아이디 / 비밀번호를 확인해주세요.");
            setTimeout(function(){
                $(".bpzBFC").fadeOut(200);
                $(".bpzBFC").attr("style","opacity:0;");
            },1500);
        },500);
    }
});