package org.serratec.candidato.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.candidato.dto.CandidatoDto;
import org.serratec.candidato.model.Escolaridade;
import org.serratec.candidato.model.VagaDesejada;
import org.serratec.candidato.service.CandidatoService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {
	
	@Autowired
	private CandidatoService candidato;
	
	@GetMapping
	public List<CandidatoDto> obterTds(){
		return candidato.obterTodos();
	}
	@GetMapping("/{id}")
	public ResponseEntity<CandidatoDto> mostrarPorId(@PathVariable Long id){
		Optional<CandidatoDto> dto = candidato.obterPorId(id);
		if(!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get()); 		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CandidatoDto cadastrarCandidado(@RequestBody @Valid CandidatoDto dto) {
		return candidato.salvarCandidato(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaCandidato(@PathVariable Long id){
		if(!candidato.apagarCandidato(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CandidatoDto> alterarCandidato(@PathVariable Long id, @RequestBody @Valid CandidatoDto dto){
		Optional<CandidatoDto> candidatoAtualizado = candidato.alterarCanditado(id, dto);
		if(!candidatoAtualizado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(candidatoAtualizado.get());
	}
	
	@GetMapping("/vaga/{vaga}")
	public List<CandidatoDto> obterporVagas(@PathVariable VagaDesejada vaga){
		return candidato.obterPorVaga(vaga);
	}
	
	@GetMapping("/escolaridade/{escola}")
	public List<CandidatoDto> obterporEscolaridade(@PathVariable Escolaridade escola){
		return candidato.obterPorEscolaridade(escola);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
