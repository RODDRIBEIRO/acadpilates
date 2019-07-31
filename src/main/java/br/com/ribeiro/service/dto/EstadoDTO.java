package br.com.ribeiro.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.ribeiro.domain.Estado} entity.
 */
public class EstadoDTO implements Serializable {

    private static final long serialVersionUID = -5815894971846577172L;

    private Long id;

    private String nomeEstado;


    private Long paisId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public Long getPaisId() {
        return paisId;
    }

    public void setPaisId(Long paisId) {
        this.paisId = paisId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EstadoDTO estadoDTO = (EstadoDTO) o;
        if (estadoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), estadoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EstadoDTO{" +
            "id=" + getId() +
            ", nomeEstado='" + getNomeEstado() + "'" +
            ", pais=" + getPaisId() +
            "}";
    }
}
