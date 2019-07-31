package br.com.ribeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.ribeiro.domain.Lancamento;

/**
 * Spring Data repository for the Lancamento entity.
 */
@Repository
public interface LancamentoRepository
		extends JpaRepository<Lancamento, Long>, QuerydslPredicateExecutor<Lancamento> {

}
