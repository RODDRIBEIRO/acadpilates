package br.com.ribeiro.repository;

import br.com.ribeiro.domain.Pais;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Pais entity.
 */
@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

}
