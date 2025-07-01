package com.secusociale.portail.domain;

import com.secusociale.portail.domain.enumeration.EtatSynchronisation;
import com.secusociale.portail.domain.enumeration.StatutSynchronisation;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * A FactureDNS.
 */
@Entity
@Table(name = "facturedns")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FactureDNS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numero_facture", nullable = false, unique = true)
    private String numeroFacture;

    @NotNull
    @Column(name = "id_declaration", nullable = false)
    private Long idDeclaration;

    @Column(name = "numero_unique")
    private String numeroUnique;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "agence_ipres")
    private String agenceIpres;

    @Column(name = "agence_css")
    private String agenceCss;

    @Column(name = "debut_periode")
    private Instant debutPeriode;

    @Column(name = "fin_periode")
    private Instant finPeriode;

    @Column(name = "date_reception")
    private Instant dateReception;

    @Column(name = "vieillesse_rg")
    private Double vieillesseRG;

    @Column(name = "vieillesse_rc")
    private Double vieillesseRC;

    @Column(name = "taux_rg")
    private Float tauxRG;

    @Column(name = "taux_rc")
    private Float tauxRC;

    @Column(name = "mt_vieillesse_rg")
    private Double mtVieillesseRG;

    @Column(name = "mt_vieillesse_rc")
    private Double mtVieillesseRC;

    @Column(name = "mt_majoration_css")
    private Double mtMajorationCss;

    @Column(name = "prestation_fam")
    private Double prestationFam;

    @Column(name = "taux_prestation_fam")
    private Float tauxPrestationFam;

    @Column(name = "mt_prestation_fam")
    private Double mtPrestationFam;

    @Column(name = "atmp")
    private Double atmp;

    @Column(name = "taux_atmp")
    private Float tauxATMP;

    @Column(name = "mt_atmp")
    private Double mtAtmp;

    @Column(name = "total_ipres")
    private Double totalIpres;

    @Column(name = "total_css")
    private Double totalCss;

    @Column(name = "total_a_payer")
    private Double totalAPayer;

    @Lob
    @Column(name = "extra_infos")
    private String extraInfos;

    @Column(name = "mt_majoration_ipres")
    private Double mtMajorationIpres;

    @Column(name = "statut")
    private String statut;

    @Column(name = "echeance")
    private Instant echeance;

    @Column(name = "salaire_declare")
    private BigDecimal salaireDeclare;

    @Column(name = "date_synchronisation")
    private Timestamp dateSynchronisation;

    @Lob
    @Column(name = "rapport_synchronisation")
    private String rapportSynchronisation;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut_synchronisation")
    private StatutSynchronisation statutSynchronisation;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_synchronisation")
    private EtatSynchronisation etatSynchronisation;

    @Column(name = "nombre_d_insertion")
    private Integer nombreInsertion;

    @Lob
    @Column(name = "infos_synchronisation")
    private String infosSynchronisation;


    public void FactureDNSRes(FactureDNSSupprime factureDNS) {
        this.idDeclaration = factureDNS.getIdDeclaration();
        this.numeroFacture = factureDNS.getNumeroFacture();
        this.numeroUnique = factureDNS.getNumeroUnique();
        this.statut = factureDNS.getStatut();
        this.raisonSociale = factureDNS.getRaisonSociale();
        this.adresse = factureDNS.getAdresse();
        this.agenceIpres = factureDNS.getAgenceIpres();
        this.agenceCss = factureDNS.getAgenceCss();
        this.debutPeriode = factureDNS.getDebutPeriode();
        this.finPeriode = factureDNS.getFinPeriode();
        this.dateReception = factureDNS.getDateReception();
        this.vieillesseRG = factureDNS.getVieillesseRG();
        this.vieillesseRC = factureDNS.getVieillesseRC();
        this.tauxRG = factureDNS.getTauxRG();
        this.tauxRC = factureDNS.getTauxRC();
        this.mtVieillesseRG = factureDNS.getMtVieillesseRG();
        this.mtVieillesseRC = factureDNS.getMtVieillesseRC();
        this.mtMajorationCss = factureDNS.getMtMajorationCss();
        this.prestationFam = factureDNS.getPrestationFam();
        this.tauxPrestationFam = factureDNS.getTauxPrestationFam();
        this.mtPrestationFam = factureDNS.getMtPrestationFam();
        this.atmp = factureDNS.getAtmp();
        this.tauxATMP = factureDNS.getTauxATMP();
        this.mtAtmp = factureDNS.getMtAtmp();
        this.totalIpres = factureDNS.getTotalIpres();
        this.totalCss = factureDNS.getTotalCss();
        this.totalAPayer = factureDNS.getTotalAPayer();
        this.mtMajorationIpres = factureDNS.getMtMajorationIpres();
        this.echeance = factureDNS.getEcheance();
        this.salaireDeclare = factureDNS.getSalaireDeclare();
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public FactureDNS numeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
        return this;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public Long getIdDeclaration() {
        return idDeclaration;
    }

    public FactureDNS idDeclaration(Long idDeclaration) {
        this.idDeclaration = idDeclaration;
        return this;
    }

    public void setIdDeclaration(Long idDeclaration) {
        this.idDeclaration = idDeclaration;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public FactureDNS numeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
        return this;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public FactureDNS raisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getAdresse() {
        return adresse;
    }

    public FactureDNS adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAgenceIpres() {
        return agenceIpres;
    }

    public FactureDNS agenceIpres(String agenceIpres) {
        this.agenceIpres = agenceIpres;
        return this;
    }

    public void setAgenceIpres(String agenceIpres) {
        this.agenceIpres = agenceIpres;
    }

    public String getAgenceCss() {
        return agenceCss;
    }

    public FactureDNS agenceCss(String agenceCss) {
        this.agenceCss = agenceCss;
        return this;
    }

    public void setAgenceCss(String agenceCss) {
        this.agenceCss = agenceCss;
    }

    public Instant getDebutPeriode() {
        return debutPeriode;
    }

    public FactureDNS debutPeriode(Instant debutPeriode) {
        this.debutPeriode = debutPeriode;
        return this;
    }

    public void setDebutPeriode(Instant debutPeriode) {
        this.debutPeriode = debutPeriode;
    }

    public Instant getFinPeriode() {
        return finPeriode;
    }

    public FactureDNS finPeriode(Instant finPeriode) {
        this.finPeriode = finPeriode;
        return this;
    }

    public void setFinPeriode(Instant finPeriode) {
        this.finPeriode = finPeriode;
    }

    public Instant getDateReception() {
        return dateReception;
    }

    public FactureDNS dateReception(Instant dateReception) {
        this.dateReception = dateReception;
        return this;
    }

    public void setDateReception(Instant dateReception) {
        this.dateReception = dateReception;
    }

    public Double getVieillesseRG() {
        return vieillesseRG;
    }

    public FactureDNS vieillesseRG(Double vieillesseRG) {
        this.vieillesseRG = vieillesseRG;
        return this;
    }

    public void setVieillesseRG(Double vieillesseRG) {
        this.vieillesseRG = vieillesseRG;
    }

    public Double getVieillesseRC() {
        return vieillesseRC;
    }

    public FactureDNS vieillesseRC(Double vieillesseRC) {
        this.vieillesseRC = vieillesseRC;
        return this;
    }

    public void setVieillesseRC(Double vieillesseRC) {
        this.vieillesseRC = vieillesseRC;
    }

    public Float getTauxRG() {
        return tauxRG;
    }

    public FactureDNS tauxRG(Float tauxRG) {
        this.tauxRG = tauxRG;
        return this;
    }

    public void setTauxRG(Float tauxRG) {
        this.tauxRG = tauxRG;
    }

    public Float getTauxRC() {
        return tauxRC;
    }

    public FactureDNS tauxRC(Float tauxRC) {
        this.tauxRC = tauxRC;
        return this;
    }

    public void setTauxRC(Float tauxRC) {
        this.tauxRC = tauxRC;
    }

    public Double getMtVieillesseRG() {
        return mtVieillesseRG;
    }

    public FactureDNS mtVieillesseRG(Double mtVieillesseRG) {
        this.mtVieillesseRG = mtVieillesseRG;
        return this;
    }

    public void setMtVieillesseRG(Double mtVieillesseRG) {
        this.mtVieillesseRG = mtVieillesseRG;
    }

    public Double getMtVieillesseRC() {
        return mtVieillesseRC;
    }

    public FactureDNS mtVieillesseRC(Double mtVieillesseRC) {
        this.mtVieillesseRC = mtVieillesseRC;
        return this;
    }

    public void setMtVieillesseRC(Double mtVieillesseRC) {
        this.mtVieillesseRC = mtVieillesseRC;
    }

    public Double getMtMajorationCss() {
        return mtMajorationCss;
    }

    public FactureDNS mtMajorationCss(Double mtMajorationCss) {
        this.mtMajorationCss = mtMajorationCss;
        return this;
    }

    public void setMtMajorationCss(Double mtMajorationCss) {
        this.mtMajorationCss = mtMajorationCss;
    }

    public Double getPrestationFam() {
        return prestationFam;
    }

    public FactureDNS prestationFam(Double prestationFam) {
        this.prestationFam = prestationFam;
        return this;
    }

    public void setPrestationFam(Double prestationFam) {
        this.prestationFam = prestationFam;
    }

    public Float getTauxPrestationFam() {
        return tauxPrestationFam;
    }

    public FactureDNS tauxPrestationFam(Float tauxPrestationFam) {
        this.tauxPrestationFam = tauxPrestationFam;
        return this;
    }

    public void setTauxPrestationFam(Float tauxPrestationFam) {
        this.tauxPrestationFam = tauxPrestationFam;
    }

    public Double getMtPrestationFam() {
        return mtPrestationFam;
    }

    public FactureDNS mtPrestationFam(Double mtPrestationFam) {
        this.mtPrestationFam = mtPrestationFam;
        return this;
    }

    public void setMtPrestationFam(Double mtPrestationFam) {
        this.mtPrestationFam = mtPrestationFam;
    }

    public Double getAtmp() {
        return atmp;
    }

    public FactureDNS atmp(Double atmp) {
        this.atmp = atmp;
        return this;
    }

    public void setAtmp(Double atmp) {
        this.atmp = atmp;
    }

    public Float getTauxATMP() {
        return tauxATMP;
    }

    public FactureDNS tauxATMP(Float tauxATMP) {
        this.tauxATMP = tauxATMP;
        return this;
    }

    public void setTauxATMP(Float tauxATMP) {
        this.tauxATMP = tauxATMP;
    }

    public Double getMtAtmp() {
        return mtAtmp;
    }

    public FactureDNS mtAtmp(Double mtAtmp) {
        this.mtAtmp = mtAtmp;
        return this;
    }

    public void setMtAtmp(Double mtAtmp) {
        this.mtAtmp = mtAtmp;
    }

    public Double getTotalIpres() {
        return totalIpres;
    }

    public FactureDNS totalIpres(Double totalIpres) {
        this.totalIpres = totalIpres;
        return this;
    }

    public void setTotalIpres(Double totalIpres) {
        this.totalIpres = totalIpres;
    }

    public Double getTotalCss() {
        return totalCss;
    }

    public FactureDNS totalCss(Double totalCss) {
        this.totalCss = totalCss;
        return this;
    }

    public void setTotalCss(Double totalCss) {
        this.totalCss = totalCss;
    }

    public Double getTotalAPayer() {
        return totalAPayer;
    }

    public FactureDNS totalAPayer(Double totalAPayer) {
        this.totalAPayer = totalAPayer;
        return this;
    }

    public void setTotalAPayer(Double totalAPayer) {
        this.totalAPayer = totalAPayer;
    }

    public String getExtraInfos() {
        return extraInfos;
    }

    public FactureDNS extraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
        return this;
    }

    public void setExtraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
    }

    public Double getMtMajorationIpres() {
        return mtMajorationIpres;
    }

    public FactureDNS mtMajorationIpres(Double mtMajorationIpres) {
        this.mtMajorationIpres = mtMajorationIpres;
        return this;
    }

    public void setMtMajorationIpres(Double mtMajorationIpres) {
        this.mtMajorationIpres = mtMajorationIpres;
    }

    public String getStatut() {
        return statut;
    }

    public FactureDNS statut(String statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Instant getEcheance() {
        return echeance;
    }

    public FactureDNS echeance(Instant echeance) {
        this.echeance = echeance;
        return this;
    }

    public BigDecimal getSalaireDeclare() {
        return salaireDeclare;
    }

    public void setSalaireDeclare(BigDecimal salaireDeclare) {
        this.salaireDeclare = salaireDeclare;
    }

    public void setEcheance(Instant echeance) {
        this.echeance = echeance;
    }

    public Timestamp getDateSynchronisation() {
        return dateSynchronisation;
    }

    public void setDateSynchronisation(Timestamp dateSynchronisation) {
        this.dateSynchronisation = dateSynchronisation;
    }

    public String getRapportSynchronisation() {
        return rapportSynchronisation;
    }

    public void setRapportSynchronisation(String rapportSynchronisation) {
        this.rapportSynchronisation = rapportSynchronisation;
    }

    public StatutSynchronisation getStatutSynchronisation() {
        return statutSynchronisation;
    }

    public void setStatutSynchronisation(StatutSynchronisation statutSynchronisation) {
        this.statutSynchronisation = statutSynchronisation;
    }

    public EtatSynchronisation getEtatSynchronisation() {
        return etatSynchronisation;
    }

    public void setEtatSynchronisation(EtatSynchronisation etatSynchronisation) {
        this.etatSynchronisation = etatSynchronisation;
    }

    public Integer getNombreInsertion() {
        return nombreInsertion;
    }

    public void setNombreInsertion(Integer nombreInsertion) {
        this.nombreInsertion = nombreInsertion;
    }

    public String getInfosSynchronisation() {
        return infosSynchronisation;
    }

    public void setInfosSynchronisation(String infosSynchronisation) {
        this.infosSynchronisation = infosSynchronisation;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FactureDNS)) {
            return false;
        }
        return id != null && id.equals(((FactureDNS) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FactureDNS{" +
            "id=" + id +
            ", numeroFacture='" + numeroFacture + '\'' +
            ", idDeclaration=" + idDeclaration +
            ", numeroUnique='" + numeroUnique + '\'' +
            ", raisonSociale='" + raisonSociale + '\'' +
            ", adresse='" + adresse + '\'' +
            ", agenceIpres='" + agenceIpres + '\'' +
            ", agenceCss='" + agenceCss + '\'' +
            ", debutPeriode=" + debutPeriode +
            ", finPeriode=" + finPeriode +
            ", dateReception=" + dateReception +
            ", vieillesseRG=" + vieillesseRG +
            ", vieillesseRC=" + vieillesseRC +
            ", tauxRG=" + tauxRG +
            ", tauxRC=" + tauxRC +
            ", mtVieillesseRG=" + mtVieillesseRG +
            ", mtVieillesseRC=" + mtVieillesseRC +
            ", mtMajorationCss=" + mtMajorationCss +
            ", prestationFam=" + prestationFam +
            ", tauxPrestationFam=" + tauxPrestationFam +
            ", mtPrestationFam=" + mtPrestationFam +
            ", atmp=" + atmp +
            ", tauxATMP=" + tauxATMP +
            ", mtAtmp=" + mtAtmp +
            ", totalIpres=" + totalIpres +
            ", totalCss=" + totalCss +
            ", totalAPayer=" + totalAPayer +
            ", extraInfos='" + extraInfos + '\'' +
            ", mtMajorationIpres=" + mtMajorationIpres +
            ", statut='" + statut + '\'' +
            ", echeance=" + echeance +
            ", salaireDeclare=" + salaireDeclare +
            ", dateSynchronisation=" + dateSynchronisation +
            ", rapportSynchronisation='" + rapportSynchronisation + '\'' +
            ", statutSynchronisation=" + statutSynchronisation +
            ", etatSynchronisation=" + etatSynchronisation +
            ", nombreInsertion=" + nombreInsertion +
            ", infosSynchronisation='" + infosSynchronisation + '\'' +
            '}';
    }
}
