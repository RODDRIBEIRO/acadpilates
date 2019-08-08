package br.com.ribeiro.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.ribeiro.repository.PessoaCustomRepository;
import br.com.ribeiro.service.dto.PessoaDTO;

@Repository
public class PessoaRepositoryImpl implements PessoaCustomRepository {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<PessoaDTO> findPessoaDtoList(PessoaDTO filter, Pageable pageable) {
        String sql = "SELECT p.id," + " p.nome as pessoa_nome," + " p.situacao as pessoa_situacao" + " FROM pessoa p"
                + " WHERE 0 = 0";

        if (filter != null) {
            if (filter.getId() != null) {
                sql += " AND p.id = :id";
            }
            if (filter.getNome() != null) {
                sql += " AND p.nome = :pessoaNome";
            }
            if (filter.getSituacao() != null) {
                sql += " AND p.situacao = :situacao";
            }
            if (StringUtils.isNotBlank(filter.getQuery())) {
                sql += " AND (p.nome ILIKE :query)";
                /*
                 * exemplo para mais inputs sql
                 * +=" AND (p.nome ILIKE :query OR grp.nome ILIKE :query)";
                 */
            }

        }
        sql += " ORDER BY p.nome";
        Query query = em.createNativeQuery(sql);
        if (filter != null) {
            if (filter.getId() != null) {
                query.setParameter("id", filter.getId());
            }
            if (filter.getSituacao() != null) {
                query.setParameter("situacao", filter.getSituacao());
            }
            if (StringUtils.isNotBlank(filter.getQuery())) {
                query.setParameter("query", "%" + filter.getQuery().trim() + "%");
            }
        }
        if (pageable != null) {
            query.setFirstResult(Integer.parseInt(pageable.getOffset() + ""));
            query.setMaxResults(pageable.getPageSize());
        }
        List<Object[]> list = query.getResultList();
        List<PessoaDTO> dtoList = new ArrayList<>();
        if (!list.isEmpty()) {
            PessoaDTO dto = null;
            int i = 0;
            for (Object[] obj : list) {
                dto = new PessoaDTO();
                i = 0;
                if (obj[i] != null) {
                    dto.setId(Long.parseLong(obj[i].toString()));
                }
                i++;
                if (obj[i] != null) {
                    dto.setNome(obj[i].toString());
                }
                i++;
                if (obj[i] != null) {
                    if (obj[i].equals(true)) {
                        dto.setSituacao(true);
                    } else {
                        dto.setSituacao(false);
                    }

                }
                dtoList.add(dto);
            }
        }
        return new PageImpl<PessoaDTO>(dtoList);
    }
}
