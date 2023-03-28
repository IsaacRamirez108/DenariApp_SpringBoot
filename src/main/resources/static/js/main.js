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


        document.getElementById("search-js").onload = () => {
            mapboxsearch.config.accessToken = MapBox_API_KEY;

            // Adding confirmation prompt to Users address
            const form = document.querySelector('form');
            form.addEventListener("submit", async (e) => {
                e.preventDefault();
                const result = await mapboxsearch.confirmAddress(form, {
                    theme: { variables: {border: '3px solid rgba(0,0,0,0.35)', borderRadius: '18px'} },
                    minimap: {
                        defaultMapStyle: ['mapbox', 'outdoors-v11'],
                        satelliteToggle: true
                    },
                    skipConfirmModal: (feature) => false // overrides default behavior, show dialog every time
                });
                console.log(result);
            });
        }

})();
