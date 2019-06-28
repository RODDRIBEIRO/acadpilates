package br.com.ribeiro.service.mapper;

import br.com.ribeiro.domain.*;
import br.com.ribeiro.service.dto.EnderecoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Endereco} and its DTO {@link EnderecoDTO}.
 */
@Mapper(componentModel = "spring", uses = {EstadoMapper.class, PessoaMapper.class})
public interface EnderecoMapper extends EntityMapper<EnderecoDTO, Endereco> {

    @Mapping(source = "estado.id", target = "estadoId")
    @Mapping(source = "pessoa.id", target = "pessoaId")
    EnderecoDTO toDto(Endereco endereco);

    @Mapping(source = "estadoId", target = "estado")
    @Mapping(source = "pessoaId", target = "pessoa")
    Endereco toEntity(EnderecoDTO enderecoDTO);

    default Endereco fromId(Long id) {
        if (id == null) {
            return null;
        }
        Endereco endereco = new Endereco();
        endereco.setId(id);
        return endereco;
    }
}
