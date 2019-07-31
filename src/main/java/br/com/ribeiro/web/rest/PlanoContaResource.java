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

import br.com.ribeiro.service.PlanoContaService;
import br.com.ribeiro.service.dto.PlanoContaDTO;
import br.com.ribeiro.service.dto.PlanoContaDTO;
import br.com.ribeiro.web.rest.errors.BadRequestAlertException;
import br.com.ribeiro.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;

/**
 * REST controller for managing {@link br.com.ribeiro.domain.PlanoConta}.
 */
@RestController
@RequestMapping("/api")
public class PlanoContaResource {

	private final Logger log = LoggerFactory.getLogger(PlanoContaResource.class);

	private static final String ENTITY_NAME = "planoConta";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final PlanoContaService planoContaService;

	public PlanoContaResource(PlanoContaService planoContaService) {
		this.planoContaService = planoContaService;
	}

	/**
	 * {@code POST  /plano-contas} : Create a new planoConta.
	 *
	 * @param planoContaDTO the planoContaDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new planoContaDTO, or with status {@code 400 (Bad Request)}
	 *         if the planoConta has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/plano-contas")
	public ResponseEntity<PlanoContaDTO> createPlanoConta(@RequestBody PlanoContaDTO planoContaDTO)
			throws URISyntaxException {
		log.debug("REST request to save PlanoConta : {}", planoContaDTO);
		if (planoContaDTO.getId() != null) {
			throw new BadRequestAlertException("A new planoConta cannot already have an ID", ENTITY_NAME, "idexists");
		}
		PlanoContaDTO result = planoContaService.save(planoContaDTO);
		return ResponseEntity
				.created(new URI("/api/plano-contas/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * {@code PUT  /plano-contas} : Updates an existing planoConta.
	 *
	 * @param planoContaDTO the planoContaDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated planoContaDTO, or with status {@code 400 (Bad Request)}
	 *         if the planoContaDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the planoContaDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/plano-contas")
	public ResponseEntity<PlanoContaDTO> updatePlanoConta(@RequestBody PlanoContaDTO planoContaDTO)
			throws URISyntaxException {
		log.debug("REST request to update PlanoConta : {}", planoContaDTO);
		if (planoContaDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		PlanoContaDTO result = planoContaService.save(planoContaDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
				planoContaDTO.getId().toString())).body(result);
	}

	/**
	 * {@code GET  /plano-contas} : get all the planoContas.
	 *
	 * @param pageable    the pagination information.
	 * @param queryParams a {@link MultiValueMap} query parameters.
	 * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of planoContas in body.
	 */
	@GetMapping("/plano-contas")
	public ResponseEntity<List<PlanoContaDTO>> getAllPlanoContas(Pageable pageable,
			@RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
		log.debug("REST request to get a page of PlanoContas");
		Page<PlanoContaDTO> page = planoContaService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "api/plano-contas");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * {@code GET  /plano-contas/:id} : get the "id" planoConta.
	 *
	 * @param id the id of the planoContaDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the planoContaDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/plano-contas/{id}")
	public ResponseEntity<PlanoContaDTO> getPlanoConta(@PathVariable Long id) {
		log.debug("REST request to get PlanoConta : {}", id);
		Optional<PlanoContaDTO> planoContaDTO = planoContaService.findOne(id);
		return ResponseUtil.wrapOrNotFound(planoContaDTO);
	}

	/**
	 * {@code DELETE  /plano-contas/:id} : delete the "id" planoConta.
	 *
	 * @param id the id of the planoContaDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/plano-contas/{id}")
	public ResponseEntity<Void> deletePlanoConta(@PathVariable Long id) {
		log.debug("REST request to delete PlanoConta : {}", id);
		planoContaService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
				.build();
	}

	/**
	 * SEARCH /_search/planoContas
	 *
	 * @param filter   the PlanoContaDTO filter information
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of PlanoContaDTO
	 *         in body
	 */
	@GetMapping("/_search/plano-contas")
	@Timed
	public ResponseEntity<List<PlanoContaDTO>> searchByFilter(PlanoContaDTO filter,
			@SortDefault.SortDefaults({ @SortDefault(sort = "descricao") }) Pageable pageable) {
		log.debug("REST request to get a page of PlanoContaDTO by filter : {}", filter);
		Page<PlanoContaDTO> page = planoContaService.search(filter, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(filter, page,
				"/api/_search/planoContas");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
}
