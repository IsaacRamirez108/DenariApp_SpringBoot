"use strict";
(function(){
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


    // Mapbox Address Confirmation Modal
    document.getElementById("search-js").onload = () => {
        mapboxsearch.config.accessToken = MapBox_API_KEY;

        // Adding confirmation prompt to Users address
        const form = document.querySelector('mapbox-address-autofill');
        form.addEventListener("submit", async (e) => {
            e.preventDefault();
            const result = await mapboxsearch.confirmAddress(form, {
                theme: { variables: {border: '3px solid rgba(0,0,0,0.35)', borderRadius: '18px'} },
                minimap: {
                    defaultMapStyle: ['mapbox', 'outdoors-v11'],
                    satelliteToggle: true
                },
                // skipConfirmModal: (feature) => false // overrides default behavior, show dialog every time
            });
            console.log(result);
        });
    }


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

})();
