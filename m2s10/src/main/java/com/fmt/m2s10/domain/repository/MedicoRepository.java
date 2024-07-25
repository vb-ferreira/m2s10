package com.fmt.m2s10.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fmt.m2s10.domain.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

	Optional<Medico> findByCrm(String crm);
	
}
