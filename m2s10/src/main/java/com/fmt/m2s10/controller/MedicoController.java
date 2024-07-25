package com.fmt.m2s10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicoResponseDto> obterPorId(@PathVariable Long id) {
		return ResponseEntity.ok(medicoService.obterPorId(id));
	}
	
	@PostMapping
	public ResponseEntity<MedicoResponseDto> cadastrar(@RequestBody MedicoRequestDto dto) {
		MedicoResponseDto medico = medicoService.cadastrar(dto);
		return new ResponseEntity<>(medico, HttpStatus.CREATED); 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MedicoResponseDto> atualizar(@PathVariable Long id, @RequestBody MedicoRequestDto dto) {
		MedicoResponseDto medico = medicoService.atualizar(dto, id);
		return new ResponseEntity<>(medico, HttpStatus.OK); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		medicoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
