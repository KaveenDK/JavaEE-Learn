$("#eventBtn").on("click", function () {
    const xhttp = new XMLHttpRequest();
    xhttp.open('GET', "http://localhost:8080/APP_One_war_exploded/event", true);

    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            let rows = JSON.parse(xhttp.responseText);

            rows.forEach(row => {
                console.log(row);
                $("#event-table tbody").append(
                    `<tr>
                       <td>${row.eid}</td>
                       <td>${row.ename}</td>
                       <td>${row.edescription}</td>
                       <td>${row.edate}</td>
                       <td>${row.eplace}</td>
                   </tr>`
                );
            });
        }
    };
    xhttp.send();
});