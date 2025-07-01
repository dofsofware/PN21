package com.secusociale.portail.domain;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A ImmatriculationRecuperee.
 */
@Entity
@Table(name = "immatriculation_recuperee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ImmatriculationRecuperee implements Serializable {

    private static final long serialVersionUID = 1L;

    public ImmatriculationRecuperee() {
    }

    public ImmatriculationRecuperee(OldImmatriculation oldImmatriculation) {
//        System.out.println("oldImmatriculation ==> " + oldImmatriculation.toString());
        setCreatedAt(oldImmatriculation.getDate());
        setNinea(oldImmatriculation.getNineaFile());
        setTypeIdentifiant(oldImmatriculation.getTypeIdentifiant());
        setNumeroIdentifiant(oldImmatriculation.getNumeroIdentifiant());
        setNumeroUnique(oldImmatriculation.getNumeroUnique());
        setRaisonSociale(oldImmatriculation.getNumeroRC());
        setAgenceCss(oldImmatriculation.getAgenceCSS());
        setAgenceIpres(oldImmatriculation.getAgenceIPRES());
        setDateTraitement(oldImmatriculation.getDateTraitement());
        setDate(oldImmatriculation.getDate());
        setId(oldImmatriculation.getId());
        setUserId(oldImmatriculation.getUserId());
        setAgentId(oldImmatriculation.getAgentId());
        setPayload(oldImmatriculation.getExtrasInfo());
        setExtrasInfo(oldImmatriculation.getExtrasInfo());
        setMoyenConfirmation(oldImmatriculation.getMoyenConfirmation());
        setMotif(oldImmatriculation.getMotif());
        setMandatFile(oldImmatriculation.getMandatFile());
        setStatus(oldImmatriculation.getStatus());
        JSONObject extrasInfoPayload = oldImmatriculation.getExtrasInfoPayload();
        if (extrasInfoPayload != null) {
            try {
                JSONObject input = extrasInfoPayload.getJSONObject("input");
                JSONObject output = extrasInfoPayload.getJSONObject("output");
                JSONObject additional = output.getJSONObject("additional");
                JSONArray identifiants = output.getJSONArray("identifiants");

                JSONObject aniObject = findObjectByProperty(identifiants, "typeIdentifiant", "ANI");
                JSONObject ancObject = findObjectByProperty(identifiants, "typeIdentifiant", "ANC");
                JSONObject sciObject = findObjectByProperty(identifiants, "typeIdentifiant", "SCI");
                if (aniObject != null) {
                    setNumeroIpres(aniObject.getString("numeroIdentifiant"));
                }

                if (ancObject != null) {
                    setNumeroCss(ancObject.getString("numeroIdentifiant"));
                }
                if (sciObject != null) {
                    setNinea(sciObject.getString("numeroIdentifiant"));
                }

                setRaisonSociale(additional.getString("employerName"));
                if (StringUtils.isEmpty(getNumeroUnique())) {
                    setNumeroUnique(input.getString("numeroUnique"));
                }
                setTauxAt(additional.getString("atRate"));
                setRegistreCommerce(additional.getString("tradeRegisterNumber"));
                setZoneCss(additional.getString("zoneCss"));
                setZoneIpres(additional.getString("zoneIpres"));
                setSectorCss(additional.getString("secteurCss"));
                setSectorIpres(additional.getString("secteurIpres"));
                setAgenceIpres(additional.getString("agenceIpres"));
                setAgenceCss(additional.getString("agenceCss"));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private static JSONObject findObjectByProperty(JSONArray jsonArray, String propertyName, String propertyValue) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (propertyValue.equals(jsonObject.optString(propertyName))) {
                return jsonObject;
            }
        }
        return null; // retourne null si aucun objet n'est trouvé
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "moyen_confirmation")
    private String moyenConfirmation;

    @Column(name = "status")
    private String status;

    @Column(name = "statusdesc")
    private String statusdesc;

    @Lob
    @Column(name = "payload")
    private String payload;

    @Lob
    @Column(name = "extras_info")
    private String extrasInfo;

    @Column(name = "type_identifiant")
    private String typeIdentifiant;

    @Column(name = "numero_identifiant")
    private String numeroIdentifiant;

    @Column(name = "ninea")
    private String ninea;

    @Column(name = "numero_unique")
    private String numeroUnique;

    @Column(name = "numero_css")
    private String numeroCss;

    @Column(name = "numero_ipres")
    private String numeroIpres;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "date_traitement")
    private Instant dateTraitement;

    @Column(name = "date")
    private Instant date;

    @Lob
    @Column(name = "zone_css")
    private String zoneCss;

    @Lob
    @Column(name = "sector_css")
    private String sectorCss;

    @Column(name = "zone_ipres")
    private String zoneIpres;

    @Lob
    @Column(name = "sector_ipres")
    private String sectorIpres;

    @Column(name = "agence_css")
    private String agenceCss;

    @Column(name = "agence_ipres")
    private String agenceIpres;

    @Column(name = "taux_at")
    private String tauxAt;

    @Lob
    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Lob
    @Column(name = "mandat_file")
    private String mandatFile;

    @Lob
    @Column(name = "motif")
    private String motif;

    @Lob
    @Column(name = "rc_file")
    private String rcFile;

    @Lob
    @Column(name = "ninea_file")
    private String nineaFile;

    @Column(name = "registre_commerce")
    private String registreCommerce;

    @Column(name = "manager_id")
    private Long manager;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoyenConfirmation() {
        return moyenConfirmation;
    }

    public ImmatriculationRecuperee moyenConfirmation(String moyenConfirmation) {
        this.moyenConfirmation = moyenConfirmation;
        return this;
    }

    public void setMoyenConfirmation(String moyenConfirmation) {
        this.moyenConfirmation = moyenConfirmation;
    }

    public String getStatus() {
        return status;
    }

    public ImmatriculationRecuperee status(String status) {
        this.status = status;
        return this;
    }

    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager_id) {
        this.manager = manager_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusdesc() {
        return statusdesc;
    }

    public ImmatriculationRecuperee statusdesc(String statusdesc) {
        this.statusdesc = statusdesc;
        return this;
    }

    public void setStatusdesc(String statusdesc) {
        this.statusdesc = statusdesc;
    }

    public String getPayload() {
        return payload;
    }

    public ImmatriculationRecuperee payload(String payload) {
        this.payload = payload;
        return this;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getExtrasInfo() {
        return extrasInfo;
    }

    public ImmatriculationRecuperee extrasInfo(String extrasInfo) {
        this.extrasInfo = extrasInfo;
        return this;
    }

    public void setExtrasInfo(String extrasInfo) {
        this.extrasInfo = extrasInfo;
    }

    public String getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public ImmatriculationRecuperee typeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
        return this;
    }

    public void setTypeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
    }

    public String getNumeroIdentifiant() {
        return numeroIdentifiant;
    }

    public ImmatriculationRecuperee numeroIdentifiant(String numeroIdentifiant) {
        this.numeroIdentifiant = numeroIdentifiant;
        return this;
    }

    public void setNumeroIdentifiant(String numeroIdentifiant) {
        this.numeroIdentifiant = numeroIdentifiant;
    }

    public String getNinea() {
        return ninea;
    }

    public ImmatriculationRecuperee ninea(String ninea) {
        this.ninea = ninea;
        return this;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public ImmatriculationRecuperee numeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
        return this;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getNumeroCss() {
        return numeroCss;
    }

    public ImmatriculationRecuperee numeroCss(String numeroCss) {
        this.numeroCss = numeroCss;
        return this;
    }

    public void setNumeroCss(String numeroCss) {
        this.numeroCss = numeroCss;
    }

    public String getNumeroIpres() {
        return numeroIpres;
    }

    public ImmatriculationRecuperee numeroIpres(String numeroIpres) {
        this.numeroIpres = numeroIpres;
        return this;
    }

    public void setNumeroIpres(String numeroIpres) {
        this.numeroIpres = numeroIpres;
    }

    public Long getUserId() {
        return userId;
    }

    public ImmatriculationRecuperee userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public ImmatriculationRecuperee agentId(Long agentId) {
        this.agentId = agentId;
        return this;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public ImmatriculationRecuperee createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getDateTraitement() {
        return dateTraitement;
    }

    public ImmatriculationRecuperee dateTraitement(Instant dateTraitement) {
        this.dateTraitement = dateTraitement;
        return this;
    }

    public void setDateTraitement(Instant dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public Instant getDate() {
        return date;
    }

    public ImmatriculationRecuperee date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getZoneCss() {
        return zoneCss;
    }

    public ImmatriculationRecuperee zoneCss(String zoneCss) {
        this.zoneCss = zoneCss;
        return this;
    }

    public void setZoneCss(String zoneCss) {
        this.zoneCss = zoneCss;
    }

    public String getSectorCss() {
        return sectorCss;
    }

    public ImmatriculationRecuperee sectorCss(String sectorCss) {
        this.sectorCss = sectorCss;
        return this;
    }

    public void setSectorCss(String sectorCss) {
        this.sectorCss = sectorCss;
    }

    public String getZoneIpres() {
        return zoneIpres;
    }

    public ImmatriculationRecuperee zoneIpres(String zoneIpres) {
        this.zoneIpres = zoneIpres;
        return this;
    }

    public void setZoneIpres(String zoneIpres) {
        this.zoneIpres = zoneIpres;
    }

    public String getSectorIpres() {
        return sectorIpres;
    }

    public ImmatriculationRecuperee sectorIpres(String sectorIpres) {
        this.sectorIpres = sectorIpres;
        return this;
    }

    public void setSectorIpres(String sectorIpres) {
        this.sectorIpres = sectorIpres;
    }

    public String getAgenceCss() {
        return agenceCss;
    }

    public ImmatriculationRecuperee agenceCss(String agenceCss) {
        this.agenceCss = agenceCss;
        return this;
    }

    public void setAgenceCss(String agenceCss) {
        this.agenceCss = agenceCss;
    }

    public String getAgenceIpres() {
        return agenceIpres;
    }

    public ImmatriculationRecuperee agenceIpres(String agenceIpres) {
        this.agenceIpres = agenceIpres;
        return this;
    }

    public void setAgenceIpres(String agenceIpres) {
        this.agenceIpres = agenceIpres;
    }

    public String getTauxAt() {
        return tauxAt;
    }

    public ImmatriculationRecuperee tauxAt(String tauxAt) {
        this.tauxAt = tauxAt;
        return this;
    }

    public void setTauxAt(String tauxAt) {
        this.tauxAt = tauxAt;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public ImmatriculationRecuperee raisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getMandatFile() {
        return mandatFile;
    }

    public ImmatriculationRecuperee mandatFile(String mandatFile) {
        this.mandatFile = mandatFile;
        return this;
    }

    public void setMandatFile(String mandatFile) {
        this.mandatFile = mandatFile;
    }

    public String getMotif() {
        return motif;
    }

    public ImmatriculationRecuperee motif(String motif) {
        this.motif = motif;
        return this;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getRcFile() {
        return rcFile;
    }

    public ImmatriculationRecuperee rcFile(String rcFile) {
        this.rcFile = rcFile;
        return this;
    }

    public void setRcFile(String rcFile) {
        this.rcFile = rcFile;
    }

    public String getNineaFile() {
        return nineaFile;
    }

    public ImmatriculationRecuperee nineaFile(String nineaFile) {
        this.nineaFile = nineaFile;
        return this;
    }

    public void setNineaFile(String nineaFile) {
        this.nineaFile = nineaFile;
    }

    public String getRegistreCommerce() {
        return registreCommerce;
    }

    public ImmatriculationRecuperee registreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
        return this;
    }

    public void setRegistreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImmatriculationRecuperee)) {
            return false;
        }
        return id != null && id.equals(((ImmatriculationRecuperee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ImmatriculationRecuperee{" +
            "id=" + getId() +
            ", moyenConfirmation='" + getMoyenConfirmation() + "'" +
            ", status='" + getStatus() + "'" +
            ", statusdesc='" + getStatusdesc() + "'" +
            ", payload='" + getPayload() + "'" +
            ", extrasInfo='" + getExtrasInfo() + "'" +
            ", typeIdentifiant='" + getTypeIdentifiant() + "'" +
            ", numeroIdentifiant='" + getNumeroIdentifiant() + "'" +
            ", ninea='" + getNinea() + "'" +
            ", numeroUnique='" + getNumeroUnique() + "'" +
            ", numeroCss='" + getNumeroCss() + "'" +
            ", numeroIpres='" + getNumeroIpres() + "'" +
            ", userId=" + getUserId() +
            ", agentId=" + getAgentId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", dateTraitement='" + getDateTraitement() + "'" +
            ", date='" + getDate() + "'" +
            ", zoneCss='" + getZoneCss() + "'" +
            ", sectorCss='" + getSectorCss() + "'" +
            ", zoneIpres='" + getZoneIpres() + "'" +
            ", sectorIpres='" + getSectorIpres() + "'" +
            ", agenceCss='" + getAgenceCss() + "'" +
            ", agenceIpres='" + getAgenceIpres() + "'" +
            ", tauxAt='" + getTauxAt() + "'" +
            ", raisonSociale='" + getRaisonSociale() + "'" +
            ", mandatFile='" + getMandatFile() + "'" +
            ", motif='" + getMotif() + "'" +
            ", rcFile='" + getRcFile() + "'" +
            ", nineaFile='" + getNineaFile() + "'" +
            ", registreCommerce='" + getRegistreCommerce() + "'" +
            "}";
    }

}
