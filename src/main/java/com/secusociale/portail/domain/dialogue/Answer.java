package com.secusociale.portail.domain.dialogue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A dialogue.
 */
@Entity
@Table(name = "answers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    private Instant createdDate;
    @Column(name = "response_date")
    private Instant responseDate;
    @Lob
    @Column(nullable = false)
    private String contenu;

    @ManyToOne
    @JsonIgnore
    private Question question;


}
