package br.com.ribeiro.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import br.com.ribeiro.service.PessoaService;
import br.com.ribeiro.service.dto.PessoaDTO;
import br.com.ribeiro.web.rest.errors.BadRequestAlertException;
import br.com.ribeiro.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;

/**
 * REST controller for managing {@link br.com.ribeiro.domain.Pessoa}.
 */
@RestController
@RequestMapping("/api")
public class PessoaResource {

	private final Logger log = LoggerFactory.getLogger(PessoaResource.class);

	private static final String ENTITY_NAME = "pessoa";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final PessoaService pessoaService;

	public PessoaResource(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	/**
	 * {@code POST  /pessoas} : Create a new pessoa.
	 *
	 * @param pessoaDTO the pessoaDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new pessoaDTO, or with status {@code 400 (Bad Request)} if
	 *         the pessoa has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/pessoas")
	public ResponseEntity<PessoaDTO> createPessoa(@Valid @RequestBody PessoaDTO pessoaDTO) throws URISyntaxException {
		log.debug("REST request to save Pessoa : {}", pessoaDTO);
		if (pessoaDTO.getId() != null) {
			throw new BadRequestAlertException("A new pessoa cannot already have an ID", ENTITY_NAME, "idexists");
		}
		PessoaDTO result = pessoaService.save(pessoaDTO);
		return ResponseEntity
				.created(new URI("/api/pessoas/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * {@code PUT  /pessoas} : Updates an existing pessoa.
	 *
	 * @param pessoaDTO the pessoaDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated pessoaDTO, or with status {@code 400 (Bad Request)} if
	 *         the pessoaDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the pessoaDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/pessoas")
	public ResponseEntity<PessoaDTO> updatePessoa(@Valid @RequestBody PessoaDTO pessoaDTO) throws URISyntaxException {
		log.debug("REST request to update Pessoa : {}", pessoaDTO);
		if (pessoaDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		PessoaDTO result = pessoaService.save(pessoaDTO);
		return ResponseEntity.ok().headers(
				HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pessoaDTO.getId().toString()))
				.body(result);
	}

	/**
	 * {@code GET  /pessoas} : get all the pessoas.
	 *
	 * @param pageable    the pagination information.
	 * @param queryParams a {@link MultiValueMap} query parameters.
	 * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of pessoas in body.
	 */
	@GetMapping("/pessoas")
	public ResponseEntity<List<PessoaDTO>> getAllPessoas(Pageable pageable,
			@RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
		log.debug("REST request to get a page of Pessoas");
		Page<PessoaDTO> page = pessoaService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "api/pessoas");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * {@code GET  /pessoas/:id} : get the "id" pessoa.
	 *
	 * @param id the id of the pessoaDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the pessoaDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/pessoas/{id}")
	public ResponseEntity<PessoaDTO> getPessoa(@PathVariable Long id) {
		log.debug("REST request to get Pessoa : {}", id);
		Optional<PessoaDTO> pessoaDTO = pessoaService.findOne(id);
		return ResponseUtil.wrapOrNotFound(pessoaDTO);
	}

	/**
	 * {@code DELETE  /pessoas/:id} : delete the "id" pessoa.
	 *
	 * @param id the id of the pessoaDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/pessoas/{id}")
	public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
		log.debug("REST request to delete Pessoa : {}", id);
		pessoaService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
				.build();
	}

	/**
	 * SEARCH /_search/pessoas
	 *
	 * @param filter   the PessoaDTO filter information
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of PessoaDTO in
	 *         body
	 */
	@GetMapping("/_search/pessoas")
	@Timed
	public ResponseEntity<List<PessoaDTO>> searchByFilter(PessoaDTO filter,
			@SortDefault.SortDefaults({ @SortDefault(sort = "nome") }) Pageable pageable) {
		log.debug("REST request to get a page of PessoaDTO by filter : {}", filter);
		Page<PessoaDTO> page = pessoaService.search(filter, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(filter, page, "/api/_search/pessoas");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	@GetMapping("/_autocomplete/pessoas")
	public ResponseEntity<List<PessoaDTO>> autocompleteByFilter(PessoaDTO filter, Pageable pageable) {
		log.debug("REST request to get a page of PessoaDTO by autocomplete filter : {}", filter);
		Page<PessoaDTO> page = pessoaService.autocomplete(filter, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(filter, page,
				"/api/_autocomplete/pessoas");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

}
