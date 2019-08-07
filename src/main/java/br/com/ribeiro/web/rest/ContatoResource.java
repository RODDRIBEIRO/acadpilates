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

import br.com.ribeiro.service.ContatoService;
import br.com.ribeiro.service.dto.ContatoDTO;
import br.com.ribeiro.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link br.com.ribeiro.domain.Contato}.
 */
@RestController
@RequestMapping("/api")
public class ContatoResource {

    private final Logger log = LoggerFactory.getLogger(ContatoResource.class);

    private static final String ENTITY_NAME = "contato";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContatoService contatoService;

    public ContatoResource(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    /**
     * {@code POST  /contatos} : Create a new contato.
     *
     * @param contatoDTO the contatoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new contatoDTO, or with status {@code 400 (Bad Request)} if
     *         the contato has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contatos")
    public ResponseEntity<ContatoDTO> createContato(@RequestBody ContatoDTO contatoDTO) throws URISyntaxException {
        log.debug("REST request to save Contato : {}", contatoDTO);
        if (contatoDTO.getId() != null) {
            throw new BadRequestAlertException("A new contato cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContatoDTO result = contatoService.save(contatoDTO);
        return ResponseEntity
                .created(new URI("/api/contatos/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /contatos} : Updates an existing contato.
     *
     * @param contatoDTO the contatoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated contatoDTO, or with status {@code 400 (Bad Request)} if
     *         the contatoDTO is not valid, or with status
     *         {@code 500 (Internal Server Error)} if the contatoDTO couldn't be
     *         updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contatos")
    public ResponseEntity<ContatoDTO> updateContato(@RequestBody ContatoDTO contatoDTO) throws URISyntaxException {
        log.debug("REST request to update Contato : {}", contatoDTO);
        if (contatoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContatoDTO result = contatoService.save(contatoDTO);
        return ResponseEntity.ok().headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contatoDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /contatos} : get all the contatos.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of contatos in body.
     */
    @GetMapping("/contatos")
    public ResponseEntity<List<ContatoDTO>> getAllContatos(Pageable pageable,
            @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Contatos");
        Page<ContatoDTO> page = contatoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /contatos/:id} : get the "id" contato.
     *
     * @param id the id of the contatoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the contatoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contatos/{id}")
    public ResponseEntity<ContatoDTO> getContato(@PathVariable Long id) {
        log.debug("REST request to get Contato : {}", id);
        Optional<ContatoDTO> contatoDTO = contatoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contatoDTO);
    }

    /**
     * {@code DELETE  /contatos/:id} : delete the "id" contato.
     *
     * @param id the id of the contatoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contatos/{id}")
    public ResponseEntity<Void> deleteContato(@PathVariable Long id) {
        log.debug("REST request to delete Contato : {}", id);
        contatoService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
