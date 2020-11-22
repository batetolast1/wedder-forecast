package io.github.batetolast1.wedderforecast.model.entity.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Locale;

@Entity
@Table(name = "locations")

@NoArgsConstructor
@Getter
@Setter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A 2-character ISO 3166-1 Alpha-2 country code
    @Enumerated(EnumType.STRING)
    private Locale.IsoCountryCode countryCode;

    // Unspecified postal code;  Any spaces in the postal code must be URL encoded as %20
    private String postalCode;
}
