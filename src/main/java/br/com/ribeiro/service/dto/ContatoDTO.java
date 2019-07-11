package br.com.ribeiro.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.ribeiro.domain.Contato} entity.
 */
public class ContatoDTO implements Serializable {

    private Long id;

    private Integer tipo;

    private String numero;

    private String ddd;

    private Boolean principal;


    private Long pessoaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContatoDTO contatoDTO = (ContatoDTO) o;
        if (contatoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contatoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContatoDTO{" +
            "id=" + getId() +
            ", tipo=" + getTipo() +
            ", numero='" + getNumero() + "'" +
            ", ddd='" + getDdd() + "'" +
            ", principal='" + isPrincipal() + "'" +
            ", pessoa=" + getPessoaId() +
            "}";
    }
}
