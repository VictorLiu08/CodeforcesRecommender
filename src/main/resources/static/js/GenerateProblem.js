$(document).ready(function () {
    $('#generateProblem').click(function () {
        var selectedTags = [];
        var minRating = $("#slider-range").slider("values", 0);
        var maxRating = $("#slider-range").slider("values", 1);
        $('#tags .selected-tag').each(function () {
            selectedTags.push($(this).text().slice(0, -1));
        });
        $.ajax({
            url: '/GenerateProblem',
            type: 'post',
            data: {
                tags: selectedTags.join(','),
                minRating: minRating,
                maxRating: maxRating
            },
            success: function (response) {
                $('#results').html(response);
            }
        });
    });
});
