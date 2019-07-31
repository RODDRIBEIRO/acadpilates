package br.com.ribeiro.service.dto;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link br.com.ribeiro.domain.Lancamentos} entity.
 */
public class LancamentosDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2493432484523023720L;

	private Long id;

	private Instant dataCompetencia;

	private Instant dataConciliacao;

	private Double valor;

	private Integer tipo;

	private String historico;

	private String numeroDocumento;

	private Long pessoaId;
	private String pessoaNome;

	private Long contaId;
	private String contaDescricao;

	private Long documentoId;
	private String documentoDescricao;

	private Long centroCustoId;
	private String centroCustoDescricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDataCompetencia() {
		return dataCompetencia;
	}

	public void setDataCompetencia(Instant dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
	}

	public Instant getDataConciliacao() {
		return dataConciliacao;
	}

	public void setDataConciliacao(Instant dataConciliacao) {
		this.dataConciliacao = dataConciliacao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getPessoaNome() {
		return pessoaNome;
	}

	public void setPessoaNome(String pessoaNome) {
		this.pessoaNome = pessoaNome;
	}

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}

	public String getContaDescricao() {
		return contaDescricao;
	}

	public void setContaDescricao(String contaDescricao) {
		this.contaDescricao = contaDescricao;
	}

	public Long getDocumentoId() {
		return documentoId;
	}

	public void setDocumentoId(Long documentoId) {
		this.documentoId = documentoId;
	}

	public String getDocumentoDescricao() {
		return documentoDescricao;
	}

	public void setDocumentoDescricao(String documentoDescricao) {
		this.documentoDescricao = documentoDescricao;
	}

	public Long getCentroCustoId() {
		return centroCustoId;
	}

	public void setCentroCustoId(Long centroCustoId) {
		this.centroCustoId = centroCustoId;
	}

	public String getCentroCustoDescricao() {
		return centroCustoDescricao;
	}

	public void setCentroCustoDescricao(String centroCustoDescricao) {
		this.centroCustoDescricao = centroCustoDescricao;
	}

	@Override
	public String toString() {
		return "LancamentosDTO [id=" + id + ", dataCompetencia=" + dataCompetencia + ", dataConciliacao="
				+ dataConciliacao + ", valor=" + valor + ", tipo=" + tipo + ", historico=" + historico
				+ ", numeroDocumento=" + numeroDocumento + ", pessoaId=" + pessoaId + ", pessoaNome=" + pessoaNome
				+ ", contaId=" + contaId + ", contaDescricao=" + contaDescricao + ", documentoId=" + documentoId
				+ ", documentoDescricao=" + documentoDescricao + ", centroCustoId=" + centroCustoId
				+ ", centroCustoDescricao=" + centroCustoDescricao + "]";
	}
}
