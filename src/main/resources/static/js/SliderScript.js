$(function () {
    $("#slider-range").slider({
        range: true,
        min: 800,
        max: 3500,
        values: [800, 3500],
        step: 100,
        slide: function (event, ui) {
            $("#rating").val(ui.values[0] + " - " + ui.values[1]);
        }
    });
    $("#rating").val($("#slider-range").slider("values", 0) +
        " - " + $("#slider-range").slider("values", 1));
});