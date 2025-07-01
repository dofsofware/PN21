package com.secusociale.portail.service.dto.dialogue;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO implements Serializable {
    private Long id;

    private String contenu;

    private Long conversation;

}
