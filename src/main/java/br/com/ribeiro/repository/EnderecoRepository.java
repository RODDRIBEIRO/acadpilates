package br.com.ribeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ribeiro.domain.Endereco;

/**
 * Spring Data repository for the Endereco entity.
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
