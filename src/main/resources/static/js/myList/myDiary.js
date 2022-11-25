const $checkYear = $(".year");
const $checkMonth = $(".month");
const $categoryDate = $(".e12i9j8n1");
const $prevDay = $(".prevArrow");
const $nextDay = $(".nextArrow");
let dayText = "";
let yearText = "";
let monthText = "";
let dateText = "";


// 캘린더 크기 미디어쿼리
checkCalendar();

$(window).resize(function(){
    if(window.innerWidth<1905){
        $(".e1k52epl0").css("font-size", "12px");
        $(".dateText").css("font-size", "12px");
        if(window.innerWidth<1650){
            $(".wholeWrap").removeAttr("style");
            $(".dateForm").removeAttr("style");
            $(".e12i9j8n0").removeAttr("style");
            $(".css-16df71f").removeAttr("style");
            $("#list_page_wrapper").removeAttr("style");

            $(".wholeWrap").css({"display":"flex", "flex-direction":"column"});
            $(".dateForm").css("width","98%");
            if(window.innerWidth>867){
                $(".e12i9j8n0").css({"width":"768px", "margin": "0 auto"});
            } else{
                $(".e12i9j8n0").css({"width":"95%", "margin": "0 auto"});
            }
            $(".e1k52epl0").css({"width":"32%"});
            $(".css-16df71f").css("float","none");
            $(".css-zr1uxp").css("height", "391px");
            $(".css-klg5aa").css("top", "-14px");
            $(".css-1kz857u").css("top", "-20px");
            $(".css-oys1qn").css("text-align", "center");
            $(".dayText").css({"top": "-25px", "position": "relative"});
            $(".dayTextArrow").css({"position":"relative", "top": "-20px"});
            $("#list_page_wrapper").css("margin", "0 auto");
        } else{
            $(".wholeWrap").removeAttr("style");
            $(".dateForm").removeAttr("style");
            $(".e12i9j8n0").removeAttr("style");
            $(".css-16df71f").removeAttr("style");
            $(".dateForm").removeAttr("style");
            $("#list_page_wrapper").removeAttr("style");


            $(".wholeWrap").css({"display":"grid", "grid-template-columns":"28% 72%", "grid-gap":"10px"});
            $(".dateForm").css("grid-template-rows","74px 450px");
            $(".e12i9j8n0").css({"width":"48%", "margin-top": "30px"});
            $(".e1k52epl0").css("width", "48%");
            $(".css-16df71f").css({"flex":"1 1 0%", "margin-bottom":"20px", "float":"right"});
            $(".css-zr1uxp").css("height", "519px");
            $(".css-klg5aa").css("top", "0px");
            $(".css-1kz857u").css("top", "0px");
            $(".css-oys1qn").css("text-align", "none");
            $(".dayText").css({"top": "0px", "position": "relative"});
            $(".dayTextArrow").css({"position":"relative", "top": "0px"});
            $("#list_page_wrapper").css("margin", "0px");
        }
    }
    else{
        $(".e1k52epl0").css("font-size", "17px");
        $(".dateText").css("font-size", "14px");
    }
})

