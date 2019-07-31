package br.com.ribeiro.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ribeiro.service.dto.LancamentoDTO;

/**
 * Service Interface for managing {@link br.com.ribeiro.domain.Lancamento}.
 */
public interface LancamentoService {

	/**
	 * Save a lancamento.
	 *
	 * @param lancamentoDTO the entity to save.
	 * @return the persisted entity.
	 */
	LancamentoDTO save(LancamentoDTO lancamentoDTO);

	/**
	 * Get all the lancamento.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	Page<LancamentoDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" lancamento.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	Optional<LancamentoDTO> findOne(Long id);

	/**
	 * Delete the "id" lancamento.
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
	Page<LancamentoDTO> search(LancamentoDTO dto, Pageable pageable);
}
