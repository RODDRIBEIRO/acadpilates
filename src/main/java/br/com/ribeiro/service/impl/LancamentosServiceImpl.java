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

import br.com.ribeiro.domain.Lancamentos;
import br.com.ribeiro.domain.QLancamentos;
import br.com.ribeiro.repository.LancamentosRepository;
import br.com.ribeiro.service.LancamentosService;
import br.com.ribeiro.service.dto.LancamentosDTO;
import br.com.ribeiro.service.mapper.LancamentosMapper;

/**
 * Service Implementation for managing {@link Lancamentos}.
 */
@Service
@Transactional
public class LancamentosServiceImpl implements LancamentosService {

	private final Logger log = LoggerFactory.getLogger(LancamentosServiceImpl.class);

	private final LancamentosRepository lancamentosRepository;

	private final LancamentosMapper lancamentosMapper;

	private final static QLancamentos qLancamentos = QLancamentos.lancamentos;

	public LancamentosServiceImpl(LancamentosRepository lancamentosRepository, LancamentosMapper lancamentosMapper) {
		this.lancamentosRepository = lancamentosRepository;
		this.lancamentosMapper = lancamentosMapper;
	}

	/**
	 * Save a lancamentos.
	 *
	 * @param lancamentosDTO the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public LancamentosDTO save(LancamentosDTO lancamentosDTO) {
		log.debug("Request to save Lancamentos : {}", lancamentosDTO);
		Lancamentos lancamentos = lancamentosMapper.toEntity(lancamentosDTO);
		lancamentos = lancamentosRepository.save(lancamentos);
		return lancamentosMapper.toDto(lancamentos);
	}

	/**
	 * Get all the lancamentos.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<LancamentosDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Lancamentos");
		return lancamentosRepository.findAll(pageable).map(lancamentosMapper::toDto);
	}

	/**
	 * Get one lancamentos by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<LancamentosDTO> findOne(Long id) {
		log.debug("Request to get Lancamentos : {}", id);
		return lancamentosRepository.findById(id).map(lancamentosMapper::toDto);
	}

	/**
	 * Delete the lancamentos by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Lancamentos : {}", id);
		lancamentosRepository.deleteById(id);
	}

	/**
	 * Search corresponding to the query.
	 *
	 * @param LancamentosDTO the query of the search
	 * @param query          the query of the search
	 * @param pageable       the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<LancamentosDTO> search(LancamentosDTO query, Pageable pageable) {
		log.debug("Request to search for a page of LancamentosDTO for query {}", query);
		BooleanExpression predicate = qLancamentos.id.isNotNull();
		/*
		 * if (isNotEmpty(query.getDescricao())) { predicate =
		 * predicate.and(qLancamentos.descricao.containsIgnoreCase(query.getDescricao())
		 * ); }
		 */
		return lancamentosRepository.findAll(predicate, pageable).map(lancamentosMapper::toDto);
	}
}
