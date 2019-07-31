package br.com.ribeiro.service;

import br.com.ribeiro.service.dto.PlanoContaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.ribeiro.domain.PlanoConta}.
 */
public interface PlanoContaService {

    /**
     * Save a planoConta.
     *
     * @param planoContaDTO the entity to save.
     * @return the persisted entity.
     */
    PlanoContaDTO save(PlanoContaDTO planoContaDTO);

    /**
     * Get all the planoContas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PlanoContaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planoConta.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PlanoContaDTO> findOne(Long id);

    /**
     * Delete the "id" planoConta.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

	Page<PlanoContaDTO> search(PlanoContaDTO query, Pageable pageable);
}
