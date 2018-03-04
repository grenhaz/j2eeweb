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
function openWindow(url, data, extra) {
    var options = $.extend({
        'title': '&nbsp;',
        'message': '<div class="progress"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%"></div></div>'
    }, extra);
    var dialog = bootbox.dialog(options);
    
    $.get(url, data, function (ret) {
        $(dialog).find('.bootbox-body').html(ret);
    });
    
    return dialog;
}

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