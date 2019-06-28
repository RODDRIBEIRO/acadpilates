package br.com.ribeiro.service.mapper;

import br.com.ribeiro.domain.*;
import br.com.ribeiro.service.dto.ContatoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contato} and its DTO {@link ContatoDTO}.
 */
@Mapper(componentModel = "spring", uses = {PessoaMapper.class})
public interface ContatoMapper extends EntityMapper<ContatoDTO, Contato> {

    @Mapping(source = "pessoa.id", target = "pessoaId")
    ContatoDTO toDto(Contato contato);

    @Mapping(source = "pessoaId", target = "pessoa")
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
