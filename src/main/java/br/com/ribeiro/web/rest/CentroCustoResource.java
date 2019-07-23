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

import br.com.ribeiro.service.CentroCustoService;
import br.com.ribeiro.service.dto.CentroCustoDTO;
import br.com.ribeiro.web.rest.errors.BadRequestAlertException;
import br.com.ribeiro.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;

/**
 * REST controller for managing {@link br.com.ribeiro.domain.CentroCusto}.
 */
@RestController
@RequestMapping("/api")
public class CentroCustoResource {

	private final Logger log = LoggerFactory.getLogger(CentroCustoResource.class);

	private static final String ENTITY_NAME = "centroCusto";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final CentroCustoService centroCustoService;

	public CentroCustoResource(CentroCustoService centroCustoService) {
		this.centroCustoService = centroCustoService;
	}

	/**
	 * {@code POST  /centro-custos} : Create a new centroCusto.
	 *
	 * @param centroCustoDTO the centroCustoDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new centroCustoDTO, or with status {@code 400 (Bad Request)}
	 *         if the centroCusto has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/centro-custos")
	public ResponseEntity<CentroCustoDTO> createCentroCusto(@RequestBody CentroCustoDTO centroCustoDTO)
			throws URISyntaxException {
		log.debug("REST request to save CentroCusto : {}", centroCustoDTO);
		if (centroCustoDTO.getId() != null) {
			throw new BadRequestAlertException("A new centroCusto cannot already have an ID", ENTITY_NAME, "idexists");
		}
		CentroCustoDTO result = centroCustoService.save(centroCustoDTO);
		return ResponseEntity
				.created(new URI("/api/centro-custos/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * {@code PUT  /centro-custos} : Updates an existing centroCusto.
	 *
	 * @param centroCustoDTO the centroCustoDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated centroCustoDTO, or with status {@code 400 (Bad Request)}
	 *         if the centroCustoDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the centroCustoDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/centro-custos")
	public ResponseEntity<CentroCustoDTO> updateCentroCusto(@RequestBody CentroCustoDTO centroCustoDTO)
			throws URISyntaxException {
		log.debug("REST request to update CentroCusto : {}", centroCustoDTO);
		if (centroCustoDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		CentroCustoDTO result = centroCustoService.save(centroCustoDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
				centroCustoDTO.getId().toString())).body(result);
	}

	/**
	 * {@code GET  /centro-custos} : get all the centroCustos.
	 *
	 * @param pageable    the pagination information.
	 * @param queryParams a {@link MultiValueMap} query parameters.
	 * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of centroCustos in body.
	 */
	@GetMapping("/centro-custos")
	public ResponseEntity<List<CentroCustoDTO>> getAllCentroCustos(Pageable pageable,
			@RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
		log.debug("REST request to get a page of CentroCustos");
		Page<CentroCustoDTO> page = centroCustoService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "api/centro-custos");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * {@code GET  /centro-custos/:id} : get the "id" centroCusto.
	 *
	 * @param id the id of the centroCustoDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the centroCustoDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/centro-custos/{id}")
	public ResponseEntity<CentroCustoDTO> getCentroCusto(@PathVariable Long id) {
		log.debug("REST request to get CentroCusto : {}", id);
		Optional<CentroCustoDTO> centroCustoDTO = centroCustoService.findOne(id);
		return ResponseUtil.wrapOrNotFound(centroCustoDTO);
	}

	/**
	 * {@code DELETE  /centro-custos/:id} : delete the "id" centroCusto.
	 *
	 * @param id the id of the centroCustoDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/centro-custos/{id}")
	public ResponseEntity<Void> deleteCentroCusto(@PathVariable Long id) {
		log.debug("REST request to delete CentroCusto : {}", id);
		centroCustoService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
				.build();
	}

	/**
	 * SEARCH /_search/centroCustos
	 *
	 * @param filter   the CentroCustoDTO filter information
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of PessoaDTO in
	 *         body
	 */
	@GetMapping("/_search/centro-custos")
	@Timed
	public ResponseEntity<List<CentroCustoDTO>> searchByFilter(CentroCustoDTO filter,
			@SortDefault.SortDefaults({ @SortDefault(sort = "descricao") }) Pageable pageable) {
		log.debug("REST request to get a page of CentroCustoDTO by filter : {}", filter);
		Page<CentroCustoDTO> page = centroCustoService.search(filter, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(filter, page,
				"/api/_search/centroCustos");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
}
