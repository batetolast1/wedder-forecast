// Initialize Google Map.
function initMap() {

    const map = new google.maps.Map(document.getElementById("map"), {
        center: {lat: 52.4095, lng: 16.9319},
        zoom: 13,
    });

    // Try HTML5 geolocation.
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            // Center Map on user's position.
            (position) => {
                const pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude,
                };
                map.setCenter(pos);
            },
            // User rejected permission to use geolocation.
            () => {
                window.alert("Error: The Geolocation service failed.");
            }
        );
    } else {
        // Browser doesn't support Geolocation
        window.alert("Error: Your browser doesn't support geolocation.");
    }


    // Place Places Autocomplete card on Map.
    const card = document.getElementById("pac-card");
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(card);

    // Create and setup Places Autocomplete input.
    const input = document.getElementById("pac-input");
    const autocomplete = new google.maps.places.Autocomplete(input);
    autocomplete.bindTo("bounds", map);
    autocomplete.setFields(["place_id", "address_components", "geometry", "icon", "name", "formatted_address"]);
    autocomplete.setTypes(["address"])

    // Set a listener on a radio button to change the filter type on Places Autocomplete.
    function setupClickListener(id, types) {
        const radioButton = document.getElementById(id);
        radioButton.addEventListener("click", () => {
            autocomplete.setTypes(types);
        });
    }

    setupClickListener("changetype-address", ["address"]);
    setupClickListener("changetype-establishment", ["establishment"]);

    // Create InfoWindow
    const infowindow = new google.maps.InfoWindow();
    const infowindowContent = document.getElementById("infowindow-content");
    infowindow.setContent(infowindowContent);

    // Create Marker.
    const marker = new google.maps.Marker({
        map,
        anchorPoint: new google.maps.Point(0, -29),
    });

    // Add event to Autocomplete input.
    autocomplete.addListener("place_changed", () => {
        const place = autocomplete.getPlace();
        displayInfowindow(place);
    });

    // Create event to disable Autocomplete when form is submitted.
    const form = infowindowContent.children["form"];
    form.addEventListener("submit", () => {
        // Disable Autocomplete and input.
        autocomplete.unbindAll();
        google.maps.event.clearInstanceListeners(input);

        // Disable submit button.
        const submitBtn = form.children["autocomplete-submit-button"];
        submitBtn.value = "Fetching result... please wait!";
        submitBtn.disabled = true;
    });

    function displayInfowindow(place) {
        // Close previous Infowindow.
        infowindow.close();
        marker.setVisible(false);

        // Get postal code and country code.
        let postalCode = "";
        let countryCode = "";

        if (place.address_components) {
            place.address_components.forEach(ac => {
                    if (ac.types.includes("country")) {
                        countryCode = ac.short_name;
                    }
                    if (ac.types.includes("postal_code")) {
                        postalCode = ac.long_name;
                    }
                }
            )
        }

        if (!postalCode || !countryCode) {
            // Place doesn't have necessary information to predict weather.
            window.alert("No postal code or country code available for input: '" + place.name + "'");
            return;
        }

        // If the place has a geometry, then present it on a map.
        if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17);
        }

        // Place marker on a map.
        marker.setPlace({
            placeId: place.place_id,
            location: place.geometry.location,
        });
        marker.setVisible(true);

        // Fill Infowindow with content.
        infowindowContent.children["place-icon"].src = place.icon;
        infowindowContent.children["place-name"].textContent = place.name;
        infowindowContent.children["place-address"].textContent = place.formatted_address;

        // Fill Infowindow form with data.
        form.children["postalCode"].value = postalCode;
        form.children["countryCode"].value = countryCode;
        form.children["placeId"].value = place.place_id;
        form.children["formattedAddress"].value = place.formatted_address;

        // Open Infowindow.
        infowindow.open(map, marker);
    }

    // Configure Places Service to add opening Infowindow when user clicks on POI.
    const service = new google.maps.places.PlacesService(map);
    const request = {
        placeId: '',
        fields: ['place_id', 'address_components', 'geometry', 'icon', 'name', 'formatted_address']
    };

    // Configure the POI click listener.
    map.addListener("click", event => {
        // Prevent default POI click event.
        event.stop();

        if (isIconMouseEvent(event)) {
            request.placeId = event.placeId;
            service.getDetails(request, callback);
        }
    });

    function isIconMouseEvent(e) {
        return "placeId" in e;
    }

    function callback(place, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
            displayInfowindow(place);
        }
    }
}

// Set localDate / localDateTime input min, max and value in form.
document.addEventListener("DOMContentLoaded", () => {
    const now = new Date();
    now.setHours(11, 0, 0, 0);

    const localDateInput = document.getElementById('local-date-input');
    if (localDateInput) {
        localDateInput.value = getLocalDate(addDays(now, 180));
        localDateInput.min = getLocalDate(addDays(now, 1));
        localDateInput.max = getLocalDate(addYears(now, 1));

    }

    const localDateTimeInput = document.getElementById('local-date-time-input');
    if (localDateTimeInput) {
        localDateInput.value = getLocalDateTime(addDays(now, 180));
        localDateInput.min = getLocalDateTime(addDays(now, 1));
        localDateInput.max = getLocalDateTime(addYears(now, 1));
    }
});

function getLocalDate(date) {
    return date.toISOString().split("T")[0];
}

function getLocalDateTime(date) {
    return date.toISOString().split(".")[0];
}

function addDays(date, days) {
    const copy = new Date(Number(date))
    copy.setDate(date.getDate() + days)
    return copy
}

function addYears(date, years) {
    const copy = new Date(Number(date))
    copy.setFullYear(date.getFullYear() + years)
    return copy
}
