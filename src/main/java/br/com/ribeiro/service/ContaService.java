package br.com.ribeiro.service;

import br.com.ribeiro.service.dto.ContaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.ribeiro.domain.Conta}.
 */
public interface ContaService {

    /**
     * Save a conta.
     *
     * @param contaDTO the entity to save.
     * @return the persisted entity.
     */
    ContaDTO save(ContaDTO contaDTO);

    /**
     * Get all the contas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ContaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" conta.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContaDTO> findOne(Long id);

    /**
     * Delete the "id" conta.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

	Page<ContaDTO> search(ContaDTO filter, Pageable pageable);
}
