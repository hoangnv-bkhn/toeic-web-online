$(document).ready(function () {
    bindEventCheckAllCheckbox("bindEventCheckAllCheckbox");
});

function bindEventCheckAllCheckbox(id) {
    $("#" + id).on('change', function () {
        if ($(this).checked()) {
            $(this).closest("table").find("input[type=checkbox]").prop("checked", true);
        } else {
            $(this).closest("table").find("input[type=checkbox]").prop("checked", false);
        }
    });
}