$(function () {
    // TODO: Sección inicial
    var anchor = window.location.hash;
    if (anchor !== '' && anchor.length > 1) {
        var link = anchor.substring(1);
        $.get(__BASE + link.replaceAll('_', '/'), function (ret) {
            $('.articles').html(ret);
        });
    }
    
    // TODO: Cambio de secciones
    $(document.body).on('click', 'ul.list-sections li a', function(e) {
        var link = $(this).attr('href');
        console.log(link);
        $.get(__BASE + '/articles/all', {}, function (ret) {
            
        });
        return true;
    });
    
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