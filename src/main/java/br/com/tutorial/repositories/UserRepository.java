package br.com.tutorial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tutorial.entities.User;

public interface UserRepository extends JpaRepository<User, Long>  
{
	List<User> findByNameContaining(String name);
}
