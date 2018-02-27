$(function () {
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