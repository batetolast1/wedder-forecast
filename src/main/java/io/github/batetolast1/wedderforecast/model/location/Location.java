package io.github.batetolast1.wedderforecast.model.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "locations")

@NoArgsConstructor
@Getter
@Setter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * Unspecified postal code; any spaces in the postal code must be URL encoded as %20
     */
    @Column(name = "postal_code")
    private String postalCode;

    /**
     * A 2-character ISO 3166-1 Alpha-2 country code
     */
    @Column(name = "country_code")
    private String countryCode;

    /**
     * Place IDs uniquely identify a place in the Google Places database and on Google Maps.
     * https://developers.google.com/places/web-service/place-id
     */
    @Column(name = "place_id")
    private String placeId;

    /**
     * Formatted_address is a string containing the human-readable address of this place.
     * https://developers.google.com/places/web-service/details
     */
    @Column(name = "formatted_address")
    private String formattedAddress;
}