function checkCalendar(){
    if(window.innerWidth<1905){
        $(".e1k52epl0").css("font-size", "12px");
        $(".dateText").css("font-size", "12px");
        if(window.innerWidth<1650){
            $(".wholeWrap").removeAttr("style");
            $(".dateForm").removeAttr("style");
            $(".e12i9j8n0").removeAttr("style");
            $(".css-16df71f").removeAttr("style");
            $("#list_page_wrapper").removeAttr("style");

            $(".wholeWrap").css({"display":"flex", "flex-direction":"column"});
            $(".dateForm").css("width","98%");
            if(window.innerWidth>867){
                $(".e12i9j8n0").css({"width":"768px", "margin": "0 auto"});
            } else{
                $(".e12i9j8n0").css({"width":"95%", "margin": "0 auto"});
            }
            $(".e1k52epl0").css({"width":"32%"});
            $(".css-16df71f").css("float","none");
            $(".css-zr1uxp").css("height", "391px");
            $(".css-klg5aa").css("top", "-14px");
            $(".css-1kz857u").css("top", "-20px");
            $(".css-oys1qn").css("text-align", "center");
            $(".dayText").css({"top": "-25px", "position": "relative"});
            $(".dayTextArrow").css({"position":"relative", "top": "-20px"});
            $("#list_page_wrapper").css("margin", "0 auto");
        } else{
            $(".wholeWrap").removeAttr("style");
            $(".dateForm").removeAttr("style");
            $(".e12i9j8n0").removeAttr("style");
            $(".css-16df71f").removeAttr("style");
            $(".dateForm").removeAttr("style");
            $("#list_page_wrapper").removeAttr("style");


            $(".wholeWrap").css({"display":"grid", "grid-template-columns":"28% 72%", "grid-gap":"10px"});
            $(".dateForm").css("grid-template-rows","74px 450px");
            $(".e12i9j8n0").css({"width":"48%", "margin-top": "30px"});
            $(".e1k52epl0").css("width", "48%");
            $(".css-16df71f").css({"flex":"1 1 0%", "margin-bottom":"20px", "float":"right"});
            $(".css-zr1uxp").css("height", "519px");
            $(".css-klg5aa").css("top", "0px");
            $(".css-1kz857u").css("top", "0px");
            $(".css-oys1qn").css("text-align", "none");
            $(".dayText").css({"top": "0px", "position": "relative"});
            $(".dayTextArrow").css({"position":"relative", "top": "0px"});
            $("#list_page_wrapper").css("margin", "0px");
        }
    }
    else{
        $(".e1k52epl0").css("font-size", "17px");
        $(".dateText").css("font-size", "14px");
    }
}



// 카테고리 변경 시
$categoryDate.on("click", function(){
    $categoryDate.attr("class", "css-1pm7srj e12i9j8n1");
    $(this).attr("class","css-10lvc7b e12i9j8n1");
    if($(this).text() == '연'){
        $("#monthCheck").hide();
        $("#yearCheck").show();
        $("#dayCheck").hide();
    } else if($(this).text() == '월'){
        $("#monthCheck").show();
        $("#yearCheck").hide();
        $("#dayCheck").hide();
    } else{
        $("#monthCheck").hide();
        $("#yearCheck").hide();
        $("#dayCheck").show();
    }
})

// 일자 변경(감소)
$prevDay.on("click", function(){
    dayText = $(".dayText").text();
    if(dayText>1) {
        if (dayText - 1 < 10) {
            dayText = "0" + (dayText - 1);
        } else {
            dayText -= 1;
        }
        $(".dayText").text(dayText);
    }
})

// 일자 변경(증가)
$nextDay.on("click", function(){
    dayText = $(".dayText").text();
    dayText = parseInt(dayText);

    if(dayText<31){
        if (dayText + 1 < 10) {
            dayText = "0" + (dayText + 1);
        } else {
            console.log(dayText);
            dayText += 1;
        }
        $(".dayText").text(dayText);
    }
})

// 날짜 선택 시 배경색 변경
$checkYear.on("click", function(){
    $checkYear.attr("class", "css-vvmm1k e1k52epl0 year");

    if($(this).attr("class")=="css-vvmm1k e1k52epl0 year check"){
        $(this).attr("class", "css-vvmm1k e1k52epl0 year");
    } else{
        $(this).attr("class", "css-vvmm1k e1k52epl0 year check");
    }
})

$checkMonth.on("click", function(){
    $checkMonth.attr("class", "css-vvmm1k e1k52epl0 month");

    if($(this).attr("class")=="css-vvmm1k e1k52epl0 month check"){
        $(this).attr("class", "css-vvmm1k e1k52epl0 month");
    } else{
        $(this).attr("class", "css-vvmm1k e1k52epl0 month check");
    }
})

$("button.eklkj752").on("click", function(){
    let today = new Date();
    let year = today.getFullYear();
    let month = ('0' + (today.getMonth() + 1)).slice(-2);
    let day = ('0' + today.getDate()).slice(-2);
    yearText = $(".year.check").text();
    monthText = $(".month.check").text();
    dayText = $(".dayText").text();
    if(!yearText && !monthText){
        dateText = year +"년 " + month + "월 " + dayText + "일";
        $(".selectDate").text(dateText);
        return;
    }

    if(!yearText){
        yearText = year + "년";
    }
    if(!monthText){
        dateText = yearText;
        $(".selectDate").text(dateText);
        return;
    }

    dateText = yearText + " " + monthText + " " + dayText+ "일";
    $(".selectDate").text(dateText);
})