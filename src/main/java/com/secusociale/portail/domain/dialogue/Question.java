package com.secusociale.portail.domain.dialogue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * A dialogue.
 */
@Entity
@Table(name = "questions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Question implements Serializable {

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

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<Answer> answers = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Conversation conversation;

}
