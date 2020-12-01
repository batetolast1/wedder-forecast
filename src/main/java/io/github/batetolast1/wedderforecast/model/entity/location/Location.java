package io.github.batetolast1.wedderforecast.model.entity.location;

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
     * Unspecified postal code;  any spaces in the postal code must be URL encoded as %20
     */
    @Column(name = "postal_code")
    private String postalCode;

    /**
     * A 2-character ISO 3166-1 Alpha-2 country code
     */
    @Column(name = "country_code")
    private String countryCode;
}
