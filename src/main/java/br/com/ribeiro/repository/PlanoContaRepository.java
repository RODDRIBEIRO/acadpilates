package br.com.ribeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.ribeiro.domain.PlanoConta;

/**
 * Spring Data repository for the PlanoConta entity.
 */
@Repository
public interface PlanoContaRepository extends JpaRepository<PlanoConta, Long>, QuerydslPredicateExecutor<PlanoConta> {

}
