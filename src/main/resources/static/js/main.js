"use strict";
(function(){
    // This forces the page to scroll to the top when refreshed
    $(document).load().scrollTop(0);

    // const script = document.getElementById('search-js');
    // script.onload = () => {
    //      mapboxsearch.autofill({accessToken: MapBox_API_KEY});
    //
    //     mapboxsearch.config.accessToken = MapBox_API_KEY;
    //     // Adding confirmation prompt to Users address
    //     const form = document.querySelector('form');
    //     form.addEventListener("submit", async (e) => {
    //         e.preventDefault();
    //         const result = await confirmAddress(form, {
    //             minimap: true,
    //             skipConfirmModal: (feature) =>
    //                 ['exact', 'high'].includes(
    //                     feature.properties.match_code.confidence
    //                 )
    //         });
    //         if (result.type === 'nochange') submitForm();
    //     });
    // };

    // // Mapbox Address Confirmation Modal
    // document.getElementById("search-js").onload = () => {
    //     mapboxsearch.config.accessToken = MapBox_API_KEY;
    //
    //     // Adding confirmation prompt to Users address
    //     const form = document.querySelector('mapbox-address-autofill');
    //     form.addEventListener("submit", async (e) => {
    //         e.preventDefault();
    //         const result = await mapboxsearch.confirmAddress(form, {
    //             theme: { variables: {border: '3px solid rgba(0,0,0,0.35)', borderRadius: '18px'} },
    //             minimap: {
    //                 defaultMapStyle: ['mapbox', 'outdoors-v11'],
    //                 satelliteToggle: true
    //             },
    //             // skipConfirmModal: (feature) => false // overrides default behavior, show dialog every time
    //         });
    //         console.log(result);
    //     });
    // }

    // Registration Form
    let current_fs, next_fs, previous_fs; //fieldsets
    let left, opacity, scale; //fieldset properties which we will animate
    let animating; //flag to prevent quick multi-click glitches

    $(".next").click(function(){
        if(animating) return false;
        animating = true;

        current_fs = $(this).parent();
        next_fs = $(this).parent().next();

        //activate next step on progressbar using the index of next_fs
        $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

        //show the next fieldset
        next_fs.show();
        //hide the current fieldset with style
        current_fs.animate({opacity: 0}, {
            step: function(now, ) {
                //as the opacity of current_fs reduces to 0 - stored in "now"
                //1. scale current_fs down to 80%
                scale = 1 - (1 - now) * 0.2;
                //2. bring next_fs from the right(50%)
                left = (now * 50)+"%";
                //3. increase opacity of next_fs to 1 as it moves in
                opacity = 1 - now;
                current_fs.css({'transform': 'scale('+scale+')'});
                next_fs.css({'left': left, 'opacity': opacity});
            },
            duration: 800,
            complete: function(){
                current_fs.hide();
                animating = false;
            },
            //this comes from the custom easing plugin
            easing: 'easeInOutBack'
        });
    });

    $(".previous").click(function(){
        if(animating) return false;
        animating = true;

        current_fs = $(this).parent();
        previous_fs = $(this).parent().prev();

        //de-activate current step on progressbar
        $("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

        //show the previous fieldset
        previous_fs.show();
        //hide the current fieldset with style
        current_fs.animate({opacity: 0}, {
            step: function(now) {
                //as the opacity of current_fs reduces to 0 - stored in "now"
                //1. scale previous_fs from 80% to 100%
                scale = 0.8 + (1 - now) * 0.2;
                //2. take current_fs to the right(50%) - from 0%
                left = ((1-now) * 50)+"%";
                //3. increase opacity of previous_fs to 1 as it moves in
                opacity = 1 - now;
                current_fs.css({'left': left});
                previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
            },
            duration: 800,
            complete: function(){
                current_fs.hide();
                animating = false;
            },
            //this comes from the custom easing plugin
            easing: 'easeInOutBack'
        });
    });
    $(".submit").click(function(){
        return false;
    })

    // Querying SSN users input to mask all numbers except the last 4 numbers & only allowing a max of 9 numbers total
    $('.ssn-value').on('keydown keyup mousedown mouseup', function() {
        let res = this.value, //grabs the value
            len = res.length, //grabs the length
            max = 9, //sets a max chars
            stars = len>0?len>1?len>2?len>3?len>4?'XXX-XX-':'XXX-X':'XXX-':'XX':'X':'', //this provides the masking and formatting
            result = stars+res.substring(5); //this is the result
        $(this).attr('maxlength', max); //setting the max length
        $(".ssn-number").val(result); //spits the value into the input
    });

    // // This Changes the Navbar from Transparent background to a solid color background when scrolling down
    // $(document).ready(function() {
    //     $(window).scroll(function() {
    //         let height = $('.first-container').height();
    //         let scrollTop = $(window).scrollTop();
    //
    //         if (scrollTop >= height - 40) {
    //             $('.navbar').addClass('solid-nav');
    //         } else {
    //             $('.navbar').removeClass('solid-nav');
    //         }
    //     });
    // });


    $( document ).ready(function() {
        var base_color = "rgb(230,230,230)";
        var active_color = "rgb(237, 40, 70)";

        var child = 1;
        var length = $("section").length - 1;
        $("#prev").addClass("disabled");
        $("#submit").addClass("disabled");

        $("section").not("section:nth-of-type(1)").hide();
        $("section").not("section:nth-of-type(1)").css('transform','translateX(100px)');

        var svgWidth = length * 200 + 24;
        $("#svg_wrap").html(
            '<svg version="1.1" id="svg_form_time" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 ' +
            svgWidth +
            ' 24" xml:space="preserve"></svg>'
        );

        function makeSVG(tag, attrs) {
            var el = document.createElementNS("http://www.w3.org/2000/svg", tag);
            for (var k in attrs) el.setAttribute(k, attrs[k]);
            return el;
        }

        for (let i = 0; i < length; i++) {
            var positionX = 12 + i * 200;
            var rect = makeSVG("rect", { x: positionX, y: 9, width: 200, height: 6 });
            document.getElementById("svg_form_time").appendChild(rect);
            // <g><rect x="12" y="9" width="200" height="6"></rect></g>'
            var circle = makeSVG("circle", {
                cx: positionX,
                cy: 12,
                r: 12,
                width: positionX,
                height: 6
            });
            document.getElementById("svg_form_time").appendChild(circle);
        }

        var circle = makeSVG("circle", {
            cx: positionX + 200,
            cy: 12,
            r: 12,
            width: positionX,
            height: 6
        });
        document.getElementById("svg_form_time").appendChild(circle);

        $('#svg_form_time rect').css('fill',base_color);
        $('#svg_form_time circle').css('fill',base_color);
        $("circle:nth-of-type(1)").css("fill", active_color);


        $(".button").click(function () {
            $("#svg_form_time rect").css("fill", active_color);
            $("#svg_form_time circle").css("fill", active_color);
            var id = $(this).attr("id");
            if (id == "next") {
                $("#prev").removeClass("disabled");
                if (child >= length) {
                    $(this).addClass("disabled");
                    $('#submit').removeClass("disabled");
                }
                if (child <= length) {
                    child++;
                }
            } else if (id == "prev") {
                $("#next").removeClass("disabled");
                $('#submit').addClass("disabled");
                if (child <= 2) {
                    $(this).addClass("disabled");
                }
                if (child > 1) {
                    child--;
                }
            }
            var circle_child = child + 1;
            $("#svg_form_time rect:nth-of-type(n + " + child + ")").css(
                "fill",
                base_color
            );
            $("#svg_form_time circle:nth-of-type(n + " + circle_child + ")").css(
                "fill",
                base_color
            );
            var currentSection = $("section:nth-of-type(" + child + ")");
            currentSection.fadeIn();
            currentSection.css('transform','translateX(0)');
            currentSection.prevAll('section').css('transform','translateX(-100px)');
            currentSection.nextAll('section').css('transform','translateX(100px)');
            $('section').not(currentSection).hide();
        });

    });
})();
