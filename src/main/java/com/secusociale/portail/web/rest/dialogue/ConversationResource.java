package com.secusociale.portail.web.rest.dialogue;

import com.secusociale.portail.domain.User;
import com.secusociale.portail.domain.dialogue.Conversation;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.dialogue.ConversationService;
import com.secusociale.portail.service.dialogue.ConversionQueryService;
import com.secusociale.portail.service.dto.dialogue.AnswerDTO;
import com.secusociale.portail.service.dto.dialogue.ConversationCriteria;
import com.secusociale.portail.service.dto.dialogue.ConversationDTO;
import com.secusociale.portail.service.dto.dialogue.QuestionDTO;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zalando.problem.Problem;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;

/**
 * REST controller for managing conversations.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ConversationResource {

    private final Logger log = LoggerFactory.getLogger(ConversationResource.class);
    private final ConversionQueryService conversionQueryService;
    private final ConversationService conversionService;
    private final UserRepository userRepository;

    @GetMapping(value = "/get-agence-conversations")
    public ResponseEntity<HashMap<String, Object>> getAgenceConversations(ConversationCriteria criteria, Pageable pageable) {
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
        Optional<String> loggedusername = getCurrentUserLogin();
        User user = loggedusername.map(userRepository::findByLogin).orElse(null);
        if (user == null) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();

        }
        StringFilter agenceFilter = new StringFilter();
        agenceFilter.setEquals(user.getAgence());
        criteria.setAgence(agenceFilter);
        Page<Conversation> page = conversionQueryService.findByCriteria(criteria, pageable);
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        List<Conversation> list = page.getContent();
        result.put("code", "200");
        result.put("list", list);
        result.put("pagination", pagination);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    @GetMapping(value = "/get-user-conversations")
    public ResponseEntity<HashMap<String, Object>> getUserConversations(ConversationCriteria criteria, Pageable pageable) {
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
        Optional<String> loggedusername = getCurrentUserLogin();
        User user = loggedusername.map(userRepository::findByLogin).orElse(null);
        if (user == null) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();

        }
        LongFilter userInitFilter = new LongFilter();
        userInitFilter.setEquals(user.getId());
        criteria.setUserInit(userInitFilter);
        Page<Conversation> page = conversionQueryService.findByCriteria(criteria, pageable);
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        List<Conversation> list = page.getContent();
        result.put("code", "200");
        result.put("list", list);
        result.put("pagination", pagination);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    @PostMapping(value = "/create-conversation")
    public ResponseEntity<HashMap<String, Object>> createConversation(@Valid @RequestBody ConversationDTO conversationDTO) {
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
        Optional<String> loggedusername = getCurrentUserLogin();
        User user = loggedusername.map(userRepository::findByLogin).orElse(null);
        if (user == null) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();

        }
        conversationDTO.setUserInit(user.getId());
        Conversation conversation = conversionService.saveConversation(conversationDTO);
        result.put("code", "200");
        result.put("data", conversation);
        return ResponseEntity.ok().body(result);

    }

    @PostMapping(value = "/post-question")
    public ResponseEntity<HashMap<String, Object>> postQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
        Conversation conversation = conversionService.postQuestion(questionDTO);
        result.put("code", "200");
        result.put("data", conversation);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(value = "/post-answer")
    public ResponseEntity<HashMap<String, Object>> postAnswer(@Valid @RequestBody AnswerDTO answerDTO) {
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
        Conversation conversation = conversionService.postAnswer(answerDTO);
        result.put("code", "200");
        result.put("data", conversation);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/close-conversation/{id}")
    public ResponseEntity<HashMap<String, Object>> closeConversation(@PathVariable Long id) {
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
        Conversation conversation = conversionService.closeConversation(id);
        result.put("code", "200");
        result.put("data", conversation);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/reopen-conversation/{id}")
    public ResponseEntity<HashMap<String, Object>> reopenConversation(@PathVariable Long id) {
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
        Conversation conversation = conversionService.reopenConversation(id);
        result.put("code", "200");
        result.put("data", conversation);
        return ResponseEntity.ok().body(result);
    }

}
