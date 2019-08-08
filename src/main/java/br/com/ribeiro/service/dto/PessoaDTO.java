package br.com.ribeiro.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link br.com.ribeiro.domain.Pessoa} entity.
 */
public class PessoaDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6109678514936262326L;

	private Long id;

	private String nome;

	private Integer tipo;

	private String cpfCnpj;

	private String rgInscEst;

	private Instant dataCadastro;

	private Instant dataNascimento;

	private Integer sexo;

	private String email;

	@Lob
	private byte[] foto;

	private String fotoContentType;
	private Boolean situacao;

	@NotNull
	@Max(value = 1)
	private Integer categoria;

	private List<EnderecoDTO> enderecos = new ArrayList<>();

	private List<ContatoDTO> contatos = new ArrayList<>();

	private String query;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getRgInscEst() {
		return rgInscEst;
	}

	public void setRgInscEst(String rgInscEst) {
		this.rgInscEst = rgInscEst;
	}

	public Instant getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Instant dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Instant getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Instant dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getFotoContentType() {
		return fotoContentType;
	}

	public void setFotoContentType(String fotoContentType) {
		this.fotoContentType = fotoContentType;
	}

	public Boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ContatoDTO> getContatos() {
		return contatos;
	}

	public void setContatos(List<ContatoDTO> contatos) {
		this.contatos = contatos;
	}

	public Boolean getSituacao() {
		return this.situacao;
	}

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		PessoaDTO pessoaDTO = (PessoaDTO) o;
		if (pessoaDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), pessoaDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", nome='" + getNome() + "'" + ", tipo='" + getTipo() + "'"
				+ ", cpfCnpj='" + getCpfCnpj() + "'" + ", rgInscEst='" + getRgInscEst() + "'" + ", dataCadastro='"
				+ getDataCadastro() + "'" + ", dataNascimento='" + getDataNascimento() + "'" + ", sexo='" + getSexo()
				+ "'" + ", email='" + getEmail() + "'" + ", foto='" + getFoto() + "'" + ", fotoContentType='"
				+ getFotoContentType() + "'" + ", situacao='" + isSituacao() + "'" + ", categoria='" + getCategoria()
				+ "'" + ", enderecos='" + getEnderecos() + "'" + ", contatos='" + getContatos() + "'" + ", query='"
				+ getQuery() + "'" + "}";
	}
}
