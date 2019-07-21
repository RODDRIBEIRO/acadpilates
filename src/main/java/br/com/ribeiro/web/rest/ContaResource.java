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

import br.com.ribeiro.service.ContaService;
import br.com.ribeiro.service.dto.ContaDTO;
import br.com.ribeiro.web.rest.errors.BadRequestAlertException;
import br.com.ribeiro.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;

/**
 * REST controller for managing {@link br.com.ribeiro.domain.Conta}.
 */
@RestController
@RequestMapping("/api")
public class ContaResource {

	private final Logger log = LoggerFactory.getLogger(ContaResource.class);

	private static final String ENTITY_NAME = "conta";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final ContaService contaService;

	public ContaResource(ContaService contaService) {
		this.contaService = contaService;
	}

	/**
	 * {@code POST  /contas} : Create a new conta.
	 *
	 * @param contaDTO the contaDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new contaDTO, or with status {@code 400 (Bad Request)} if
	 *         the conta has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/contas")
	public ResponseEntity<ContaDTO> createConta(@Valid @RequestBody ContaDTO contaDTO) throws URISyntaxException {
		log.debug("REST request to save Conta : {}", contaDTO);
		if (contaDTO.getId() != null) {
			throw new BadRequestAlertException("A new conta cannot already have an ID", ENTITY_NAME, "idexists");
		}
		ContaDTO result = contaService.save(contaDTO);
		return ResponseEntity
				.created(new URI("/api/contas/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * {@code PUT  /contas} : Updates an existing conta.
	 *
	 * @param contaDTO the contaDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated contaDTO, or with status {@code 400 (Bad Request)} if the
	 *         contaDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the contaDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/contas")
	public ResponseEntity<ContaDTO> updateConta(@Valid @RequestBody ContaDTO contaDTO) throws URISyntaxException {
		log.debug("REST request to update Conta : {}", contaDTO);
		if (contaDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		ContaDTO result = contaService.save(contaDTO);
		return ResponseEntity.ok().headers(
				HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contaDTO.getId().toString()))
				.body(result);
	}

	/**
	 * {@code GET  /contas} : get all the contas.
	 *
	 * @param pageable    the pagination information.
	 * @param queryParams a {@link MultiValueMap} query parameters.
	 * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of contas in body.
	 */
	@GetMapping("/contas")
	public ResponseEntity<List<ContaDTO>> getAllContas(Pageable pageable,
			@RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
		log.debug("REST request to get a page of Contas");
		Page<ContaDTO> page = contaService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "api/contas");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * {@code GET  /contas/:id} : get the "id" conta.
	 *
	 * @param id the id of the contaDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the contaDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/contas/{id}")
	public ResponseEntity<ContaDTO> getConta(@PathVariable Long id) {
		log.debug("REST request to get Conta : {}", id);
		Optional<ContaDTO> contaDTO = contaService.findOne(id);
		return ResponseUtil.wrapOrNotFound(contaDTO);
	}

	/**
	 * {@code DELETE  /contas/:id} : delete the "id" conta.
	 *
	 * @param id the id of the contaDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/contas/{id}")
	public ResponseEntity<Void> deleteConta(@PathVariable Long id) {
		log.debug("REST request to delete Conta : {}", id);
		contaService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
				.build();
	}

	/**
	 * SEARCH /_search/contas
	 *
	 * @param filter   the ContaDTO filter information
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of PessoaDTO in
	 *         body
	 */
	@GetMapping("/_search/contas")
	@Timed
	public ResponseEntity<List<ContaDTO>> searchByFilter(ContaDTO filter,
			@SortDefault.SortDefaults({ @SortDefault(sort = "descricao") }) Pageable pageable) {
		log.debug("REST request to get a page of ContaDTO by filter : {}", filter);
		Page<ContaDTO> page = contaService.search(filter, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(filter, page, "/api/_search/contas");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
}
