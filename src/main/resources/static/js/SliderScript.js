$(function () {
    $("#slider-range").slider({
        range: true,
        min: 800,
        max: 3500,
        values: [800, 3500],
        step: 100,
        slide: function (event, ui) {
            $("#rating").text(ui.values[0] + " - " + ui.values[1]);
        }
    });
    $("#rating").text($("#slider-range").slider("values", 0) +
        " - " + $("#slider-range").slider("values", 1));
});