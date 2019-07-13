package br.com.ribeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.ribeiro.domain.Pessoa;

/**
 * Spring Data repository for the Pessoa entity.
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, QuerydslPredicateExecutor<Pessoa> {

}
