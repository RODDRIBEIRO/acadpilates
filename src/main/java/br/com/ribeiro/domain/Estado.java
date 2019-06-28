package br.com.ribeiro.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Estado.
 */
@Entity
@Table(name = "estado")
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_estado")
    private String nomeEstado;

    @ManyToOne
    @JsonIgnoreProperties("estados")
    private Pais pais;

    @OneToMany(mappedBy = "estado")
    private Set<Endereco> enderecos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public Estado nomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
        return this;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public Pais getPais() {
        return pais;
    }

    public Estado pais(Pais pais) {
        this.pais = pais;
        return this;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public Estado enderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
        return this;
    }

    public Estado addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
        endereco.setEstado(this);
        return this;
    }

    public Estado removeEndereco(Endereco endereco) {
        this.enderecos.remove(endereco);
        endereco.setEstado(null);
        return this;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Estado)) {
            return false;
        }
        return id != null && id.equals(((Estado) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Estado{" +
            "id=" + getId() +
            ", nomeEstado='" + getNomeEstado() + "'" +
            "}";
    }
}
