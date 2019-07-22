package br.com.ribeiro.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.ribeiro.domain.Documento} entity.
 */
public class DocumentoDTO extends AbstractDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2903324770639609570L;

	private Long id;

    @NotNull
    private String descricao;

    @NotNull
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

        DocumentoDTO documentoDTO = (DocumentoDTO) o;
        if (documentoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentoDTO{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", situacao='" + isSituacao() + "'" +
            "}";
    }
}
