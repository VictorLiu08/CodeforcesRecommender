$(document).ready(function () {
    $('#generateRecommended').prop('disabled', true);
    $('#submitHandle').prop('disabled', true);

    $('#handle').on('input', function () {
        const inputField = $('#handle');
        const submitButton = $('#submitHandle');

        if (inputField.val().trim() !== "") {
            submitButton.prop('disabled', false);
        } else {
            submitButton.prop('disabled', true);
        }
    });

    $('#submitHandle').click(function () {
        $('#generateRecommended').prop('disabled', false);
    });

    $('#generateRecommended').click(function () {
        $.ajax({
            url: '/RecommendProblem',
            type: 'post',
            data: {
            },
            success: function (response) {
                $('#problem').html(response);
            }
        });
    });
});
