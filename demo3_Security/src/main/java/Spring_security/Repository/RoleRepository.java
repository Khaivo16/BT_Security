package Spring_security.Repository;

import java.util.Optional;

import javax.management.relation.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.Entity;

@Entity
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Query("SELECT u FROM Role u WHERE u.name = :name") 
	
	public Role getUserByName(@Param("name") String name); 
	
	Optional<Role> findByName(String name);

}
