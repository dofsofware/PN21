package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "journal_immatriculation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JournalImmatriculation implements Serializable {

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

    @ManyToOne
    @JoinColumn(name = "immatriculation_id")
    private NouvelleImmatriculation immatriculation;

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

    public NouvelleImmatriculation getImmatriculation() { return immatriculation; }
    public void setImmatriculation(NouvelleImmatriculation immatriculation) { this.immatriculation = immatriculation; }
}
