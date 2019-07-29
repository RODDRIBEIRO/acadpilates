package br.com.ribeiro.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ribeiro.service.dto.LancamentosDTO;

/**
 * Service Interface for managing {@link br.com.ribeiro.domain.Lancamentos}.
 */
public interface LancamentosService {

	/**
	 * Save a lancamentos.
	 *
	 * @param lancamentosDTO the entity to save.
	 * @return the persisted entity.
	 */
	LancamentosDTO save(LancamentosDTO lancamentosDTO);

	/**
	 * Get all the lancamentos.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	Page<LancamentosDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" lancamentos.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	Optional<LancamentosDTO> findOne(Long id);

	/**
	 * Delete the "id" lancamentos.
	 *
	 * @param id the id of the entity.
	 */
	void delete(Long id);

	/**
	 * Search
	 * 
	 * @param searchDTO search
	 * @param pageable  pagination
	 * @return list of PessoaDTO
	 */
	Page<LancamentosDTO> search(LancamentosDTO dto, Pageable pageable);
}
