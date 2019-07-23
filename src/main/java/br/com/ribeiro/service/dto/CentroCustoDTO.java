package br.com.ribeiro.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.ribeiro.domain.CentroCusto} entity.
 */
public class CentroCustoDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4583925312005825139L;

	private Long id;

	private String descricao;

	private Boolean situacao;

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

	public Boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		CentroCustoDTO centroCustoDTO = (CentroCustoDTO) o;
		if (centroCustoDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), centroCustoDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "CentroCustoDTO{" + "id=" + getId() + ", descricao='" + getDescricao() + "'" + ", situacao='"
				+ isSituacao() + "'" + "}";
	}
}
