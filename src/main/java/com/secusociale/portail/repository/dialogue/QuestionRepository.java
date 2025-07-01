package com.secusociale.portail.repository.dialogue;

import com.secusociale.portail.domain.dialogue.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
