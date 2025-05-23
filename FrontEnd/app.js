$("#eventBtn").on("click", function () {
    console.log("clicked");
    $.ajax({
        url: "http://localhost:8080/APP_One_war_exploded/event",
        success: function (resp) {
            console.log(resp);
        }
    });
});