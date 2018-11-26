package br.com.tutorial.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tutorial.entities.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long>  
{
	List<Institution> findByNameContaining(String name);
}
