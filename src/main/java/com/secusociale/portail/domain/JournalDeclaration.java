package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "journal_declaration")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JournalDeclaration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_action")
    private Instant dateAction;

    @Column(name = "detail")
    private String detail;

    @Column(name = "type_journal")
    private String typeJournal;

    @Column(name = "utilisateur_id")
    private Long utilisateurId;

    @Column(name = "utilisateur_login")
    private String utilisateurLogin;

    @Column(name = "utilisateur_prenom")
    private String utilisateurPrenom;

    @Column(name = "utilisateur_nom")
    private String utilisateurNom;

    @NotNull
    @Column(name = "id_declaration", nullable = false)
    private Long idDeclaration;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Instant getDateAction() { return dateAction; }
    public void setDateAction(Instant dateAction) { this.dateAction = dateAction; }

    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }

    public String getTypeJournal() { return typeJournal; }
    public void setTypeJournal(String typeJournal) { this.typeJournal = typeJournal; }

    public Long getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(Long utilisateurId) { this.utilisateurId = utilisateurId; }

    public String getUtilisateurLogin() { return utilisateurLogin; }
    public void setUtilisateurLogin(String utilisateurLogin) { this.utilisateurLogin = utilisateurLogin; }

    public String getUtilisateurPrenom() { return utilisateurPrenom; }
    public void setUtilisateurPrenom(String utilisateurPrenom) { this.utilisateurPrenom = utilisateurPrenom; }

    public String getUtilisateurNom() { return utilisateurNom; }
    public void setUtilisateurNom(String utilisateurNom) { this.utilisateurNom = utilisateurNom; }


    public Long getIdDeclaration() {
        return idDeclaration;
    }

    public void setIdDeclaration(Long idDeclaration) {
        this.idDeclaration = idDeclaration;
    }
}
