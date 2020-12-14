package io.github.batetolast1.wedderforecast.repository.user;

import io.github.batetolast1.wedderforecast.model.user.Role;
import io.github.batetolast1.wedderforecast.model.user.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getByUserRole(UserRole userRole);
}
