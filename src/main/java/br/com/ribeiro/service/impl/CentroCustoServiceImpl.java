package br.com.ribeiro.service.impl;

import static br.com.ribeiro.service.util.NullUtil.isNotEmpty;

import java.util.Optional;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ribeiro.domain.CentroCusto;
import br.com.ribeiro.domain.QCentroCusto;
import br.com.ribeiro.repository.CentroCustoRepository;
import br.com.ribeiro.service.CentroCustoService;
import br.com.ribeiro.service.dto.CentroCustoDTO;
import br.com.ribeiro.service.mapper.CentroCustoMapper;

/**
 * Service Implementation for managing {@link CentroCusto}.
 */
@Service
@Transactional
public class CentroCustoServiceImpl extends AbstractService implements CentroCustoService {

	private final Logger log = LoggerFactory.getLogger(CentroCustoServiceImpl.class);

	private final CentroCustoRepository centroCustoRepository;

	private final CentroCustoMapper centroCustoMapper;

	private static final QCentroCusto qCentroCusto = QCentroCusto.centroCusto;

	public CentroCustoServiceImpl(CentroCustoRepository centroCustoRepository, CentroCustoMapper centroCustoMapper) {
		this.centroCustoRepository = centroCustoRepository;
		this.centroCustoMapper = centroCustoMapper;
	}

	/**
	 * Save a centroCusto.
	 *
	 * @param centroCustoDTO the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public CentroCustoDTO save(CentroCustoDTO centroCustoDTO) {
		log.debug("Request to save CentroCusto : {}", centroCustoDTO);
		CentroCusto centroCusto = centroCustoMapper.toEntity(centroCustoDTO);
		centroCusto = centroCustoRepository.save(centroCusto);
		return centroCustoMapper.toDto(centroCusto);
	}

	/**
	 * Get all the centroCustos.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<CentroCustoDTO> findAll(Pageable pageable) {
		log.debug("Request to get all CentroCustos");
		return centroCustoRepository.findAll(pageable).map(centroCustoMapper::toDto);
	}

	/**
	 * Get one centroCusto by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<CentroCustoDTO> findOne(Long id) {
		log.debug("Request to get CentroCusto : {}", id);
		return centroCustoRepository.findById(id).map(centroCustoMapper::toDto);
	}

	/**
	 * Delete the centroCusto by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete CentroCusto : {}", id);
		centroCustoRepository.deleteById(id);
	}

	/**
	 * Search corresponding to the query.
	 *
	 * @param CentroCustoDTO the query of the search
	 * @param query          the query of the search
	 * @param pageable       the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<CentroCustoDTO> search(CentroCustoDTO query, Pageable pageable) {
		log.debug("Request to search for a page of CentroCustoDTO for query {}", query);
		BooleanExpression predicate = qCentroCusto.id.isNotNull();
		if (isNotEmpty(query.getDescricao())) {
			predicate = predicate.and(qCentroCusto.descricao.containsIgnoreCase(query.getDescricao()));
		}
		return centroCustoRepository.findAll(predicate, pageable).map(centroCustoMapper::toDto);
	}
}
