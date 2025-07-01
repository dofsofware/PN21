package com.secusociale.portail.service.dto.dialogue;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConversationDTO implements Serializable {
    private Long id;

    @Builder.Default
    private String status = "INIT";

    private String title;

    private String agence;

    private Long userInit;
}
