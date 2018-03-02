function refreshBlock(destination, url, scroll) {
    if (scroll) {
        // TODO: Scroll a la posición
        var aTag = $('#return-top');
        if (aTag.length > 0) {
            //console.log("top: " + aTag.offset().top);
            //console.log("scroll: " + $(document).scrollTop());
            $('html,body').animate({
                scrollTop: aTag.offset().top + $(document).scrollTop()
            }, 'slow');
        }
    }
    
    $.get(url, function (ret) {
        $(destination).html(ret);
    });
};

$(function () {
    if (typeof __TAG !== 'undefined') {
        // Sección inicial
        var anchor = window.location.hash;
        if (anchor !== '' && anchor.length > 1) {
            var link = anchor.substring(1);
            refreshBlock('.articles', __BASE + 'ajax/' + link.replace(/\_/g, '/'), false);
        } else {
            refreshBlock('.articles', __BASE + 'ajax/' + (__TAG !== '' && __TAG !== undefined  ? __TAG + '/' : '') + 'all', false);
        }
    }
    
    // Cambio de secciones
    $(document.body).on('click', 'a.go', function(e) {
        var url = $(this).attr('href');
        var index = url.indexOf('#');
        if (index >= 0) {
            var link = url.substring(index + 1);
            var destination = $(this).data('destination');
            if (destination === undefined) {
                destination = '.articles';
            }
            refreshBlock(destination, __BASE + 'ajax/' + link.replace(/\_/g, '/'), true);
        }
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