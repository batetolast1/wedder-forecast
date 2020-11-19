package io.github.batetolast1.wedderforecast.model.repository.user;

import io.github.batetolast1.wedderforecast.model.entity.user.Role;
import io.github.batetolast1.wedderforecast.model.entity.user.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getByUserRole(UserRole userRole);
}
