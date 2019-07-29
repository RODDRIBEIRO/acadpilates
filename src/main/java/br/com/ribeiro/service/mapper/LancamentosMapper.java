package br.com.ribeiro.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.ribeiro.domain.Lancamentos;
import br.com.ribeiro.service.dto.LancamentosDTO;

/**
 * Mapper for the entity {@link Lancamentos} and its DTO {@link LancamentosDTO}.
 */
@Mapper(componentModel = "spring", uses = { PessoaMapper.class, ContaMapper.class, DocumentoMapper.class,
		CentroCustoMapper.class })
public interface LancamentosMapper extends EntityMapper<LancamentosDTO, Lancamentos> {

	@Mapping(source = "pessoa.id", target = "pessoaId")
	@Mapping(source = "pessoa.nome", target = "pessoaNome")
	@Mapping(source = "conta.id", target = "contaId")
	@Mapping(source = "conta.descricao", target = "contaDescricao")
	@Mapping(source = "documento.id", target = "documentoId")
	@Mapping(source = "documento.descricao", target = "documentoDescricao")
	@Mapping(source = "centroCusto.id", target = "centroCustoId")
	@Mapping(source = "centroCusto.descricao", target = "centroCustoDescricao")
	LancamentosDTO toDto(Lancamentos lancamentos);

	@Mapping(source = "pessoaId", target = "pessoa")
	@Mapping(source = "contaId", target = "conta")
	@Mapping(source = "documentoId", target = "documento")
	@Mapping(source = "centroCustoId", target = "centroCusto")
	Lancamentos toEntity(LancamentosDTO lancamentosDTO);

	default Lancamentos fromId(Long id) {
		if (id == null) {
			return null;
		}
		Lancamentos lancamentos = new Lancamentos();
		lancamentos.setId(id);
		return lancamentos;
	}
}
