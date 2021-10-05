$(function () {
    'use strict';


    /*****************************************************
     * Loader
     *****************************************************/
    $(".loaders > img:gt(0)").hide();

    setInterval(function() {
        $('.loaders> img:first')
            .fadeOut(1000)
            .next()
            .fadeIn(1000)
            .end()
            .appendTo('.loaders');
    },  2000);
    $(window).on("load", function () {
        $("#page-loader").css('display', 'none');
    });

    /*****************************************************
     * General Targets
     *****************************************************/
    function sliderContents() {
        var targetContainer = $('#slider-contents .jumbotron'),
            mainSliderContentsHeight = $(targetContainer).outerHeight();
        console.log(mainSliderContentsHeight);
        $(targetContainer).css('margin-top', -( mainSliderContentsHeight/2 ));
        console.log(mainSliderContentsHeight);
    }
    sliderContents();
    $( window ).on("load", function() {
        sliderContents();
    });

    $( window ).on("resize", function() {
        sliderContents();
    });

    $(".property-map-wrapper #advance-search .top-btn").on('click', function () {
        $(".property-map-wrapper #advance-search .top-btn").toggleClass('active');
        $(".property-map-wrapper #advance-search .top-btn i").toggleClass('fa-caret-up');
        $("#adv-search-form").stop(true, true).slideToggle();
    });

    $('time.updated').each(function(){
        var me = $(this);
        me.html(me.html().replace(/^(\w+)/, '<strong>$1</strong>'));
    });

    /*****************************************************
     * Main Menu
     *****************************************************/
    $("#site-nav li").on('mouseenter mouseleave', function () {
        $(this).children("ul").stop(true, true).slideToggle(300);
    });



    /*****************************************************
     * Image Zoom
     * https://github.com/fat/zoom.js/
     ************************"*****************************/
    $("a.zoom").on("click", function (e) {
        e.preventDefault();
    });



    /*****************************************************
     * Slick Slider
     * http://kenwheeler.github.io/slick/
     *****************************************************/
    if( jQuery().slick ) {
        $('#main-slider').slick({
            dots: false,
            infinite: true,
            fade: true,
            speed: 1000,
            autoplaySpeed: 3000,
            lazyLoad: 'progressive',
            cssEase: 'linear',
            adaptiveHeight:true,
            autoplay: true,
            prevArrow: '<i class="fa fa-angle-right"></i>',
            nextArrow: '<i class="fa fa-angle-left"></i>',
            responsive: [
                {
                    breakpoint: 991,
                    settings: {
                        arrows: false
                    }
                }
            ]
        });

        $('#property-for-rent-slider').slick({
            slidesToShow: 3,
            slidesToScroll: 1,
            dots: false,
            infinite: true,
            autoplay: true,
            speed: 1000,
            autoplaySpeed: 2000,
            prevArrow: '<i class="fa fa-angle-left"></i>',
            nextArrow: '<i class="fa fa-angle-right"></i>',
            responsive: [
                {
                    breakpoint: 1200,
                    settings: {
                        slidesToShow: 2
                    }
                },
                {
                    breakpoint: 767,
                    settings: {
                        arrows: false,
                        slidesToShow: 2
                    }
                },
                {
                    breakpoint: 600,
                    settings: {
                        arrows: false,
                        slidesToShow: 1
                    }
                }
            ]
        });
    }



    /*****************************************************
     * Scroll Top
     *****************************************************/
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('#scroll-top').fadeIn();
        } else {
            $('#scroll-top').fadeOut();
        }
    });
    $("a[href='#top']").on('click', function() {
        $("html, body").animate({ scrollTop: 0 }, "slow");
        return false;
    });



    /*****************************************************
     * Properties Layout Function
     *****************************************************/
    var propertyItem =$(".layout-item-wrap"),
        layoutLinks= $('.layout-view .fa'),
        listStyle= $('.layout-item');

    layoutLinks.on('click', function () {
        var targetClass = 'col-xs-' + $(this).data('layout');

        propertyItem.removeClass(function (index, className) {
            return (className.match (/\bcol-\S+/g) || []).join(' ');
        });
        propertyItem.addClass(targetClass);

        if (propertyItem.hasClass('col-xs-12')) {
            listStyle.addClass('list-style');
        } else {
            listStyle.removeClass('list-style');
        }
        layoutLinks.removeClass('selected');
        $(this).addClass('selected');
        return false;
    });




    /*****************************************************
     * Tabs
     *****************************************************/
    $( "#tabs, #widget-tabs" ).tabs();



    /*****************************************************
     * Slick Nav
     * http://slicknav.com/
     *****************************************************/
    if(jQuery().slicknav ) {
        $('#site-nav > ul').slicknav({
            prependTo: "#site-header-top + .container >.row"
        });
    }



    /*****************************************************
     * Twitter Feeds
     * https://github.com/sonnyt/Tweetie
     *****************************************************/
    if(jQuery().twittie ) {
        $('#twitter-feeds').twittie({
            username: 'themesinspire',
            'count': 2,
            'hideReplies': true,
            template: '<span class="avatar"> {{avatar}} </span> <p class="tweet">{{tweet}}</p>',
            loadingText: 'Fetching Tweets...'
        }, function () {

        });

    }



    /*****************************************************
     * Whats Nearby Function
     * https://github.com/LaGrangeMtl/WhatsNearby
     *****************************************************/
    window.onload= function () {
        if(jQuery().whatsnearby ) {
            $("#nearby-places-map").whatsnearby({
                zoom: 15,
                width: "100%",
                address: "Montreal",
                placesRadius: 500,
                scrollwheel: false,
                placesTypes: ['restaurant', 'cafe', 'gym'],
                excludePlacesTypes: ['bar']
            });
        }
    };



    /*****************************************************
     * Contact Form AJAX validation and submission
     * Validation Plugin : http://bassistance.de/jquery-plugins/jquery-plugin-validation/
     * Form Ajax Plugin : http://www.malsup.com/jquery/form/
     ****************************************************/
    if(jQuery().validate && jQuery().ajaxSubmit)
    {

        var submitButton = $('#submit-button'),
            ajaxLoader = $('#ajax-loader'),
            messageContainer = $('#message-container'),
            errorContainer = $("#error-container");

        var formOptions = {
            beforeSubmit: function () {
                submitButton.attr('disabled', 'disabled');
                ajaxLoader.fadeIn('fast');
                messageContainer.fadeOut('fast');
                errorContainer.fadeOut('fast');
            },
            success: function (ajax_response, statusText, xhr, $form) {
                function parseMyHeader(){
                    try {
                        return JSON.parse(ajax_response);
                    } catch(ex){
                        return false;
                    }
                }

                var response = parseMyHeader();
                ajaxLoader.fadeOut('fast');
                submitButton.removeAttr('disabled');
                if(!null && response.response == true){
                    if (response.response) {
                        $form.resetForm();
                        messageContainer.html(response.message).fadeIn('fast');
                    } else {
                        errorContainer.html(response.message).fadeIn('fast');
                    }
                }else{
                    errorContainer.html(ajax_response).fadeIn('fast');
                }
            }
        };

        $('.contact-form').validate({
            errorLabelContainer: errorContainer,
            submitHandler: function (form) {
                $(form).ajaxSubmit(formOptions);
            }
        });
    }



    /*****************************************************
     *Animations
     * http://mynameismatthieu.com/WOW/
     *http://daneden.github.io/animate.css
     ******************************************************/
    function afterReveal( el ) {
        el.addEventListener('animationend', function( event ) {
            $(el).removeAttr('style');
        });
    }
    var wow = new WOW(
        {
            boxClass:     'wow',      // animated element css class (default is wow)
            animateClass: 'animated', // animation css class (default is animated)
            offset:       0,          // distance to the element when triggering the animation (default is 0)
            mobile:       false,       // trigger animations on mobile devices (default is true)
            live:         true,       // act on asynchronously loaded content (default is true)
           // callback:     afterReveal,
            scrollContainer: null // optional scroll container selector, otherwise use window
        }
    );
    $(window).on('load', function() {
        wow.init();
    });



    /*****************************************************
     * Mixup Filter Plugin
     * https://www.kunkalabs.com/mixitup/
     *****************************************************/
    var filterContainer = document.getElementById('filter-container');

    if($(filterContainer).length > 0){
        var mixer = mixitup(filterContainer, {
            selectors: {
                target: '.mix'
            },
            animation: {
                "duration": 618,
                effects: 'fade scale stagger(50ms)' // Set a 'stagger' effect for the loading animation
            },
            load: {
                filter: 'none' // Ensure all targets start from hidden (i.e. display: none;)
            }
        });

        // Add a class to the container to remove 'visibility: hidden;' from targets. This
        // prevents any flickr of content before the page's JavaScript has loaded.
        filterContainer.classList.add('mixitup-ready');

        // Show all targets in the container
        mixer.show()
            .then(function() {

                // Remove the stagger effect for any subsequent operations
                mixer.configure({
                    animation: {
                        effects: 'fade scale'
                    }
                });
            });
    }



}(jQuery));