package br.com.ribeiro.service.impl;

import static br.com.ribeiro.service.util.NullUtil.isNotEmpty;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.ribeiro.domain.Documento;
import br.com.ribeiro.domain.QDocumento;
import br.com.ribeiro.repository.DocumentoRepository;
import br.com.ribeiro.service.DocumentoService;
import br.com.ribeiro.service.dto.DocumentoDTO;
import br.com.ribeiro.service.mapper.DocumentoMapper;

/**
 * Service Implementation for managing {@link Documento}.
 */
@Service
@Transactional
public class DocumentoServiceImpl extends AbstractService implements DocumentoService {

	private final Logger log = LoggerFactory.getLogger(DocumentoServiceImpl.class);

	private final DocumentoRepository documentoRepository;

	private final DocumentoMapper documentoMapper;

	private static final QDocumento qDocumento = QDocumento.documento;

	public DocumentoServiceImpl(DocumentoRepository documentoRepository, DocumentoMapper documentoMapper) {
		this.documentoRepository = documentoRepository;
		this.documentoMapper = documentoMapper;
	}

	/**
	 * Save a documento.
	 *
	 * @param documentoDTO the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public DocumentoDTO save(DocumentoDTO documentoDTO) {
		log.debug("Request to save Documento : {}", documentoDTO);
		Documento documento = documentoMapper.toEntity(documentoDTO);
		documento = documentoRepository.save(documento);
		return documentoMapper.toDto(documento);
	}

	/**
	 * Get all the documentos.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<DocumentoDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Documentos");
		return documentoRepository.findAll(pageable).map(documentoMapper::toDto);
	}

	/**
	 * Get one documento by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<DocumentoDTO> findOne(Long id) {
		log.debug("Request to get Documento : {}", id);
		return documentoRepository.findById(id).map(documentoMapper::toDto);
	}

	/**
	 * Delete the documento by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Documento : {}", id);
		documentoRepository.deleteById(id);
	}

	/**
	 * Search corresponding to the query.
	 *
	 * @param DocumentoDTO the query of the search
	 * @param query        the query of the search
	 * @param pageable     the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<DocumentoDTO> search(DocumentoDTO query, Pageable pageable) {
		log.debug("Request to search for a page of DocumentoDTO for query {}", query);
		BooleanExpression predicate = qDocumento.id.isNotNull();
		if (isNotEmpty(query.getDescricao())) {
			predicate = predicate.and(qDocumento.descricao.containsIgnoreCase(query.getDescricao()));
		}
		return documentoRepository.findAll(predicate, pageable).map(documentoMapper::toDto);
	}
}
