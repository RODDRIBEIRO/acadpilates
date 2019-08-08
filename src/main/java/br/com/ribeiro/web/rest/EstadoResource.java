package br.com.ribeiro.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ribeiro.service.EstadoService;
import br.com.ribeiro.service.dto.EstadoDTO;
import br.com.ribeiro.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link br.com.ribeiro.domain.Estado}.
 */
@RestController
@RequestMapping("/api")
public class EstadoResource {

    private final Logger log = LoggerFactory.getLogger(EstadoResource.class);

    private static final String ENTITY_NAME = "estado";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstadoService estadoService;

    public EstadoResource(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    /**
     * {@code POST  /estados} : Create a new estado.
     *
     * @param estadoDTO the estadoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new estadoDTO, or with status {@code 400 (Bad Request)} if
     *         the estado has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estados")
    public ResponseEntity<EstadoDTO> createEstado(@RequestBody EstadoDTO estadoDTO) throws URISyntaxException {
        log.debug("REST request to save Estado : {}", estadoDTO);
        if (estadoDTO.getId() != null) {
            throw new BadRequestAlertException("A new estado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadoDTO result = estadoService.save(estadoDTO);
        return ResponseEntity
                .created(new URI("/api/estados/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /estados} : Updates an existing estado.
     *
     * @param estadoDTO the estadoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated estadoDTO, or with status {@code 400 (Bad Request)} if
     *         the estadoDTO is not valid, or with status
     *         {@code 500 (Internal Server Error)} if the estadoDTO couldn't be
     *         updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estados")
    public ResponseEntity<EstadoDTO> updateEstado(@RequestBody EstadoDTO estadoDTO) throws URISyntaxException {
        log.debug("REST request to update Estado : {}", estadoDTO);
        if (estadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadoDTO result = estadoService.save(estadoDTO);
        return ResponseEntity.ok().headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, estadoDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /estados} : get all the estados.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of estados in body.
     */
    @GetMapping("/estados")
    public ResponseEntity<List<EstadoDTO>> getAllEstados(Pageable pageable,
            @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Estados");
        Page<EstadoDTO> page = estadoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /estados/:id} : get the "id" estado.
     *
     * @param id the id of the estadoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the estadoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estados/{id}")
    public ResponseEntity<EstadoDTO> getEstado(@PathVariable Long id) {
        log.debug("REST request to get Estado : {}", id);
        Optional<EstadoDTO> estadoDTO = estadoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadoDTO);
    }

    /**
     * {@code DELETE  /estados/:id} : delete the "id" estado.
     *
     * @param id the id of the estadoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estados/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable Long id) {
        log.debug("REST request to delete Estado : {}", id);
        estadoService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
