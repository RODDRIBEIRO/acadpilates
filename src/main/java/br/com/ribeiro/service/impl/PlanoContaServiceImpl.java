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

import br.com.ribeiro.domain.PlanoConta;
import br.com.ribeiro.domain.QPlanoConta;
import br.com.ribeiro.repository.PlanoContaRepository;
import br.com.ribeiro.service.PlanoContaService;
import br.com.ribeiro.service.dto.PlanoContaDTO;
import br.com.ribeiro.service.mapper.PlanoContaMapper;

/**
 * Service Implementation for managing {@link PlanoConta}.
 */
@Service
@Transactional
public class PlanoContaServiceImpl extends AbstractService implements PlanoContaService {

	private final Logger log = LoggerFactory.getLogger(PlanoContaServiceImpl.class);

	private final PlanoContaRepository planoContaRepository;

	private static final QPlanoConta qPlanoConta = QPlanoConta.planoConta;

	private final PlanoContaMapper planoContaMapper;

	public PlanoContaServiceImpl(PlanoContaRepository planoContaRepository, PlanoContaMapper planoContaMapper) {
		this.planoContaRepository = planoContaRepository;
		this.planoContaMapper = planoContaMapper;
	}

	/**
	 * Save a planoConta.
	 *
	 * @param planoContaDTO the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public PlanoContaDTO save(PlanoContaDTO planoContaDTO) {
		log.debug("Request to save PlanoConta : {}", planoContaDTO);
		PlanoConta planoConta = planoContaMapper.toEntity(planoContaDTO);
		planoConta = planoContaRepository.save(planoConta);
		return planoContaMapper.toDto(planoConta);
	}

	/**
	 * Get all the planoContas.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<PlanoContaDTO> findAll(Pageable pageable) {
		log.debug("Request to get all PlanoContas");
		return planoContaRepository.findAll(pageable).map(planoContaMapper::toDto);
	}

	/**
	 * Get one planoConta by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<PlanoContaDTO> findOne(Long id) {
		log.debug("Request to get PlanoConta : {}", id);
		return planoContaRepository.findById(id).map(planoContaMapper::toDto);
	}

	/**
	 * Delete the planoConta by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete PlanoConta : {}", id);
		planoContaRepository.deleteById(id);
	}

	/**
	 * Search corresponding to the query.
	 *
	 * @param PlanoContaDTO the query of the search
	 * @param query         the query of the search
	 * @param pageable      the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<PlanoContaDTO> search(PlanoContaDTO query, Pageable pageable) {
		log.debug("Request to search for a page of PlanoContaDTO for query {}", query);
		BooleanExpression predicate = qPlanoConta.id.isNotNull();
		if (isNotEmpty(query.getDescricao())) {
			predicate = predicate.and(qPlanoConta.descricao.containsIgnoreCase(query.getDescricao()));
		}
		return planoContaRepository.findAll(predicate, pageable).map(planoContaMapper::toDto);
	}
}
