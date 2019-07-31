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

import br.com.ribeiro.service.LancamentoService;
import br.com.ribeiro.service.dto.LancamentoDTO;
import br.com.ribeiro.web.rest.errors.BadRequestAlertException;
import br.com.ribeiro.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;

/**
 * REST controller for managing {@link br.com.ribeiro.domain.Lancamento}.
 */
@RestController
@RequestMapping("/api")
public class LancamentoResource {

	private final Logger log = LoggerFactory.getLogger(LancamentoResource.class);

	private static final String ENTITY_NAME = "lancamento";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final LancamentoService lancamentoService;

	public LancamentoResource(LancamentoService lancamentoService) {
		this.lancamentoService = lancamentoService;
	}

	/**
	 * {@code POST  /lancamento} : Create a new lancamento.
	 *
	 * @param lancamentoDTO the lancamentoDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new lancamentoDTO, or with status {@code 400 (Bad Request)}
	 *         if the lancamento has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/lancamento")
	public ResponseEntity<LancamentoDTO> createLancamento(@RequestBody LancamentoDTO lancamentoDTO)
			throws URISyntaxException {
		log.debug("REST request to save Lancamento : {}", lancamentoDTO);
		if (lancamentoDTO.getId() != null) {
			throw new BadRequestAlertException("A new lancamento cannot already have an ID", ENTITY_NAME, "idexists");
		}
		LancamentoDTO result = lancamentoService.save(lancamentoDTO);
		return ResponseEntity
				.created(new URI("/api/lancamento/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * {@code PUT  /lancamento} : Updates an existing lancamento.
	 *
	 * @param lancamentoDTO the lancamentoDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated lancamentoDTO, or with status {@code 400 (Bad Request)}
	 *         if the lancamentoDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the lancamentoDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/lancamento")
	public ResponseEntity<LancamentoDTO> updateLancamento(@RequestBody LancamentoDTO lancamentoDTO)
			throws URISyntaxException {
		log.debug("REST request to update Lancamento : {}", lancamentoDTO);
		if (lancamentoDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		LancamentoDTO result = lancamentoService.save(lancamentoDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
				lancamentoDTO.getId().toString())).body(result);
	}

	/**
	 * {@code GET  /lancamento} : get all the lancamento.
	 *
	 * @param pageable    the pagination information.
	 * @param queryParams a {@link MultiValueMap} query parameters.
	 * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of lancamento in body.
	 */
	@GetMapping("/lancamento")
	public ResponseEntity<List<LancamentoDTO>> getAllLancamento(Pageable pageable,
			@RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
		log.debug("REST request to get a page of Lancamento");
		Page<LancamentoDTO> page = lancamentoService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "api/lancamento");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * {@code GET  /lancamento/:id} : get the "id" lancamento.
	 *
	 * @param id the id of the lancamentoDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the lancamentoDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/lancamento/{id}")
	public ResponseEntity<LancamentoDTO> getLancamento(@PathVariable Long id) {
		log.debug("REST request to get Lancamento : {}", id);
		Optional<LancamentoDTO> lancamentoDTO = lancamentoService.findOne(id);
		return ResponseUtil.wrapOrNotFound(lancamentoDTO);
	}

	/**
	 * {@code DELETE  /lancamento/:id} : delete the "id" lancamento.
	 *
	 * @param id the id of the lancamentoDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/lancamento/{id}")
	public ResponseEntity<Void> deleteLancamento(@PathVariable Long id) {
		log.debug("REST request to delete Lancamento : {}", id);
		lancamentoService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
				.build();
	}

	/**
	 * SEARCH /_search/lancamento
	 *
	 * @param filter   the LancamentoDTO filter information
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of PessoaDTO in
	 *         body
	 */
	@GetMapping("/_search/lancamento")
	@Timed
	public ResponseEntity<List<LancamentoDTO>> searchByFilter(LancamentoDTO filter,
			@SortDefault.SortDefaults({ @SortDefault(sort = "descricao") }) Pageable pageable) {
		log.debug("REST request to get a page of LancamentoDTO by filter : {}", filter);
		Page<LancamentoDTO> page = lancamentoService.search(filter, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(filter, page,
				"/api/_search/lancamento");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
}
