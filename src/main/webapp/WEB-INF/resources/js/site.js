function refreshBlock(destination, url, data, scroll) {
    if (scroll) {
        var aTag = $('#return-top');
        if (aTag.length > 0) {
            $('html,body').animate({
                scrollTop: aTag.offset().top + $(document).scrollTop()
            }, 'slow');
        }
    }
    
    $.get(url, data, function (ret) {
        $(destination).html(ret);
    });
};

$(function () {
    // Tooltips
    $('[data-toggle="tooltip"]').tooltip();
    
    // Slider de cambio de página
    $(window).on('beforeunload', function () {
        if ($('.loading-info').length === 0) {
            var $container = $('<div />')
                .addClass('loading-info slider')
                .appendTo('body');
            $('<div class="line"></div>').appendTo($container);
            $('<div class="break dot1"></div>').appendTo($container);
            $('<div class="break dot2"></div>').appendTo($container);
            $('<div class="break dot3"></div>').appendTo($container);
        }
    });
});