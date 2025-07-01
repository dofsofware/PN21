package com.secusociale.portail.domain.consultation;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

/**
 * Un Employeur en mode consultation.
 */
@Entity
@Table(name = "employeur_consultation")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmployeurConsultation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numero_unique", nullable = false, unique = true)
    private String numeroUnique;

    @NotNull
    @Column(name = "numero_cni", nullable = false)
    private String numeroCni;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "validate_at")
    private Instant validateAt;
}
