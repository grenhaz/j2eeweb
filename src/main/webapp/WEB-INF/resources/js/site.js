function refreshArticles(url, scroll) {
    if (scroll) {
        // TODO: Scroll a la posición
        var aTag = $('#articles-top');
        console.log("top: " + aTag.offset().top);
        console.log("scroll: " + $(document).scrollTop());
        $('html,body').animate({
            scrollTop: 140//aTag.offset().top + $('.navbar').height()
        }, 'slow');
    }
    
    $.get(url, function (ret) {
        $('.articles').html(ret);
    });
};

$(function () {
    // Sección inicial
    var anchor = window.location.hash;
    if (anchor !== '' && anchor.length > 1) {
        var link = anchor.substring(1);
        refreshArticles(__BASE + 'ajax/' + link.replace(/\_/g, '/'), false);
    } else {
        refreshArticles(__BASE + 'ajax/' + (__TAG !== '' && __TAG !== undefined  ? __TAG + '/' : '') + 'all', false);
    }
    
    // Cambio de secciones
    $(document.body).on('click', 'a.go', function(e) {
        var url = $(this).attr('href');
        var index = url.indexOf('#');
        if (index >= 0) {
            var link = url.substring(index + 1);
            refreshArticles(__BASE + 'ajax/' + link.replace(/\_/g, '/'), true);
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