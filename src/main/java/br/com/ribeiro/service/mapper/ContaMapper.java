package br.com.ribeiro.service.mapper;

import br.com.ribeiro.domain.*;
import br.com.ribeiro.service.dto.ContaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Conta} and its DTO {@link ContaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContaMapper extends EntityMapper<ContaDTO, Conta> {



    default Conta fromId(Long id) {
        if (id == null) {
            return null;
        }
        Conta conta = new Conta();
        conta.setId(id);
        return conta;
    }
}
