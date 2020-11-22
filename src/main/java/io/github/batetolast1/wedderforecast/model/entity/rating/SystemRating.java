package io.github.batetolast1.wedderforecast.model.entity.rating;

import io.github.batetolast1.wedderforecast.model.entity.rating.enums.SystemRatingValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "system_ratings")

@NoArgsConstructor
@Getter
@Setter
public class SystemRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "overall")
    private SystemRatingValue overall;

    @Enumerated(EnumType.STRING)
    @Column(name = "temp")
    private SystemRatingValue temp;

    @Enumerated(EnumType.STRING)
    @Column(name = "pres")
    private SystemRatingValue pres;

    @Enumerated(EnumType.STRING)
    @Column(name = "precip")
    private SystemRatingValue precip;

    @Enumerated(EnumType.STRING)
    @Column(name = "cld_wind")
    private SystemRatingValue cldWind;

    @Enumerated(EnumType.STRING)
    @Column(name = "hum")
    private SystemRatingValue hum;
}
