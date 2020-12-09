// https://developers.google.com/maps/documentation/javascript/examples/places-autocomplete
// This example requires the Places library. Include the libraries=places
// parameter when you first load the API. For example:
// <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">
function initMap() {

    const map = new google.maps.Map(document.getElementById("map"), {
        center: {lat: 52.4095, lng: 16.9319},
        zoom: 13,
        // TODO add clickable icons https://developers.google.com/maps/documentation/javascript/examples/event-poi
        clickableIcons: false
    });

    // Try HTML5 geolocation.
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude,
                };
                map.setCenter(pos);
            },
            () => {
                window.alert("Error: The Geolocation service failed.");
            }
        );
    } else {
        // Browser doesn't support Geolocation
        window.alert("Error: Your browser doesn't support geolocation.");
    }

    const card = document.getElementById("pac-card");
    const input = document.getElementById("pac-input");

    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(card);

    const autocomplete = new google.maps.places.Autocomplete(input);
    // Bind the map's bounds (viewport) property to the autocomplete object,
    // so that the autocomplete requests use the current map bounds for the
    // bounds option in the request.
    autocomplete.bindTo("bounds", map);

    // Set the data fields to return when the user selects a place.
    autocomplete.setFields(["place_id", "address_components", "geometry", "icon", "name", "formatted_address"]);
    // Set the default filter type on Places Autocomplete.
    autocomplete.setTypes(["address"])

    const infowindow = new google.maps.InfoWindow();
    const infowindowContent = document.getElementById("infowindow-content");
    infowindow.setContent(infowindowContent);
    const marker = new google.maps.Marker({
        map,
        anchorPoint: new google.maps.Point(0, -29),
    });

    autocomplete.addListener("place_changed", () => {
        infowindow.close();
        marker.setVisible(false);
        const place = autocomplete.getPlace();

        if (!place.geometry) {
            // User entered the name of a Place that was not suggested and
            // pressed the Enter key, or the Place Details request failed.
            window.alert("No details available for input: '" + place.name + "'");
            return;
        }

        // If the place has a geometry, then present it on a map.
        if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17); // Why 17? Because it looks good.
        }
        marker.setPlace({
            placeId: place.place_id,
            location: place.geometry.location,
        });
        marker.setVisible(true);

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

        infowindowContent.children["place-icon"].src = place.icon;
        infowindowContent.children["place-name"].textContent = place.name;
        infowindowContent.children["place-address"].textContent = place.formatted_address;
        infowindowContent.children["form"].children["postalCode"].value = postalCode;
        infowindowContent.children["form"].children["countryCode"].value = countryCode;
        infowindowContent.children["form"].children["placeId"].value = place.place_id;
        infowindowContent.children["form"].children["formattedAddress"].value = place.formatted_address;
        infowindow.open(map, marker);
    });

    // Sets a listener on a radio button to change the filter type on Places Autocomplete.
    function setupClickListener(id, types) {
        const radioButton = document.getElementById(id);
        radioButton.addEventListener("click", () => {
            autocomplete.setTypes(types);
        });
    }

    setupClickListener("changetype-address", ["address"]);
    setupClickListener("changetype-establishment", ["establishment"]);
}

document.addEventListener("DOMContentLoaded", () => {
    const now = new Date();
    document.getElementById('localDate').value = addDays(now, 1).toISOString().split("T")[0];
    console.log(addDays(now, 1).toISOString().split("T")[0]);
    document.getElementById("localDate").min = addDays(now, 1).toISOString().split("T")[0];
    console.log(addYears(now, 1).toISOString().split("T")[0]);
    document.getElementById("localDate").max = addYears(now, 1).toISOString().split("T")[0];
});

function disableSendButton(formElement) {
    let submitBtn = formElement.lastElementChild;
    submitBtn.value = "Fetching result... please wait!";
    submitBtn.disabled = true;
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
