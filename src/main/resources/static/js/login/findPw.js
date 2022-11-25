/* 이메일 정규식,input박스 입력값, uuid생성 및 url경로값 저장하는 변수선언 */
var emailRegex=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
var $inputemail=findForm.email;
var uuid=([1e7]+-1e3+-4e3+-8e3+-1e11).replace(/[018]/g, c=>(c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c/4).toString(16));
var url=location.href;

console.log(uuid);

/* 입력박스 클릭시 테두리 변경 */
$(findForm.email).on("click",function(){
    $(this).css("border","1px solid blue");
});

/* 아이디 입력박스 위치에서 다른 곳 클릭시 */
$(findForm.email).on("blur",function(e){
    $("input[name='email']").css("border","");
});

/* 이메일 입력값의 변화를 확인하는 부분 */
$(findForm.email).on("change input",function(){
    /* 이메일 정규식표현식 통과 못할시 */
    if(!emailRegex.test($inputemail.value)){
        $("input[name='email']").attr("class","Form__Input-sc-1quypp7-1 hYhAPw");
        $(".cIOZzg").text("올바른 이메일 형식이 아닙니다.");
        $(".cIOZzg").attr("class","Form__Description-sc-1quypp7-2 bViOzS");
        $("button[name='sendemail']").attr("disabled",true);
        $("button[name='sendemail']").attr("class","Button-bqxlp0-0 ppTTf");
    }
    /* 이메일 정규표현식 통과시 */
    else{
        $("input[name='email']").attr("class","Form__Input-sc-1quypp7-1 iRBMai");
        $(".bViOzS").text("재설정하려는 비밀번호의 아이디(이메일)를 입력해 주세요.");
        $(".bViOzS").attr("class","Form__Description-sc-1quypp7-2 cIOZzg");
        $("button[name='sendemail']").attr("disabled",false);
        $("button[name='sendemail']").attr("class","Button-bqxlp0-0 bGHqGe");
    }
});