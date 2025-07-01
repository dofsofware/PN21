package com.secusociale.portail.service.dto.dialogue;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDTO implements Serializable {
    private Long id;
    private Instant createdDate;
    private Instant responseDate;
    private String contenu;
    private Long question;

}
