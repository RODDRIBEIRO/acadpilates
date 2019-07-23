package br.com.ribeiro.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ribeiro.service.dto.CentroCustoDTO;

/**
 * Service Interface for managing {@link br.com.ribeiro.domain.CentroCusto}.
 */
public interface CentroCustoService {

	/**
	 * Save a centroCusto.
	 *
	 * @param centroCustoDTO the entity to save.
	 * @return the persisted entity.
	 */
	CentroCustoDTO save(CentroCustoDTO centroCustoDTO);

	/**
	 * Get all the centroCustos.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	Page<CentroCustoDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" centroCusto.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	Optional<CentroCustoDTO> findOne(Long id);

	/**
	 * Delete the "id" centroCusto.
	 *
	 * @param id the id of the entity.
	 */
	void delete(Long id);

	Page<CentroCustoDTO> search(CentroCustoDTO filter, Pageable pageable);
}
