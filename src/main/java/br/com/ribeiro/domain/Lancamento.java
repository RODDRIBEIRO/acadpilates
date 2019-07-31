package br.com.ribeiro.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Lancamento.
 */
@Entity
@Table(name = "lancamento")
public class Lancamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data_competencia")
	private Instant dataCompetencia;

	@Column(name = "data_conciliacao")
	private Instant dataConciliacao;

	@Column(name = "valor")
	private Double valor;

	@Column(name = "tipo")
	private Integer tipo;

	@Column(name = "historico")
	private String historico;

	@Column(name = "numero_documento")
	private String numeroDocumento;

	@ManyToOne
	@JsonIgnoreProperties("lancamento")
	private Pessoa pessoa;

	@ManyToOne
	@JsonIgnoreProperties("lancamento")
	private Conta conta;

	@ManyToOne
	@JsonIgnoreProperties("lancamento")
	private CentroCusto centroCusto;

	@ManyToOne
	@JsonIgnoreProperties("lancamento")
	private Documento documento;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDataCompetencia() {
		return dataCompetencia;
	}

	public Lancamento dataCompetencia(Instant dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
		return this;
	}

	public void setDataCompetencia(Instant dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
	}

	public Double getValor() {
		return valor;
	}

	public Lancamento valor(Double valor) {
		this.valor = valor;
		return this;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getTipo() {
		return tipo;
	}

	public Lancamento tipo(Integer tipo) {
		this.tipo = tipo;
		return this;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public Instant getDataConciliacao() {
		return dataConciliacao;
	}

	public void setDataConciliacao(Instant dataConciliacao) {
		this.dataConciliacao = dataConciliacao;
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

	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Lancamento pessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
		return this;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Conta getConta() {
		return conta;
	}

	public Lancamento conta(Conta conta) {
		this.conta = conta;
		return this;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Lancamento)) {
			return false;
		}
		return id != null && id.equals(((Lancamento) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", dataCompetencia=" + dataCompetencia + ", dataConciliacao=" + dataConciliacao
				+ ", valor=" + valor + ", tipo=" + tipo + ", historico=" + historico + ", numeroDocumento="
				+ numeroDocumento + ", pessoa=" + pessoa + ", conta=" + conta + ", centroCusto=" + centroCusto
				+ ", documento=" + documento + "]";
	}

}
