package br.com.ribeiro.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A Conta.
 */
@Entity
@Table(name = "conta")
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@NotNull
	@Column(name = "situacao", nullable = false)
	private Boolean situacao;

	@NotNull
	@Column(name = "tipo", nullable = false)
	private Integer tipo;

	@Column(name = "numero_conta")
	private String numeroConta;

	@Column(name = "agencia")
	private String agencia;
	
	@Column(name = "saldo")
	private Double saldo;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Conta descricao(String descricao) {
		this.descricao = descricao;
		return this;
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

	public Conta tipo(Integer tipo) {
		this.tipo = tipo;
		return this;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public Conta numeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
		return this;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getAgencia() {
		return agencia;
	}

	public Conta agencia(String agencia) {
		this.agencia = agencia;
		return this;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

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
		if (!(o instanceof Conta)) {
			return false;
		}
		return id != null && id.equals(((Conta) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "Conta{" + "id=" + getId() + ", descricao='" + getDescricao() + "'" + ", situacao=" + getSituacao()
				+ ", tipo=" + getTipo() + ", numeroConta='" + getNumeroConta() + "'" + ", agencia='" + getAgencia()
				+ "'" + "}";
	}
}
