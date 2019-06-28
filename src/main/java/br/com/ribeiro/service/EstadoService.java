package br.com.ribeiro.service;

import br.com.ribeiro.service.dto.EstadoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.ribeiro.domain.Estado}.
 */
public interface EstadoService {

    /**
     * Save a estado.
     *
     * @param estadoDTO the entity to save.
     * @return the persisted entity.
     */
    EstadoDTO save(EstadoDTO estadoDTO);

    /**
     * Get all the estados.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EstadoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" estado.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstadoDTO> findOne(Long id);

    /**
     * Delete the "id" estado.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
