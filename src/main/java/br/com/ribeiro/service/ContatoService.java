package br.com.ribeiro.service;

import br.com.ribeiro.service.dto.ContatoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.ribeiro.domain.Contato}.
 */
public interface ContatoService {

    /**
     * Save a contato.
     *
     * @param contatoDTO the entity to save.
     * @return the persisted entity.
     */
    ContatoDTO save(ContatoDTO contatoDTO);

    /**
     * Get all the contatoes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ContatoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" contato.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContatoDTO> findOne(Long id);

    /**
     * Delete the "id" contato.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
