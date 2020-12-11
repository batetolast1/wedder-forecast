package io.github.batetolast1.wedderforecast.model.entity.user;

import io.github.batetolast1.wedderforecast.model.entity.user.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")

@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
