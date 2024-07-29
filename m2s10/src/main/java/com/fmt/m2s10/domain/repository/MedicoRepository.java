package com.fmt.m2s10.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.fmt.m2s10.domain.enums.EspecialidadeEnum;
import com.fmt.m2s10.domain.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>, PagingAndSortingRepository<Medico, Long> {

	Optional<Medico> findByCrm(String crm);
	
	@Query("FROM Medico MED WHERE (:nome is null OR MED.nome = :nome) "
			+ "AND (:especialidade is null OR MED.especialidade = :especialidade) "
			+ "AND (CAST(:dataNascimento AS timestamp) is null OR MED.dataNascimento = :dataNascimento)")
	List<Medico> findAll(@Param("nome") String nome, 
			@Param("especialidade") EspecialidadeEnum especialidade,
			@Param("dataNascimento") LocalDate dataNascimento,
			PageRequest paginacao);
	
}
