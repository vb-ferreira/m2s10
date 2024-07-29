package com.fmt.m2s10.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fmt.m2s10.domain.enums.EspecialidadeEnum;

public class MedicoResumeDto {
	
    private String nome;
    
    @JsonSerialize
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private EspecialidadeEnum especialidade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EspecialidadeEnum getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(EspecialidadeEnum especialidade) {
		this.especialidade = especialidade;
	}
    
}
