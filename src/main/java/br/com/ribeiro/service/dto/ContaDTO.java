package br.com.ribeiro.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link br.com.ribeiro.domain.Conta} entity.
 */
public class ContaDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1017326081959781142L;

	private Long id;

	private String descricao;

	@NotNull
	private Boolean situacao;

	@NotNull
	private Integer tipo;

	private String numeroConta;

	private String agencia;
	
	private Double saldo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ContaDTO contaDTO = (ContaDTO) o;
		if (contaDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), contaDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "ContaDTO{" + "id=" + getId() + ", descricao='" + getDescricao() + "'" + ", situacao=" + getSituacao()
				+ ", tipo=" + getTipo() + ", numeroConta='" + getNumeroConta() + "'" + ", agencia='" + getAgencia()
				+ "'" + "}";
	}
}
