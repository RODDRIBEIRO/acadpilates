package br.com.ribeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.ribeiro.domain.Documento;

/**
 * Spring Data repository for the Documento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long>, QuerydslPredicateExecutor<Documento> {

}
	