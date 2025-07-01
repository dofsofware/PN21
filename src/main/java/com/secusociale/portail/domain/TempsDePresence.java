package com.secusociale.portail.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.secusociale.portail.domain.enumeration.StatutTP;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "temps_de_presence")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TempsDePresence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "numero_unique")
    private Long numeroUnique;

    @Column(name = "numero_css")
    private Long numeroCss;

    @Column(name = "numero_ipres")
    private Long numeroIpres;

//    @JsonIgnoreProperties(value = { "contenuTP", "tempsDePresence" }, allowSetters = true)
//    @OneToOne(fetch = FetchType.LAZY)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private FileTP fileTP;

    public static TempsDePresence mapToTempsDePresence(HashMap<String, Object> tempsDePresence, HashMap<String, Object> fileTP, HashMap<String, Object> contenuTP, List<Map<String, String>> salarieTPs) {
        try {
            // Création de l'instance TempsDePresence
            TempsDePresence tp = new TempsDePresence();

            // Mapping des champs simples
            tp.setUserId(Long.valueOf(tempsDePresence.get("userId").toString()));
            tp.setNumeroUnique(Long.valueOf(tempsDePresence.get("numeroUnique").toString()));

            if (tempsDePresence.get("numeroCss") != null) {
                tp.setNumeroCss(Long.valueOf(tempsDePresence.get("numeroCss").toString()));
            }
            if (tempsDePresence.get("numeroIpres") != null) {
                tp.setNumeroIpres(Long.valueOf(tempsDePresence.get("numeroIpres").toString()));
            }

            // Création et mapping du FileTP
            FileTP file = new FileTP();
            file.setDateSoumission(LocalDate.parse(fileTP.get("dateSoumission").toString()));
            file.setStatut(StatutTP.valueOf(fileTP.get("statut").toString()));
            file.setDocLink(fileTP.get("docLink").toString());
            file.setFileEncoded(fileTP.get("fileEncoded").toString());

            // Création et mapping du ContenuTP
            ContenuTP contenu = new ContenuTP();
            contenu.setNumeroUnique(Long.valueOf(contenuTP.get("numeroUnique").toString()));
            contenu.setAnnee(Integer.valueOf(contenuTP.get("annee").toString()));
            contenu.setTrimestre(Integer.valueOf(contenuTP.get("trimestre").toString()));
            contenu.setStatut(StatutTP.valueOf(contenuTP.get("statut").toString()));
            contenu.setEmployeur(contenuTP.get("employeur").toString());

            if (contenuTP.get("ancienNumeroCss") != null) {
                contenu.setAncienNumeroCss(Long.valueOf(contenuTP.get("ancienNumeroCss").toString()));
            }
            if (contenuTP.get("ancienNumeroIpres") != null) {
                contenu.setAncienNumeroIpres(Long.valueOf(contenuTP.get("ancienNumeroIpres").toString()));
            }

            // Création et mapping des SalarieTP
            Set<SalarieTP> salarieTPSet = new HashSet<>();
            for (Map<String, String> salarieData : salarieTPs) {
                SalarieTP salarie = new SalarieTP();
                salarie.setNumeroAssureSocial(Long.valueOf(salarieData.get("numeroAssureSocial")));
                salarie.setNom(salarieData.get("nom"));
                salarie.setPrenom(salarieData.get("prenom"));
                salarie.setTypePiece(salarieData.get("typePiece"));
                salarie.setNumeroPiece(salarieData.get("numeroPiece"));
                salarie.setAge((int) Double.parseDouble(salarieData.get("age")));
                salarie.setNombreEnfantEligibre(parseDoubleToInt(salarieData.get("nombreEnfantEligibre")));
                salarie.setTempsDePresenceHeureMois1(parseDoubleToInt(salarieData.get("tempsDePresenceHeureMois1")));
                salarie.setTempsDePresenceHeureMois2(parseDoubleToInt(salarieData.get("tempsDePresenceHeureMois2")));
                salarie.setTempsDePresenceHeureMois3(parseDoubleToInt(salarieData.get("tempsDePresenceHeureMois3")));
                if (salarieData.get("observation") != null) {
                    salarie.setObservation(salarieData.get("observation"));
                }

                salarie.setContenuTP(contenu);
                salarieTPSet.add(salarie);
            }

            // Établissement des relations bidirectionnelles
            contenu.setSalarieTPs(salarieTPSet);
            file.setContenuTP(contenu);
            tp.setFileTP(file);

            return tp;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here

    private static int parseDoubleToInt(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0; // ou throw new IllegalArgumentException("La valeur ne peut pas être nulle");
        }
        try {
            return (int) Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Impossible de convertir la valeur '" + value + "' en nombre");
        }
    }

    public Long getId() {
        return this.id;
    }

    public TempsDePresence id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public TempsDePresence userId(Long userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNumeroUnique() {
        return this.numeroUnique;
    }

    public TempsDePresence numeroUnique(Long numeroUnique) {
        this.setNumeroUnique(numeroUnique);
        return this;
    }

    public void setNumeroUnique(Long numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public Long getNumeroCss() {
        return this.numeroCss;
    }

    public TempsDePresence numeroCss(Long numeroCss) {
        this.setNumeroCss(numeroCss);
        return this;
    }

    public void setNumeroCss(Long numeroCss) {
        this.numeroCss = numeroCss;
    }

    public Long getNumeroIpres() {
        return this.numeroIpres;
    }

    public TempsDePresence numeroIpres(Long numeroIpres) {
        this.setNumeroIpres(numeroIpres);
        return this;
    }

    public void setNumeroIpres(Long numeroIpres) {
        this.numeroIpres = numeroIpres;
    }

    public FileTP getFileTP() {
        return this.fileTP;
    }

    public void setFileTP(FileTP fileTP) {
        this.fileTP = fileTP;
    }

    public TempsDePresence fileTP(FileTP fileTP) {
        this.setFileTP(fileTP);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TempsDePresence)) {
            return false;
        }
        return getId() != null && getId().equals(((TempsDePresence) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }


    // prettier-ignore
    @Override
    public String toString() {
        return "TempsDePresence{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", numeroUnique=" + getNumeroUnique() +
            ", numeroCss=" + getNumeroCss() +
            ", numeroIpres=" + getNumeroIpres() +
            "}";
    }
}
