package br.com.ribeiro.service.impl;

import static br.com.ribeiro.service.util.NullUtil.isNotEmpty;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.ribeiro.domain.Contato;
import br.com.ribeiro.domain.Endereco;
import br.com.ribeiro.domain.Pessoa;
import br.com.ribeiro.domain.QPessoa;
import br.com.ribeiro.repository.PessoaRepository;
import br.com.ribeiro.service.PessoaService;
import br.com.ribeiro.service.dto.PessoaDTO;
import br.com.ribeiro.service.mapper.ContatoMapper;
import br.com.ribeiro.service.mapper.EnderecoMapper;
import br.com.ribeiro.service.mapper.PessoaMapper;

/**
 * Service Implementation for managing {@link Pessoa}.
 */
@Service
@Transactional
public class PessoaServiceImpl extends AbstractService implements PessoaService {

	private final Logger log = LoggerFactory.getLogger(PessoaServiceImpl.class);

	private final PessoaRepository pessoaRepository;

	private static final QPessoa qPessoa = QPessoa.pessoa;

	private final PessoaMapper pessoaMapper;

	private final EnderecoMapper enderecoMapper;

	private final ContatoMapper contatoMapper;

	public PessoaServiceImpl(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper,
			EnderecoMapper enderecoMapper, ContatoMapper contatoMapper) {
		this.pessoaRepository = pessoaRepository;
		this.pessoaMapper = pessoaMapper;
		this.enderecoMapper = enderecoMapper;
		this.contatoMapper = contatoMapper;
	}

	/**
	 * Save a pessoa.
	 *
	 * @param pessoaDTO the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public PessoaDTO save(PessoaDTO pessoaDTO) {
		log.debug("Request to save Pessoa : {}", pessoaDTO);
		Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);

		List<Endereco> listaEnderecos = enderecoMapper.toEntity(pessoaDTO.getEnderecos());
		Set<Endereco> enderecos = new HashSet<Endereco>(listaEnderecos);

		List<Contato> listaContatos = contatoMapper.toEntity(pessoaDTO.getContatos());
		Set<Contato> contatos = new HashSet<Contato>(listaContatos);

		pessoa.setEnderecos(enderecos);
		pessoa.setContatos(contatos);
		pessoa.dataCadastro(Instant.now());
		pessoa.dataNascimento(pessoaDTO.getDataNascimento());

		pessoa = pessoaRepository.save(pessoa);
		return pessoaMapper.toDto(pessoa);
	}

	/**
	 * Get all the pessoas.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<PessoaDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Pessoas");
		return pessoaRepository.findAll(pageable).map(pessoaMapper::toDto);
	}

	/**
	 * Get one pessoa by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<PessoaDTO> findOne(Long id) {
		log.debug("Request to get Pessoa : {}", id);
		return pessoaRepository.findById(id).map(pessoaMapper::toDto);
	}

	/**
	 * Delete the pessoa by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Pessoa : {}", id);
		pessoaRepository.deleteById(id);
	}

	/**
	 * Search corresponding to the query.
	 *
	 * @param PessoaDTO the query of the search
	 * @param query     the query of the search
	 * @param pageable  the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<PessoaDTO> search(PessoaDTO query, Pageable pageable) {
		log.debug("Request to search for a page of PessoaDTO for query {}", query);
		BooleanExpression predicate = qPessoa.id.isNotNull();
		if (isNotEmpty(query.getNome())) {
			predicate = predicate.and(qPessoa.nome.containsIgnoreCase(query.getNome()));
		}
		return pessoaRepository.findAll(predicate, pageable).map(pessoaMapper::toDto);
	}
}
