package br.com.ribeiro.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * A Pessoa.
 */
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "tipo")
	private Integer tipo;

	@Column(name = "cpf_cnpj")
	private String cpfCnpj;

	@Column(name = "rg_insc_est")
	private String rgInscEst;

	@Column(name = "data_cadastro")
	private Instant dataCadastro;

	@Column(name = "data_nascimento")
	private Instant dataNascimento;

	@Lob
	@Column(name = "foto")
	private byte[] foto;

	@Column(name = "foto_content_type")
	private String fotoContentType;

	@Column(name = "situacao")
	private Boolean situacao;

	@Column(name = "sexo")
	private Integer sexo;

	@Column(name = "email")
	private String email;

	@NotNull
	@Max(value = 1)
	@Column(name = "categoria", nullable = false)
	private Integer categoria;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "pessoa_id")
	private Set<Endereco> enderecos = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "pessoa_id")
	private Set<Contato> contatos = new HashSet<>();

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public Pessoa nome(String nome) {
		this.nome = nome;
		return this;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTipo() {
		return tipo;
	}

	public Pessoa tipo(Integer tipo) {
		this.tipo = tipo;
		return this;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public Pessoa cpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
		return this;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getRgInscEst() {
		return rgInscEst;
	}

	public Pessoa rgInscEst(String rgInscEst) {
		this.rgInscEst = rgInscEst;
		return this;
	}

	public void setRgInscEst(String rgInscEst) {
		this.rgInscEst = rgInscEst;
	}

	public Instant getDataCadastro() {
		return dataCadastro;
	}

	public Pessoa dataCadastro(Instant dataCadastro) {
		this.dataCadastro = dataCadastro;
		return this;
	}

	public void setDataCadastro(Instant dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Instant getDataNascimento() {
		return dataNascimento;
	}

	public Pessoa dataNascimento(Instant dataNascimento) {
		this.dataNascimento = dataNascimento;
		return this;
	}

	public void setDataNascimento(Instant dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public byte[] getFoto() {
		return foto;
	}

	public Pessoa foto(byte[] foto) {
		this.foto = foto;
		return this;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getFotoContentType() {
		return fotoContentType;
	}

	public Pessoa fotoContentType(String fotoContentType) {
		this.fotoContentType = fotoContentType;
		return this;
	}

	public void setFotoContentType(String fotoContentType) {
		this.fotoContentType = fotoContentType;
	}

	public Boolean isSituacao() {
		return situacao;
	}

	public Pessoa situacao(Boolean situacao) {
		this.situacao = situacao;
		return this;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public Pessoa categoria(Integer categoria) {
		this.categoria = categoria;
		return this;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public Pessoa enderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
		return this;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<Contato> getContatos() {
		return contatos;
	}

	public Pessoa contatos(Set<Contato> contatos) {
		this.contatos = contatos;
		return this;
	}

	public void setContatos(Set<Contato> contatos) {
		this.contatos = contatos;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Pessoa)) {
			return false;
		}
		return id != null && id.equals(((Pessoa) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "Pessoa{" + "id=" + getId() + ", nome='" + getNome() + "'" + ", tipo=" + getTipo() + ", cpfCnpj='"
				+ getCpfCnpj() + "'" + ", rgInscEst='" + getRgInscEst() + "'" + ", dataCadastro='" + getDataCadastro()
				+ "'" + ", dataNascimento='" + getDataNascimento() + "'" + ", foto='" + getFoto() + "'"
				+ ", fotoContentType='" + getFotoContentType() + "'" + ", situacao='" + isSituacao() + "'"
				+ ", categoria=" + getCategoria() + "}";
	}
}
