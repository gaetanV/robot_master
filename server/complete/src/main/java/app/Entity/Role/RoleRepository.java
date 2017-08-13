package app.Entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.Entity.Role.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
     Role findByName(String role);
}
