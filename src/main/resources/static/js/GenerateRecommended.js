$(document).ready(function () {
    $('#generate-btn1').prop('disabled', true);
    $('#fetch-user-btn').prop('disabled', true);

    $('#handle').on('input', function () {
        const inputField = $('#handle');
        const submitButton = $('#fetch-user-btn');

        if (inputField.val().trim() !== "") {
            submitButton.prop('disabled', false);
        } else {
            submitButton.prop('disabled', true);
        }
    });

    $('#fetch-user-btn').click(function () {
        $('#generate-btn1').prop('disabled', false);
    });

    $('#generate-btn1').click(function () {
        $.ajax({
            url: '/RecommendProblem',
            type: 'post',
            data: {
            },
            success: function (response) {
                $('#results').html(response);
            }
        });
    });
});
