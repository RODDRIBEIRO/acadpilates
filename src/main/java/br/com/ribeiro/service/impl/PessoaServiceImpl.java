package br.com.ribeiro.service.impl;

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

import br.com.ribeiro.domain.Endereco;
import br.com.ribeiro.domain.Pessoa;
import br.com.ribeiro.repository.EnderecoRepository;
import br.com.ribeiro.repository.PessoaRepository;
import br.com.ribeiro.service.PessoaService;
import br.com.ribeiro.service.dto.PessoaDTO;
import br.com.ribeiro.service.mapper.EnderecoMapper;
import br.com.ribeiro.service.mapper.PessoaMapper;

/**
 * Service Implementation for managing {@link Pessoa}.
 */
@Service
@Transactional
public class PessoaServiceImpl implements PessoaService {

	private final Logger log = LoggerFactory.getLogger(PessoaServiceImpl.class);

	private final PessoaRepository pessoaRepository;

	private final EnderecoRepository enderecoRepository;

	private final PessoaMapper pessoaMapper;

	private final EnderecoMapper enderecoMapper;

	public PessoaServiceImpl(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository,
			PessoaMapper pessoaMapper, EnderecoMapper enderecoMapper) {
		this.pessoaRepository = pessoaRepository;
		this.enderecoRepository = enderecoRepository;
		this.pessoaMapper = pessoaMapper;
		this.enderecoMapper = enderecoMapper;
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
		pessoa.setEnderecos(enderecos);
		pessoa.dataCadastro(Instant.now());
		
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
}
