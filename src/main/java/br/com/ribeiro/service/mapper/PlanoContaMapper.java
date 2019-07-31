package br.com.ribeiro.service.mapper;

import br.com.ribeiro.domain.*;
import br.com.ribeiro.service.dto.PlanoContaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlanoConta} and its DTO {@link PlanoContaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlanoContaMapper extends EntityMapper<PlanoContaDTO, PlanoConta> {



    default PlanoConta fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlanoConta planoConta = new PlanoConta();
        planoConta.setId(id);
        return planoConta;
    }
}
