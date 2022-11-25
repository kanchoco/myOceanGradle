/* 이미지 전환 딜레이 3초 */
var count=0;
const $windowWidths = $(window).width();

checkWidths();


if(matchMedia("screen and (max-width: 769px)").matches){
    $(".egtVMY").attr("style","display:flex");
}
else{
    $(".egtVMY").attr("style","display:none");
}

function checkWidths(){
    if($windowWidths<769){
        $(".egtVMY").attr("style","display:flex");
    }
    else{
        $(".egtVMY").attr("style","display:none");
    }
}

$(window).on("resize",function(){
    if (window.innerWidth < 769) {
        $(".egtVMY").attr("style","display:flex");
    }
    else{
        $(".egtVMY").attr("style","display:none");
    }
}).trigger("resize");

// $(window).resize(function() {
//     if (window.innerWidth < 769) {
//         $(".egtVMY").attr("style","display:flex");
//     }
//     else{
//         $(".egtVMY").attr("style","display:none");
//     }
// });

setInterval(function(){
    switch(count){
        case 0:
            $(".hoYWgp").next().attr("class","Banner__BackgroundImage-sc-13uuyx6-2 fMxjfF");
            $(".hoYWgp").next().next().attr("class","Banner__BackgroundImage-sc-13uuyx6-2 hpebkH");
            break;
        case 1:
            $(".hoYWgp").next().next().attr("class","Banner__BackgroundImage-sc-13uuyx6-2 fMxjfF");
            $(".hoYWgp").next().next().next().attr("class","Banner__BackgroundImage-sc-13uuyx6-2 hpebkH");
            break;
        case 2:
            $(".hoYWgp").next().next().next().attr("class","Banner__BackgroundImage-sc-13uuyx6-2 fMxjfF");
            $(".hoYWgp").next().next().next().next().attr("class","Banner__BackgroundImage-sc-13uuyx6-2 hpebkH");
            break;
        case 3:
            $(".hoYWgp").next().next().next().next().attr("class","Banner__BackgroundImage-sc-13uuyx6-2 fMxjfF");
            $(".hoYWgp").next().next().next().next().next().attr("class","Banner__BackgroundImage-sc-13uuyx6-2 hpebkH");
            break;
        case 4:
            $(".hoYWgp").next().next().next().next().next().attr("class","Banner__BackgroundImage-sc-13uuyx6-2 fMxjfF");
            $(".hoYWgp").next().attr("class","Banner__BackgroundImage-sc-13uuyx6-2 hpebkH");
            break;
    }
    ++count;
    count%=5;
    console.log("count:"+count);
},3000);
