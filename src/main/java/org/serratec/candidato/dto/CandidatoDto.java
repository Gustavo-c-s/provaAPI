package org.serratec.candidato.dto;

import java.time.LocalDate;

import org.serratec.candidato.model.Candidato;
import org.serratec.candidato.model.Escolaridade;
import org.serratec.candidato.model.StatusCurriculo;
import org.serratec.candidato.model.VagaDesejada;

public record CandidatoDto(Long id,
		String nome, 
		LocalDate dataNascimento, 
		String cpf, 
		Escolaridade escolaridade,
		VagaDesejada vagaDesejada, 
		StatusCurriculo statusCurriculo) {
	
	public Candidato toEntity() {
		Candidato candidato= new Candidato();
		candidato.setId(this.id);
		candidato.setNome(this.nome);
		candidato.setDataNascimento(this.dataNascimento());
		candidato.setCpf(this.cpf);
		candidato.setEscolaridade(this.escolaridade);
		candidato.setVagaDesejada(this.vagaDesejada);
		candidato.setStatusCurriculo(this.statusCurriculo());
		return candidato;
	}
	
	public static CandidatoDto toDto(Candidato canditado) {
		return new CandidatoDto(canditado.getId(),canditado.getNome(),canditado.getDataNascimento(),
				canditado.getCpf(),canditado.getEscolaridade(),canditado.getVagaDesejada(),
				canditado.getStatusCurriculo());
	}

}

