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
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import br.com.ribeiro.service.LancamentosService;
import br.com.ribeiro.service.dto.LancamentosDTO;
import br.com.ribeiro.web.rest.errors.BadRequestAlertException;
import br.com.ribeiro.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;

/**
 * REST controller for managing {@link br.com.ribeiro.domain.Lancamentos}.
 */
@RestController
@RequestMapping("/api")
public class LancamentosResource {

	private final Logger log = LoggerFactory.getLogger(LancamentosResource.class);

	private static final String ENTITY_NAME = "lancamentos";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final LancamentosService lancamentosService;

	public LancamentosResource(LancamentosService lancamentosService) {
		this.lancamentosService = lancamentosService;
	}

	/**
	 * {@code POST  /lancamentos} : Create a new lancamentos.
	 *
	 * @param lancamentosDTO the lancamentosDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new lancamentosDTO, or with status {@code 400 (Bad Request)}
	 *         if the lancamentos has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/lancamentos")
	public ResponseEntity<LancamentosDTO> createLancamentos(@RequestBody LancamentosDTO lancamentosDTO)
			throws URISyntaxException {
		log.debug("REST request to save Lancamentos : {}", lancamentosDTO);
		if (lancamentosDTO.getId() != null) {
			throw new BadRequestAlertException("A new lancamentos cannot already have an ID", ENTITY_NAME, "idexists");
		}
		LancamentosDTO result = lancamentosService.save(lancamentosDTO);
		return ResponseEntity
				.created(new URI("/api/lancamentos/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * {@code PUT  /lancamentos} : Updates an existing lancamentos.
	 *
	 * @param lancamentosDTO the lancamentosDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated lancamentosDTO, or with status {@code 400 (Bad Request)}
	 *         if the lancamentosDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the lancamentosDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/lancamentos")
	public ResponseEntity<LancamentosDTO> updateLancamentos(@RequestBody LancamentosDTO lancamentosDTO)
			throws URISyntaxException {
		log.debug("REST request to update Lancamentos : {}", lancamentosDTO);
		if (lancamentosDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		LancamentosDTO result = lancamentosService.save(lancamentosDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
				lancamentosDTO.getId().toString())).body(result);
	}

	/**
	 * {@code GET  /lancamentos} : get all the lancamentos.
	 *
	 * @param pageable    the pagination information.
	 * @param queryParams a {@link MultiValueMap} query parameters.
	 * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of lancamentos in body.
	 */
	@GetMapping("/lancamentos")
	public ResponseEntity<List<LancamentosDTO>> getAllLancamentos(Pageable pageable,
			@RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
		log.debug("REST request to get a page of Lancamentos");
		Page<LancamentosDTO> page = lancamentosService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "api/lancamentos");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * {@code GET  /lancamentos/:id} : get the "id" lancamentos.
	 *
	 * @param id the id of the lancamentosDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the lancamentosDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/lancamentos/{id}")
	public ResponseEntity<LancamentosDTO> getLancamentos(@PathVariable Long id) {
		log.debug("REST request to get Lancamentos : {}", id);
		Optional<LancamentosDTO> lancamentosDTO = lancamentosService.findOne(id);
		return ResponseUtil.wrapOrNotFound(lancamentosDTO);
	}

	/**
	 * {@code DELETE  /lancamentos/:id} : delete the "id" lancamentos.
	 *
	 * @param id the id of the lancamentosDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/lancamentos/{id}")
	public ResponseEntity<Void> deleteLancamentos(@PathVariable Long id) {
		log.debug("REST request to delete Lancamentos : {}", id);
		lancamentosService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
				.build();
	}

	/**
	 * SEARCH /_search/lancamentos
	 *
	 * @param filter   the LancamentosDTO filter information
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of PessoaDTO in
	 *         body
	 */
	@GetMapping("/_search/lancamentos")
	@Timed
	public ResponseEntity<List<LancamentosDTO>> searchByFilter(LancamentosDTO filter,
			@SortDefault.SortDefaults({ @SortDefault(sort = "descricao") }) Pageable pageable) {
		log.debug("REST request to get a page of LancamentosDTO by filter : {}", filter);
		Page<LancamentosDTO> page = lancamentosService.search(filter, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(filter, page,
				"/api/_search/lancamentos");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
}
