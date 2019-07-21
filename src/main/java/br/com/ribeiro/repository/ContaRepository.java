package br.com.ribeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.ribeiro.domain.Conta;

/**
 * Spring Data repository for the Conta entity.
 */
@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>, QuerydslPredicateExecutor<Conta> {

}
