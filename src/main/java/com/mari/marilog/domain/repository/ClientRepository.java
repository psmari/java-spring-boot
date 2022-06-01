package com.mari.marilog.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mari.marilog.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	List<Client> findByName(String name);
	List<Client> findByNameContaining(String name);
	List<Client> findByEmail(String email);
}
