package br.com.ribeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.ribeiro.domain.CentroCusto;

/**
 * Spring Data repository for the CentroCusto entity.
 */
@Repository
public interface CentroCustoRepository extends JpaRepository<CentroCusto, Long>, QuerydslPredicateExecutor<CentroCusto> {

}
