var f = true;

$(document).ready(function(){
    loadData();
});

function loadData() {

    $.getJSON('/user/all', function (data) {
        if (data.length != 0){
            for (var i = 0; i < data.length; i++) {
                var tr = $('<tr>')
                    .append($('<td>').html('<input type="checkbox" class="check" value=' + data[i].id + '>'))
                    .append($('<td>').text(data[i].firstName))
                    .append($('<td>').text(data[i].lastName));
                 $('#tbody').append(tr);
            }
        }else {
            $('.data').remove();
        }
    });

}

function del() {

    var data = { 'ids' : []};
    $(":checked").each(function() {
        data['ids'].push($(this).val());
    });

    $.ajax({
        url: "/user/delete",
        method: "POST",
        data: data
    });

    window.location.reload();

}

function add() {
    var json = $("#add_form").serialize();
    $.post("/user/new", json, function (data) {
        $('.error_label').text(data);
        if (data == "")
            setTimeout(function(){window.location.reload()}, 500);
    });

}

function selectAll(){
    $('input[type="checkbox"]').prop("checked", f);
    f = !f;
}
