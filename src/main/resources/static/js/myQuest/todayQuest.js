let todayQuestService = (function(){

    function getTodayQuest(callback, error){
        $.ajax({
            url: "/myCompleteQuest/todayQuest",
            type: "get",
            success: function (questDTO,status, xhr) {
                if(callback){
                    callback(questDTO);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }


    return {getTodayQuest: getTodayQuest}
}) ();