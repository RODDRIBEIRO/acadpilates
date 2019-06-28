package br.com.ribeiro.service.mapper;

import br.com.ribeiro.domain.*;
import br.com.ribeiro.service.dto.EstadoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Estado} and its DTO {@link EstadoDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaisMapper.class})
public interface EstadoMapper extends EntityMapper<EstadoDTO, Estado> {

    @Mapping(source = "pais.id", target = "paisId")
    EstadoDTO toDto(Estado estado);

    @Mapping(source = "paisId", target = "pais")
    @Mapping(target = "enderecos", ignore = true)
    @Mapping(target = "removeEndereco", ignore = true)
    Estado toEntity(EstadoDTO estadoDTO);

    default Estado fromId(Long id) {
        if (id == null) {
            return null;
        }
        Estado estado = new Estado();
        estado.setId(id);
        return estado;
    }
}
