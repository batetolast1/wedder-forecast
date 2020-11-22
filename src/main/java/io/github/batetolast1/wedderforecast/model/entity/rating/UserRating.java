package io.github.batetolast1.wedderforecast.model.entity.rating;

import io.github.batetolast1.wedderforecast.model.entity.rating.enums.UserRatingValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_ratings")

@NoArgsConstructor
@Getter
@Setter
public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "overall")
    private UserRatingValue overall;

    @Enumerated(EnumType.STRING)
    @Column(name = "temp")
    private UserRatingValue temp;

    @Enumerated(EnumType.STRING)
    @Column(name = "precip")
    private UserRatingValue precip;

    @Enumerated(EnumType.STRING)
    @Column(name = "cld_wind")
    private UserRatingValue cldWind;
}
