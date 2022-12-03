/* input태그의 입력값을 읽기위한 변수, 패스워드 정규식, 이메일링크 클릭후 넘어오는 파라미터값,db uuid값 */
var $password=passwordForm.password;
var $passwordCheck=passwordForm.passwordConfirm;
var passwordRegex=/^(?=.*?[A-Z]{0})(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{10,}$/;
var $emailuuid=new URL(location.href).searchParams.get("verificationCode");
var $emailReceivedEmail=new URL(location.href).searchParams.get("email");
var $dbuuid;

/* 현재 시간과 db 시간값 차이를 확인하기 위한 변수 및 계산식(밀리초로 표현) */
var passedTime=new Date("2022-12-02 17:46:00").getTime();

var tonow=new Date().getMilliseconds();
const difftime=(tonow-passedTime)/1000;

/* 클릭시 input박스 테두리 변경 */
$(passwordForm.password).on("click",function(){
    $(this).css("border","1px solid blue");
    $(passwordForm.passwordConfirm).css("border","");
});
$(passwordForm.passwordConfirm).on("click",function(){
    $(this).css("border","1px solid blue");
    $(passwordForm.password).css("border","");
});

/* input박스 이외 부분 클릭시 변경 */
$(passwordForm.password).on("blur",function(){
    $(passwordForm.password).css("border","");
});
$(passwordForm.passwordConfirm).on("blur",function(){
    $(passwordForm.passwordConfirm).css("border","");
});

/* 비밀번호 입력박스 입력값이 입력될 때마다 검사 */
$(passwordForm.password).on("change input",function(){
    if(!passwordRegex.test($password.value)){
        $("input[name='password']").attr("class","Form__Input-sc-1quypp7-1 hYhAPw");
        $("p.cIOZzg").attr("class","Form__Description-sc-1quypp7-2 bViOzS");
        $("button[name='change']").attr("disabled",true);
        $("button[name='change']").attr("class","Button-bqxlp0-0 ppTTf");
        if($password.value==""){
            $("p.bViOzS").attr("class","Form__Description-sc-1quypp7-2 cIOZzg");
            $("input[name='password']").attr("class","Form__Input-sc-1quypp7-1 iRBMai");
            $("input[name='password']").css("border","");
            $("button[name='change']").attr("disabled",true);
            $("button[name='change']").attr("class","Button-bqxlp0-0 ppTTf");
        }
    }
    else if(passwordRegex.test($password.value)){
        $("p.bViOzS").attr("class","Form__Description-sc-1quypp7-2 cIOZzg");
        $("input[name='password']").attr("class","Form__Input-sc-1quypp7-1 iRBMai");
        $("input[name='password']").css("border","");
    }
});

/* 비밀번호 확인 입력박스 입력값이 입력될 때마다 검사 */
$(passwordForm.passwordConfirm).on("change input",function(){
    if($password.value!=$passwordCheck.value){
        $("input[name='passwordConfirm']").attr("class","Form__Input-sc-1quypp7-1 hYhAPw");
        $(".bViOzSS").text("먼저 입력하신 비밀번호와 일치하지 않습니다.");
        $("button[name='change']").attr("disabled",true);
        $("button[name='change']").attr("class","Button-bqxlp0-0 ppTTf");
        if($passwordCheck.value==""){
            $("p.bViOzSS").text("");
            $("input[name='passwordConfirm']").attr("class","Form__Input-sc-1quypp7-1 iRBMai");
            $("input[name='passwordConfirm']").css("border","");
            $("button[name='change']").attr("disabled",true);
            $("button[name='change']").attr("class","Button-bqxlp0-0 ppTTf");
        }
    }
    else{
        /* 비밀번호, 비밀번호 확인 입력값이 일치하면 */
        $("p.bViOzSS").text("");
        $("input[name='passwordConfirm']").attr("class","Form__Input-sc-1quypp7-1 iRBMai");
        $("input[name='passwordConfirm']").css("border","");

        $("button[name='change']").attr("disabled",false);
        $("button[name='change']").attr("class","Button-bqxlp0-0 bGHqGe");
        $("button[name='change']").on("click",function(){
            requestFinduser();
            /* 하단의 검은색 박스 나타났다 사라지는 부분 */
            if((difftime>=300) || $emailuuid!=$dbuuid){
                // console.log(difftime);
                // console.log($dbuuid);
                // console.log($emailuuid);

                /* 하단의 검은박스 나타나는 부분 */
                $(".bpzBFC").fadeIn(1000);

                /* 나타난 하단의 검은박스 일정시간 이후 사라지는 부분 */
                setTimeout(function(){
                    $(".bpzBFC").attr("style","display:flex");
                    setTimeout(function(){
                        $(".bpzBFC").fadeOut(1000);
                    },1800);
                },600);

                /* 하단의 검은박스가 사라진 후 스타일 변경 부분 */
                setTimeout(function(){
                    $(".bpzBFC").attr("style","display:none");
                },3500);
            }
            /* 버튼 타입 submit으로 변경 */
            else{
                // savechangePw();
                $("button[name='change']").attr("type","submit");
            }
        });
    }
});

function requestFinduser(){
    $.ajax({
        type:"post",
        url:"requestFindUser",
        headers:{"Content-Type":"application/json"},
        data:$emailReceivedEmail,
        dataType:"json",
        success:function(result){console.log(result);
            console.log(result.userEmail);
            console.log(result.userFindtime);
            console.log(result.userId);
            console.log(result.userUuid);

            var dbrequestTime=result.userFindtime;
            var year=dbrequestTime.substr(0,4);
            var month=dbrequestTime.substr(5,7);
            var day=dbrequestTime.substr(8,10);
            var hour=dbrequestTime.substr(11,13);
            var minute=dbrequestTime.substr(14,16);
            var second=dbrequestTime.substr(17,19);
            var dbdate=new Date(year,month,day,hour,minute,second).getMilliseconds();

            console.log("diff:"+(tonow-dbdate)/1000);
        },
        error:function(status,error){;}
    })
}


function savechangePw(){
    $.ajax({
        type:"post",
        url:"saveChangePw",
        headers:{"Content-Type":"application/json"},
        data:$passwordCheck.value,
        dataType:"text",
        success:function(result){;},
        error:function(status,error){;}
    })
}