$(document).ready(function () {
    $('#generateProblem').click(function () {
        var selectedTags = [];
        var minRating = $("#rating").val().split(" - ")[0];
        var maxRating = $("#rating").val().split(" - ")[1];
        $('#tags .selected-tag').each(function () {
            selectedTags.push($(this).text().slice(0, -1));
        });
        $.ajax({
            url: '/GenerateProblem',
            type: 'post',
            data: {
                tags: selectedTags.join(','),
                minRating: minRating.toString(),
                maxRating: maxRating.toString()
            },
            success: function (response) {
                $('#problem').html(response);
            }
        });
    });
});
