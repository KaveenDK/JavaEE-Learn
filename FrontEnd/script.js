$(document).ready(function () {
    loadEvents();

    function loadEvents() {
        $("#event-table tbody").empty();
        $.ajax({
            url: "http://localhost:8080/APP_One_war_exploded/event",
            method: "GET",
            success: function (rows) {
                rows.forEach(row => {
                    $("#event-table tbody").append(
                        `<tr>
                           <td>${row.eid}</td>
                           <td>${row.ename}</td>
                           <td>${row.edescription}</td>
                           <td>${row.edate}</td>
                           <td>${row.eplace}</td>
                           <td>
                               <button class="btn btn-warning btn-sm update-btn">
                                   <i class="bi bi-pencil-square"></i> Update
                               </button>
                               <button class="btn btn-danger btn-sm delete-btn">
                                   <i class="bi bi-trash"></i> Delete
                               </button>
                           </td>
                       </tr>`
                    );
                });
            }
        });
    }

    $("#addEventBtn").on("click", function () {
        const event = {
            eid: $("#eid").val(),
            ename: $("#ename").val(),
            edescription: $("#edescription").val(),
            edate: $("#edate").val(),
            eplace: $("#eplace").val()
        };

        $.ajax({
            url: "http://localhost:8080/APP_One_war_exploded/event",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(event),
            success: function () {
                alert("Event added successfully!");
                loadEvents();
                clearFields();
            }
        });
    });

    $(document).on("click", ".update-btn", function () {
        const row = $(this).closest("tr");
        const event = {
            eid: row.find("td:eq(0)").text(),
            ename: row.find("td:eq(1)").text(),
            edescription: row.find("td:eq(2)").text(),
            edate: row.find("td:eq(3)").text(),
            eplace: row.find("td:eq(4)").text()
        };

        $("#eid").val(event.eid).prop("disabled", true);
        $("#ename").val(event.ename);
        $("#edescription").val(event.edescription);
        $("#edate").val(event.edate);
        $("#eplace").val(event.eplace);
        $("#updateEventBtn").show();
        $("#addEventBtn").hide();
    });

    $("#updateEventBtn").on("click", function () {
        const event = {
            eid: $("#eid").val(),
            ename: $("#ename").val(),
            edescription: $("#edescription").val(),
            edate: $("#edate").val(),
            eplace: $("#eplace").val()
        };

        $.ajax({
            url: "http://localhost:8080/APP_One_war_exploded/event",
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify(event),
            success: function () {
                alert("Event updated successfully!");
                loadEvents();
                clearFields();
                $("#updateEventBtn").hide();
                $("#addEventBtn").show();
                $("#eid").prop("disabled", false);
            }
        });
    });

    $(document).on("click", ".delete-btn", function () {
        const eid = $(this).closest("tr").find("td:eq(0)").text();

        if (confirm("Are you sure you want to delete this event?")) {
            $.ajax({
                url: `http://localhost:8080/APP_One_war_exploded/event?eid=${eid}`,
                method: "DELETE",
                success: function () {
                    alert("Event deleted successfully!");
                    loadEvents();
                }
            });
        }
    });

    $(document).on("click", "#event-table tbody tr", function () {
        $(this).addClass("selected-row").siblings().removeClass("selected-row");
    });

    function clearFields() {
        $("#eid").val("").prop("disabled", false);
        $("#ename").val("");
        $("#edescription").val("");
        $("#edate").val("");
        $("#eplace").val("");
    }
});