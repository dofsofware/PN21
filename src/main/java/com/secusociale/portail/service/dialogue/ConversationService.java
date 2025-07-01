package com.secusociale.portail.service.dialogue;

import com.secusociale.portail.domain.dialogue.Answer;
import com.secusociale.portail.domain.dialogue.Conversation;
import com.secusociale.portail.domain.dialogue.Question;
import com.secusociale.portail.repository.dialogue.AnswerRepository;
import com.secusociale.portail.repository.dialogue.ConversationRepository;
import com.secusociale.portail.repository.dialogue.QuestionRepository;
import com.secusociale.portail.service.dto.dialogue.AnswerDTO;
import com.secusociale.portail.service.dto.dialogue.ConversationDTO;
import com.secusociale.portail.service.dto.dialogue.ConversationStatuses;
import com.secusociale.portail.service.dto.dialogue.QuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.time.Instant;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    /**
     * Save a Conversation.
     *
     * @param conversationDTO the entity to save.
     * @return the persisted entity.
     */
    public Conversation saveConversation(ConversationDTO conversationDTO) {
        Conversation conversation = Conversation.builder()
            .agence(conversationDTO.getAgence())
            .title(conversationDTO.getTitle())
            .userInit(conversationDTO.getUserInit())
            .status(ConversationStatuses.INIT)
            .createdDate(Instant.now())
            .build();
        return conversationRepository.save(conversation);
    }

    /**
     * Reopen a Conversation.
     *
     * @param conservationId the id of the conversation to reopen.
     * @return the conservation entity.
     */
    public Conversation reopenConversation(Long conservationId) {
        Optional<Conversation> optionalConversation = conversationRepository.findById(conservationId);
        if (optionalConversation.isPresent()) {
            Conversation conversation = optionalConversation.get();
            conversation.setStatus(ConversationStatuses.OPEN);
            conversation.setClosedDate(null);
            return conversationRepository.save(conversation);
        }
        return null;
    }

    /**
     * close a Conversation.
     *
     * @param conservationId the id of the conversation to close.
     * @return the conservation entity.
     */
    public Conversation closeConversation(Long conservationId) {
        Optional<Conversation> optionalConversation = conversationRepository.findById(conservationId);
        if (optionalConversation.isPresent()) {
            Conversation conversation = optionalConversation.get();
            conversation.setStatus(ConversationStatuses.CLOSED);
            conversation.setClosedDate(Instant.now());
            return conversationRepository.save(conversation);
        }
        return null;
    }

    /**
     * Post a Question.
     *
     * @param questionDTO the entity to save.
     * @return the persisted entity.
     */
    public Conversation postQuestion(QuestionDTO questionDTO) {
        Optional<Conversation> optionalConversation = conversationRepository.findById(questionDTO.getConversation());
        if (!optionalConversation.isPresent()) {
            throw Problem.builder()
                .withStatus(Status.BAD_REQUEST)
                .withDetail("Conversation introuvable")
                .withTitle("Erreur")
                .build();
        }
        Conversation conversation = optionalConversation.get();
        if (conversation.getStatus().equalsIgnoreCase(ConversationStatuses.CLOSED)) {
            throw Problem.builder()
                .withStatus(Status.BAD_REQUEST)
                .withDetail("Conversation fermee")
                .withTitle("Erreur")
                .build();
        }
        Question question = Question.builder()
            .contenu(questionDTO.getContenu())
            .createdDate(Instant.now())
            .conversation(conversation)
            .build();

        conversation = questionRepository.save(question).getConversation();
        conversation.setStatus(ConversationStatuses.OPEN);

        return conversationRepository.save(conversation);
    }

    /**
     * Post an Answer.
     *
     * @param answerDTO the entity to save.
     * @return the persisted entity.
     */
    public Conversation postAnswer(AnswerDTO answerDTO) {
        Optional<Question> optionalQuestion = questionRepository.findById(answerDTO.getQuestion());
        if (!optionalQuestion.isPresent()) {
            throw Problem.builder()
                .withStatus(Status.BAD_REQUEST)
                .withDetail("Question introuvable")
                .withTitle("Erreur")
                .build();
        }
        Question question = optionalQuestion.get();
        Conversation conversation = question.getConversation();

        if (conversation.getStatus().equalsIgnoreCase(ConversationStatuses.CLOSED)) {
            throw Problem.builder()
                .withStatus(Status.BAD_REQUEST)
                .withDetail("Conversation fermee")
                .withTitle("Erreur")
                .build();
        }
        Answer answer = Answer.builder()
            .contenu(answerDTO.getContenu())
            .createdDate(Instant.now())
            .responseDate(Instant.now())
            .question(question)
            .build();

        answerRepository.save(answer);
        return conversationRepository.save(conversation);
    }
}
