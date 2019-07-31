package br.com.ribeiro.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.ribeiro.domain.PlanoConta} entity.
 */
public class PlanoContaDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String descricao;

	private String numero;

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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		PlanoContaDTO planoContaDTO = (PlanoContaDTO) o;
		if (planoContaDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), planoContaDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "PlanoContaDTO{" + "id=" + getId() + ", descricao='" + getDescricao() + "'" + ", numero='" + getNumero()
				+ "'" + "}";
	}
}
