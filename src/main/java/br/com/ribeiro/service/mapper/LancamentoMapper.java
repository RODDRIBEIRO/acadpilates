package br.com.ribeiro.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.ribeiro.domain.Lancamento;
import br.com.ribeiro.service.dto.LancamentoDTO;

/**
 * Mapper for the entity {@link Lancamento} and its DTO {@link LancamentoDTO}.
 */
@Mapper(componentModel = "spring", uses = { PessoaMapper.class, ContaMapper.class, DocumentoMapper.class,
		CentroCustoMapper.class })
public interface LancamentoMapper extends EntityMapper<LancamentoDTO, Lancamento> {

	@Mapping(source = "pessoa.id", target = "pessoaId")
	@Mapping(source = "pessoa.nome", target = "pessoaNome")
	@Mapping(source = "conta.id", target = "contaId")
	@Mapping(source = "conta.descricao", target = "contaDescricao")
	@Mapping(source = "documento.id", target = "documentoId")
	@Mapping(source = "documento.descricao", target = "documentoDescricao")
	@Mapping(source = "centroCusto.id", target = "centroCustoId")
	@Mapping(source = "centroCusto.descricao", target = "centroCustoDescricao")
	LancamentoDTO toDto(Lancamento lancamento);

	@Mapping(source = "pessoaId", target = "pessoa")
	@Mapping(source = "contaId", target = "conta")
	@Mapping(source = "documentoId", target = "documento")
	@Mapping(source = "centroCustoId", target = "centroCusto")
	Lancamento toEntity(LancamentoDTO lancamentoDTO);

	default Lancamento fromId(Long id) {
		if (id == null) {
			return null;
		}
		Lancamento lancamento = new Lancamento();
		lancamento.setId(id);
		return lancamento;
	}
}
