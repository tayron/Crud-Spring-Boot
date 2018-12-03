package br.com.tutorial.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tutorial.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>  
{
	List<Role> findByNameContaining(String name);
}
