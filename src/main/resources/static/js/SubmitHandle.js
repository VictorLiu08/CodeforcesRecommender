$(document).ready(function() {
    $('#submitHandle').click(function() {
        $('#loadingIcon').show();
        var handle = $('#handle').val();
        $.ajax({
            url: '/process',
            type: 'post',
            data: {
                handle: handle,
            },
            success: function(response) {
                $('#Results').html(response);
            },
            complete: function() {
                $('#loadingIcon').hide();
            }
        });
    });
});
