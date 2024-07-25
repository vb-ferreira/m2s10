package com.fmt.m2s10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmt.m2s10.domain.service.MedicoService;
import com.fmt.m2s10.dto.MedicoRequestDto;
import com.fmt.m2s10.dto.MedicoResponseDto;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;
	
	@GetMapping
	public ResponseEntity<List<MedicoResponseDto>> obterTodos() {
		// Temporário
		return ResponseEntity.ok(medicoService.obterTodos());
	}
	
	@PostMapping
	public ResponseEntity<MedicoResponseDto> cadastrar(@RequestBody MedicoRequestDto dto) {
		MedicoResponseDto medico = medicoService.cadastrar(dto);
		return new ResponseEntity<>(medico, HttpStatus.CREATED); 
	}
	
}
