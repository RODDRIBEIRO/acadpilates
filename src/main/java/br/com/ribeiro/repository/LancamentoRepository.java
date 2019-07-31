package br.com.ribeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.ribeiro.domain.Lancamentos;

/**
 * Spring Data repository for the Lancamentos entity.
 */
@Repository
public interface LancamentosRepository
		extends JpaRepository<Lancamentos, Long>, QuerydslPredicateExecutor<Lancamentos> {

}
