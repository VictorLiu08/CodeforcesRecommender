$(document).ready(function() {
    $('#fetch-user-btn').click(function() {
        $('#loadingIcon').show();
        $('#user-info').css("display", "flex");
        var handle = $('#handle').val();
        $.ajax({
            url: '/process',
            type: 'post',
            data: {
                handle: handle,
            },
            success: function(response) {
                $('#user-info').html(response);
            },
            complete: function() {
                $('#loadingIcon').hide();
            }
        });
    });
});
