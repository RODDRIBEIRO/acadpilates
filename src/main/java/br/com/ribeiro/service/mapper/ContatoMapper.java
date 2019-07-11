package br.com.ribeiro.service.mapper;

import br.com.ribeiro.domain.*;
import br.com.ribeiro.service.dto.ContatoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contato} and its DTO {@link ContatoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContatoMapper extends EntityMapper<ContatoDTO, Contato> {

	ContatoDTO toDto(Contato contato);

	Contato toEntity(ContatoDTO contatoDTO);

	default Contato fromId(Long id) {
		if (id == null) {
			return null;
		}
		Contato contato = new Contato();
		contato.setId(id);
		return contato;
	}
}
