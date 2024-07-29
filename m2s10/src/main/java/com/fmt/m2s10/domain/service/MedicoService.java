package com.fmt.m2s10.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fmt.m2s10.domain.enums.EspecialidadeEnum;
import com.fmt.m2s10.domain.exception.ResourceBadRequestException;
import com.fmt.m2s10.domain.exception.ResourceNotFoundException;
import com.fmt.m2s10.domain.model.Medico;
import com.fmt.m2s10.domain.repository.MedicoRepository;
import com.fmt.m2s10.dto.MedicoRequestDto;
import com.fmt.m2s10.dto.MedicoResponseDto;
import com.fmt.m2s10.dto.MedicoResumeDto;

@Service
public class MedicoService implements ICRUDService<MedicoRequestDto, MedicoResponseDto> {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<MedicoResponseDto> obterTodos() {
	
		// Temporário
		List<Medico> medicos = medicoRepository.findAll();
				
		return medicos.stream()
				.map(medico -> mapper.map(medico, MedicoResponseDto.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public MedicoResponseDto obterPorId(Long id) {
		
		Optional<Medico> optMedico = medicoRepository.findById(id);
		
		if (optMedico.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possível encontrar o médico com o id: " + id);
		} 
		
		return mapper.map(optMedico.get(), MedicoResponseDto.class);

	}

	@Override
	public MedicoResponseDto cadastrar(MedicoRequestDto dto) {

		// valida presença de nome e crm e unicidade do crm no request
		validarMedico(dto);
		
		Medico medico = mapper.map(dto, Medico.class);
				
		medico.setId(null);
		
		medico = medicoRepository.save(medico);
		
		return mapper.map(medico, MedicoResponseDto.class);
	
	}

	@Override
	public MedicoResponseDto atualizar(MedicoRequestDto dto, Long id) {
				
		validarMedico(dto);
		
		Medico medico = mapper.map(dto, Medico.class);
				
		medico.setId(id);
				
		medico = medicoRepository.save(medico);
		
		return mapper.map(medico, MedicoResponseDto.class);
	
	}

	@Override
	public void deletar(Long id) {
		
		obterPorId(id);
	
		medicoRepository.deleteById(id);
	
	}

	private void validarMedico(MedicoRequestDto dto) {
		
		if (dto.getNome() == null || dto.getCrm() == null) {
			throw new ResourceBadRequestException("Nome e CRM são obrigatórios");
		}
		
		Optional<Medico> optMedico = medicoRepository.findByCrm(dto.getCrm());
		
		if (optMedico.isPresent()) {
			throw new ResourceBadRequestException("Já existe um médico cadastrado com o crm: " + dto.getCrm());
		}
		
	}
	
	public Page<MedicoResumeDto> obterResumoTodos(String nome, EspecialidadeEnum especialidade, LocalDate dataNascimento, Integer pageSize, Integer offset) {
		
		List<Medico> medicos = medicoRepository.findAll(nome, especialidade, dataNascimento, PageRequest.of(pageSize, offset));
		
		List<MedicoResumeDto> medicosDto = medicos.stream()
				.map(medico -> mapper.map(medico, MedicoResumeDto.class))
				.collect(Collectors.toList());
		
		int total =  medicosDto.size();
		
		Page<MedicoResumeDto> pages = new PageImpl<MedicoResumeDto>(medicosDto, PageRequest.of(pageSize, offset), total);
		
		return pages;
		
	}

}
