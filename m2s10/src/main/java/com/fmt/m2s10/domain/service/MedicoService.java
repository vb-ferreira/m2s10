package com.fmt.m2s10.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmt.m2s10.domain.exception.ResourceBadRequestException;
import com.fmt.m2s10.domain.model.Medico;
import com.fmt.m2s10.domain.repository.MedicoRepository;
import com.fmt.m2s10.dto.MedicoRequestDto;
import com.fmt.m2s10.dto.MedicoResponseDto;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MedicoResponseDto cadastrar(MedicoRequestDto dto) {

		// valida presença de nome e crm e unicidade do crm
		validarMedico(dto);
		
		Medico medico = mapper.map(dto, Medico.class);
				
		medico.setId(null);
		
		medico = medicoRepository.save(medico);
		
		return mapper.map(medico, MedicoResponseDto.class);
	
	}

	@Override
	public MedicoResponseDto atualizar(MedicoRequestDto dto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Long id) {
		// TODO Auto-generated method stub
		
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
	
}
