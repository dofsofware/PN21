package com.secusociale.portail.service;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.DeclarationJournalJob;
import com.secusociale.portail.domain.JournalJob;
import com.secusociale.portail.domain.SuiviJob;
import com.secusociale.portail.domain.enumeration.ModeExecution;
import com.secusociale.portail.repository.DeclarationJournalJobRepository;
import com.secusociale.portail.repository.JournalJobRepository;
import com.secusociale.portail.service.dto.JournalJobDTO;
import com.secusociale.portail.service.dto.SuiviJobDTO;
import com.secusociale.portail.service.mapper.JournalJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service Implementation for managing {@link SuiviJob}.
 */
@Service
@Transactional
public class JournalJobService {

    private final Logger log = LoggerFactory.getLogger(JournalJobService.class);

    private final JournalJobRepository journalJobRepository;

    private final DeclarationJournalJobRepository declarationJournalJobRepository;

    private final EntityManager entityManager;

    private  JournalJobMapper journalJobMapper;

    public JournalJobService(JournalJobRepository journalJobRepository, DeclarationJournalJobRepository declarationJournalJobRepository, EntityManager entityManager, JournalJobMapper journalJobMapper) {
        this.journalJobRepository = journalJobRepository;
        this.declarationJournalJobRepository = declarationJournalJobRepository;
        this.entityManager = entityManager;
        this.journalJobMapper = journalJobMapper;
    }

   /* *//**
     * Save a suiviJob.
     *
     * @param JournalJobDTO the entity to save.
     * @return the persisted entity.
     */
    public JournalJobDTO save(JournalJobDTO JournalJobDTO) {
        log.debug("Request to save SuiviJob : {}", JournalJobDTO);
        JournalJob journalJob = journalJobMapper.toEntity(JournalJobDTO);
        journalJob = journalJobRepository.save(journalJob);
        return journalJobMapper.toDto(journalJob);
    }

    /**
     * Get all the suiviJobs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<JournalJobDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SuiviJobs");
        return journalJobRepository.findAll(pageable)
            .map(journalJobMapper::toDto);
    }

    /**
     * Get one suiviJob by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<JournalJobDTO> findOne(Long id) {
        log.debug("Request to get SuiviJob : {}", id);
        return journalJobRepository.findById(id)
            .map(journalJobMapper::toDto);
    }


    /**
     * Get one suiviJob by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<JournalJob> findById(Long id) {
        log.debug("Request to get SuiviJob : {}", id);
        return journalJobRepository.findById(id);
    }


    @Transactional
    public int updateJobExecution(Long journalJobId, String demarrePar, ModeExecution modeExecution) {
        String jpql = "UPDATE JournalJob j SET j.demarrePar = :demarrePar, j.modeExecution = :modeExecution WHERE j.id = :id";

        return entityManager.createQuery(jpql)
            .setParameter("demarrePar", demarrePar)
            .setParameter("modeExecution", modeExecution)
            .setParameter("id", journalJobId)
            .executeUpdate();
    }


    public JournalJob saves(JournalJob journalJob) {
        log.debug("Request to save JournalJob : {}", journalJob);
        return journalJobRepository.save(journalJob);
    }

    /**
     * Get one journalJob by nom.
     *
     * @param nom the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<JournalJobDTO> findJob(String nom) {
        log.debug("Request to get SuiviJob : {}", nom);
        return journalJobRepository.findByNom(nom)
            .map(journalJobMapper::toDto);
    }

    public List<DeclarationJournalJob> findDeclarationsByJournalJobId(Long journalJobId) {
        log.debug("Request to get all DeclarationJournalJob by journalJobId : {}", journalJobId);
        return declarationJournalJobRepository.findByJournalJobId(journalJobId);
    }


    public String stopSynchronisationDelaration(String uuid) {
        log.debug("Arrêt demandé pour la synchronisation SEND_INVOICES_TO_PSRM");

        Optional<JournalJob> jobOpt = journalJobRepository.findByBatchExecutionId(uuid);

        if (!jobOpt.isPresent()) {
            throw new NoSuchElementException("Aucun job trouvé avec l'UUID : " + uuid);
        }


        JournalJob journalJob = jobOpt.get();
        String statut = journalJob.getStatut();
        Instant termineLe = journalJob.getTermineLe();

        if ("RUNNING".equals(statut)) {
            throw new IllegalStateException("Le job est en cours d'exécution. Statut actuel : " + statut);
        }

        String apiUrl = Constants.MS_SYNCHRO_URL + "/api/synch/interrupt/" + uuid;
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                log.debug("Réponse de l'API interruption : {}", response.getBody());
                return response.getBody();
            } else {
                String msg = "Erreur lors de l'appel à l'API d'interruption : " + response.getStatusCode();
                log.error(msg);
                throw new RuntimeException(msg);
            }

        } catch (HttpClientErrorException e) {
            String msg = "Erreur de client HTTP : " + e.getStatusCode();
            log.error(msg);
            throw new RuntimeException(msg, e);
        } catch (Exception e) {
            String msg = "Erreur inattendue : " + e.getMessage();
            log.error(msg);
            throw new RuntimeException(msg, e);
        }
    }
}
