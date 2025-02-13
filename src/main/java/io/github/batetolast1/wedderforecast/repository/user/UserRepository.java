package io.github.batetolast1.wedderforecast.repository.user;

import io.github.batetolast1.wedderforecast.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
