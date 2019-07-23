package br.com.ribeiro.service.impl;

import static br.com.ribeiro.service.util.NullUtil.isNotEmpty;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.ribeiro.domain.Conta;
import br.com.ribeiro.domain.QConta;
import br.com.ribeiro.repository.ContaRepository;
import br.com.ribeiro.service.ContaService;
import br.com.ribeiro.service.dto.ContaDTO;
import br.com.ribeiro.service.mapper.ContaMapper;

/**
 * Service Implementation for managing {@link Conta}.
 */
@Service
@Transactional
public class ContaServiceImpl extends AbstractService implements ContaService {

	private final Logger log = LoggerFactory.getLogger(ContaServiceImpl.class);

	private final ContaRepository contaRepository;

	private final ContaMapper contaMapper;

	private static final QConta qConta = QConta.conta;

	public ContaServiceImpl(ContaRepository contaRepository, ContaMapper contaMapper) {
		this.contaRepository = contaRepository;
		this.contaMapper = contaMapper;
	}

	/**
	 * Save a conta.
	 *
	 * @param contaDTO the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public ContaDTO save(ContaDTO contaDTO) {
		log.debug("Request to save Conta : {}", contaDTO);
		Conta conta = contaMapper.toEntity(contaDTO);
		conta = contaRepository.save(conta);
		return contaMapper.toDto(conta);
	}

	/**
	 * Get all the contas.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<ContaDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Contas");
		return contaRepository.findAll(pageable).map(contaMapper::toDto);
	}

	/**
	 * Get one conta by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<ContaDTO> findOne(Long id) {
		log.debug("Request to get Conta : {}", id);
		return contaRepository.findById(id).map(contaMapper::toDto);
	}

	/**
	 * Delete the conta by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Conta : {}", id);
		contaRepository.deleteById(id);
	}

	/**
	 * Search corresponding to the query.
	 *
	 * @param ContaDTO the query of the search
	 * @param query    the query of the search
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<ContaDTO> search(ContaDTO query, Pageable pageable) {
		log.debug("Request to search for a page of ContaDTO for query {}", query);
		BooleanExpression predicate = qConta.id.isNotNull();
		if (isNotEmpty(query.getDescricao())) {
			predicate = predicate.and(qConta.descricao.containsIgnoreCase(query.getDescricao()));
		}
		return contaRepository.findAll(predicate, pageable).map(contaMapper::toDto);
	}
}
