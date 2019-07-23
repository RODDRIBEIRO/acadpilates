package br.com.ribeiro.service.mapper;

import br.com.ribeiro.domain.*;
import br.com.ribeiro.service.dto.CentroCustoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CentroCusto} and its DTO {@link CentroCustoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CentroCustoMapper extends EntityMapper<CentroCustoDTO, CentroCusto> {



    default CentroCusto fromId(Long id) {
        if (id == null) {
            return null;
        }
        CentroCusto centroCusto = new CentroCusto();
        centroCusto.setId(id);
        return centroCusto;
    }
}
