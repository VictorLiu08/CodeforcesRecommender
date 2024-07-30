$(document).ready(function() {
    $('#fetch-user-btn').click(function() {
        $('#user-info').html('<div class="loading" id="loadingIcon"></div>');
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
