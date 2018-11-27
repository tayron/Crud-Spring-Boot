package br.com.tutorial.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tutorial.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long>  
{
	List<Student> findByNameContaining(String name);
}
