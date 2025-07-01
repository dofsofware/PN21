package com.secusociale.portail.domain.dialogue;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * A dialogue.
 */
@Entity
@Table(name = "conversations")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Conversation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    private Instant createdDate;
    @Column(name = "closed_date")
    private Instant closedDate;
    @Size(max = 20)
    @Column(name = "status")
    @Builder.Default
    private String status = "INIT";
    private String title;
    @Column(nullable = false)
    private String agence;
    @Column(nullable = false)
    private Long userInit;

    @OneToMany(mappedBy = "conversation", fetch = FetchType.EAGER)
    private List<Question> questions = new ArrayList<>();


}
