package br.com.ribeiro.service.mapper;

import br.com.ribeiro.domain.*;
import br.com.ribeiro.service.dto.PessoaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pessoa} and its DTO {@link PessoaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PessoaMapper extends EntityMapper<PessoaDTO, Pessoa> {


    @Mapping(target = "enderecos", ignore = true)
    @Mapping(target = "contatoes", ignore = true)
    @Mapping(target = "removeContato", ignore = true)
    Pessoa toEntity(PessoaDTO pessoaDTO);

    default Pessoa fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        return pessoa;
    }
}
