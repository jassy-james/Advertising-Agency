$(document).ready(function() {
  $('.up').click(function () {
    var scroll = $(this).attr('href');
    if ($(scroll).length != 0) {
        $('html, body').animate({
            scrollTop: $(scroll).offset().top
        }, 500);
    }
    return false;
  });
});

$(document).ready(function() {
  $('.btn-warning').on('click', '.btn-warning', function() {
    $('#addProductForm').hide(500);
  });
});