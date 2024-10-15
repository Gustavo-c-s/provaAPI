package org.serratec.candidato.service;

import java.util.List;
import java.util.Optional;

import org.serratec.candidato.dto.CandidatoDto;
import org.serratec.candidato.model.Candidato;
import org.serratec.candidato.model.Escolaridade;
import org.serratec.candidato.model.VagaDesejada;
import org.serratec.candidato.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {
	@Autowired
	private CandidatoRepository repositorio;
	
	public List<CandidatoDto> obterTodos(){
		return repositorio.findAll().stream().map(c -> CandidatoDto.toDto(c)).toList();
	}
	public Optional<CandidatoDto> obterPorId(Long id){
		if(!repositorio.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(CandidatoDto.toDto(repositorio.findById(id).get()));
	}
	public CandidatoDto salvarCandidato(CandidatoDto dto) {
		Candidato canditadoEntity = repositorio.save(dto.toEntity());
		return CandidatoDto.toDto(canditadoEntity);
		
	}
	public boolean apagarCandidato(Long id) {
		if(!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
	}
	public Optional<CandidatoDto> alterarCanditado(Long id, CandidatoDto dto){
		if(!repositorio.existsById(id)) {
			return Optional.empty();
		}
		Candidato canditadoEntity = dto.toEntity();
		canditadoEntity.setId(id);
		repositorio.save(canditadoEntity);
		return Optional.of(CandidatoDto.toDto(canditadoEntity));
	}
	
	public List<CandidatoDto> obterPorVaga(VagaDesejada vaga ) {
		List<Candidato> candidatos = repositorio.findByVagaDesejada(vaga);
	    return candidatos.stream().map(c-> CandidatoDto.toDto(c)).toList();
	}
	public List<CandidatoDto> obterPorEscolaridade(Escolaridade escola ) {
		List<Candidato> candidato = repositorio.findByEscolaridade(escola);
		return candidato.stream().map(c -> CandidatoDto.toDto(c)).toList();
	}

}
