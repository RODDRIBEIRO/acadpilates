package br.com.ribeiro.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.ribeiro.domain.Pais} entity.
 */
public class PaisDTO implements Serializable {

    private static final long serialVersionUID = -4001456324375078552L;

    private Long id;

    private String nomePais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePais() {
        return nomePais;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PaisDTO paisDTO = (PaisDTO) o;
        if (paisDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paisDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaisDTO{" + "id=" + getId() + ", nomePais='" + getNomePais() + "'" + "}";
    }
}
