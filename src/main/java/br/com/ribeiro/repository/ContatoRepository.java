package br.com.ribeiro.repository;

import br.com.ribeiro.domain.Contato;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Contato entity.
 */
@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
