package br.com.ribeiro.repository;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.ribeiro.service.dto.PessoaDTO;

/**
 * Spring Data repository for the Pessoa entity.
 */
@Repository
public interface PessoaCustomRepository 
{
    EntityManager getEntityManager();

    Page<PessoaDTO> findPessoaDtoList(PessoaDTO filter, Pageable pageable);
}
