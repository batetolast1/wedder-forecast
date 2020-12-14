package io.github.batetolast1.wedderforecast.model.rating;

import io.github.batetolast1.wedderforecast.model.rating.enums.UserRatingValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "user_ratings")

@NoArgsConstructor
@Getter
@Setter
public class UserRating extends Rating {

    @Enumerated(EnumType.STRING)
    private UserRatingValue overall;
}
