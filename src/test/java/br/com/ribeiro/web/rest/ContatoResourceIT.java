package br.com.ribeiro.web.rest;

import br.com.ribeiro.AcadpilatesApp;
import br.com.ribeiro.domain.Contato;
import br.com.ribeiro.repository.ContatoRepository;
import br.com.ribeiro.service.ContatoService;
import br.com.ribeiro.service.dto.ContatoDTO;
import br.com.ribeiro.service.mapper.ContatoMapper;
import br.com.ribeiro.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static br.com.ribeiro.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link ContatoResource} REST controller.
 */
@SpringBootTest(classes = AcadpilatesApp.class)
public class ContatoResourceIT {

    private static final Integer DEFAULT_TIPO = 1;
    private static final Integer UPDATED_TIPO = 2;

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_DDD = "AAAAAAAAAA";
    private static final String UPDATED_DDD = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PRINCIPAL = false;
    private static final Boolean UPDATED_PRINCIPAL = true;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ContatoMapper contatoMapper;

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restContatoMockMvc;

    private Contato contato;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContatoResource contatoResource = new ContatoResource(contatoService);
        this.restContatoMockMvc = MockMvcBuilders.standaloneSetup(contatoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contato createEntity(EntityManager em) {
        Contato contato = new Contato()
            .tipo(DEFAULT_TIPO)
            .numero(DEFAULT_NUMERO)
            .ddd(DEFAULT_DDD)
            .email(DEFAULT_EMAIL)
            .principal(DEFAULT_PRINCIPAL);
        return contato;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contato createUpdatedEntity(EntityManager em) {
        Contato contato = new Contato()
            .tipo(UPDATED_TIPO)
            .numero(UPDATED_NUMERO)
            .ddd(UPDATED_DDD)
            .email(UPDATED_EMAIL)
            .principal(UPDATED_PRINCIPAL);
        return contato;
    }

    @BeforeEach
    public void initTest() {
        contato = createEntity(em);
    }

    @Test
    @Transactional
    public void createContato() throws Exception {
        int databaseSizeBeforeCreate = contatoRepository.findAll().size();

        // Create the Contato
        ContatoDTO contatoDTO = contatoMapper.toDto(contato);
        restContatoMockMvc.perform(post("/api/contatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contatoDTO)))
            .andExpect(status().isCreated());

        // Validate the Contato in the database
        List<Contato> contatoList = contatoRepository.findAll();
        assertThat(contatoList).hasSize(databaseSizeBeforeCreate + 1);
        Contato testContato = contatoList.get(contatoList.size() - 1);
        assertThat(testContato.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testContato.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testContato.getDdd()).isEqualTo(DEFAULT_DDD);
        assertThat(testContato.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testContato.isPrincipal()).isEqualTo(DEFAULT_PRINCIPAL);
    }

    @Test
    @Transactional
    public void createContatoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contatoRepository.findAll().size();

        // Create the Contato with an existing ID
        contato.setId(1L);
        ContatoDTO contatoDTO = contatoMapper.toDto(contato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContatoMockMvc.perform(post("/api/contatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contato in the database
        List<Contato> contatoList = contatoRepository.findAll();
        assertThat(contatoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllContatoes() throws Exception {
        // Initialize the database
        contatoRepository.saveAndFlush(contato);

        // Get all the contatoList
        restContatoMockMvc.perform(get("/api/contatoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contato.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].ddd").value(hasItem(DEFAULT_DDD.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].principal").value(hasItem(DEFAULT_PRINCIPAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getContato() throws Exception {
        // Initialize the database
        contatoRepository.saveAndFlush(contato);

        // Get the contato
        restContatoMockMvc.perform(get("/api/contatoes/{id}", contato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contato.getId().intValue()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.ddd").value(DEFAULT_DDD.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.principal").value(DEFAULT_PRINCIPAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingContato() throws Exception {
        // Get the contato
        restContatoMockMvc.perform(get("/api/contatoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContato() throws Exception {
        // Initialize the database
        contatoRepository.saveAndFlush(contato);

        int databaseSizeBeforeUpdate = contatoRepository.findAll().size();

        // Update the contato
        Contato updatedContato = contatoRepository.findById(contato.getId()).get();
        // Disconnect from session so that the updates on updatedContato are not directly saved in db
        em.detach(updatedContato);
        updatedContato
            .tipo(UPDATED_TIPO)
            .numero(UPDATED_NUMERO)
            .ddd(UPDATED_DDD)
            .email(UPDATED_EMAIL)
            .principal(UPDATED_PRINCIPAL);
        ContatoDTO contatoDTO = contatoMapper.toDto(updatedContato);

        restContatoMockMvc.perform(put("/api/contatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contatoDTO)))
            .andExpect(status().isOk());

        // Validate the Contato in the database
        List<Contato> contatoList = contatoRepository.findAll();
        assertThat(contatoList).hasSize(databaseSizeBeforeUpdate);
        Contato testContato = contatoList.get(contatoList.size() - 1);
        assertThat(testContato.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testContato.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testContato.getDdd()).isEqualTo(UPDATED_DDD);
        assertThat(testContato.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testContato.isPrincipal()).isEqualTo(UPDATED_PRINCIPAL);
    }

    @Test
    @Transactional
    public void updateNonExistingContato() throws Exception {
        int databaseSizeBeforeUpdate = contatoRepository.findAll().size();

        // Create the Contato
        ContatoDTO contatoDTO = contatoMapper.toDto(contato);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContatoMockMvc.perform(put("/api/contatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contato in the database
        List<Contato> contatoList = contatoRepository.findAll();
        assertThat(contatoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContato() throws Exception {
        // Initialize the database
        contatoRepository.saveAndFlush(contato);

        int databaseSizeBeforeDelete = contatoRepository.findAll().size();

        // Delete the contato
        restContatoMockMvc.perform(delete("/api/contatoes/{id}", contato.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Contato> contatoList = contatoRepository.findAll();
        assertThat(contatoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contato.class);
        Contato contato1 = new Contato();
        contato1.setId(1L);
        Contato contato2 = new Contato();
        contato2.setId(contato1.getId());
        assertThat(contato1).isEqualTo(contato2);
        contato2.setId(2L);
        assertThat(contato1).isNotEqualTo(contato2);
        contato1.setId(null);
        assertThat(contato1).isNotEqualTo(contato2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContatoDTO.class);
        ContatoDTO contatoDTO1 = new ContatoDTO();
        contatoDTO1.setId(1L);
        ContatoDTO contatoDTO2 = new ContatoDTO();
        assertThat(contatoDTO1).isNotEqualTo(contatoDTO2);
        contatoDTO2.setId(contatoDTO1.getId());
        assertThat(contatoDTO1).isEqualTo(contatoDTO2);
        contatoDTO2.setId(2L);
        assertThat(contatoDTO1).isNotEqualTo(contatoDTO2);
        contatoDTO1.setId(null);
        assertThat(contatoDTO1).isNotEqualTo(contatoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(contatoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(contatoMapper.fromId(null)).isNull();
    }
}
