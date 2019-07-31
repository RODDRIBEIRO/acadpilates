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

import br.com.ribeiro.domain.Lancamento;
import br.com.ribeiro.domain.QLancamento;
import br.com.ribeiro.repository.LancamentoRepository;
import br.com.ribeiro.service.LancamentoService;
import br.com.ribeiro.service.dto.LancamentoDTO;
import br.com.ribeiro.service.mapper.LancamentoMapper;

/**
 * Service Implementation for managing {@link Lancamento}.
 */
@Service
@Transactional
public class LancamentoServiceImpl implements LancamentoService {

	private final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);

	private final LancamentoRepository lancamentoRepository;

	private final LancamentoMapper lancamentoMapper;

	private final static QLancamento qLancamento = QLancamento.lancamento;

	public LancamentoServiceImpl(LancamentoRepository lancamentoRepository, LancamentoMapper lancamentoMapper) {
		this.lancamentoRepository = lancamentoRepository;
		this.lancamentoMapper = lancamentoMapper;
	}

	/**
	 * Save a lancamento.
	 *
	 * @param lancamentoDTO the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public LancamentoDTO save(LancamentoDTO lancamentoDTO) {
		log.debug("Request to save Lancamento : {}", lancamentoDTO);
		Lancamento lancamento = lancamentoMapper.toEntity(lancamentoDTO);
		lancamento = lancamentoRepository.save(lancamento);
		return lancamentoMapper.toDto(lancamento);
	}

	/**
	 * Get all the lancamento.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<LancamentoDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Lancamento");
		return lancamentoRepository.findAll(pageable).map(lancamentoMapper::toDto);
	}

	/**
	 * Get one lancamento by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<LancamentoDTO> findOne(Long id) {
		log.debug("Request to get Lancamento : {}", id);
		return lancamentoRepository.findById(id).map(lancamentoMapper::toDto);
	}

	/**
	 * Delete the lancamento by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Lancamento : {}", id);
		lancamentoRepository.deleteById(id);
	}

	/**
	 * Search corresponding to the query.
	 *
	 * @param LancamentoDTO the query of the search
	 * @param query          the query of the search
	 * @param pageable       the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<LancamentoDTO> search(LancamentoDTO query, Pageable pageable) {
		log.debug("Request to search for a page of LancamentoDTO for query {}", query);
		BooleanExpression predicate = qLancamento.id.isNotNull();
		/*
		 * if (isNotEmpty(query.getDescricao())) { predicate =
		 * predicate.and(qLancamento.descricao.containsIgnoreCase(query.getDescricao())
		 * ); }
		 */
		return lancamentoRepository.findAll(predicate, pageable).map(lancamentoMapper::toDto);
	}
}
