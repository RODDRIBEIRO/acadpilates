package br.com.ribeiro.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Pais.
 */
@Entity
@Table(name = "pais")
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_pais")
    private String nomePais;

    @OneToMany(mappedBy = "pais")
    private Set<Estado> estados = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePais() {
        return nomePais;
    }

    public Pais nomePais(String nomePais) {
        this.nomePais = nomePais;
        return this;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }

    public Set<Estado> getEstados() {
        return estados;
    }

    public Pais estados(Set<Estado> estados) {
        this.estados = estados;
        return this;
    }

    public Pais addEstado(Estado estado) {
        this.estados.add(estado);
        estado.setPais(this);
        return this;
    }

    public Pais removeEstado(Estado estado) {
        this.estados.remove(estado);
        estado.setPais(null);
        return this;
    }

    public void setEstados(Set<Estado> estados) {
        this.estados = estados;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pais)) {
            return false;
        }
        return id != null && id.equals(((Pais) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Pais{" +
            "id=" + getId() +
            ", nomePais='" + getNomePais() + "'" +
            "}";
    }
}
