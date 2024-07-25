package com.fmt.m2s10.domain.model;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

import com.fmt.m2s10.domain.enums.EspecialidadeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String crm;
		
	private LocalDate dataNascimento;
	
	private String telefone;
	
	@Enumerated(value = EnumType.STRING)
	private EspecialidadeEnum especialidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public EspecialidadeEnum getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(EspecialidadeEnum especialidade) {
		this.especialidade = especialidade;
	}	
	
}
