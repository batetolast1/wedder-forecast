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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    /**
     * A term to be matched against all content that Google has indexed for this place.
     * https://developers.google.com/places/web-service/search
     */
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "postal_coordinate_id")
    private PostalCoordinate postalCoordinate;
}
