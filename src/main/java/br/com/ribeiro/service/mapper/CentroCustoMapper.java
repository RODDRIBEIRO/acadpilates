package br.com.ribeiro.service.mapper;

import org.mapstruct.Mapper;

import br.com.ribeiro.domain.CentroCusto;
import br.com.ribeiro.service.dto.CentroCustoDTO;

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
