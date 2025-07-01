package com.secusociale.portail.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A Employeur.
 */
@Entity
@Table(name = "employeur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Employeur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employer_type")
    private String employerType;

    @Column(name = "type_etablissement")
    private String typeEtablissement;

    @Column(name = "raison_sociale")
    @JsonProperty("raisonSociale")
    private String employerName;

    @Column(name = "maison_mere")
    private String maisonMere;

    @Column(name = "prenom")
    @JsonProperty("prenom")
    private String firstName;

    @Column(name = "nom")
    @JsonProperty("nom")
    private String lastName;

    @Column(name = "type_identifiant")
    @JsonProperty("typeIdentifiant")
    private String typeOfIdentity;

    @Column(name = "numero_identifiant")
    @JsonProperty("numeroIdentifiant")
    private String numeroIdentifiant;

    @Column(name = "legal_status")
    private String legalStatus;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "business_sector")
    private String businessSector;

    @Column(name = "main_line_of_business")
    private String mainLineOfBusiness;

    @Column(name = "no_of_workers_in_gen_scheme")
    private Integer noOfWorkersInGenScheme;

    @Column(name = "no_of_workers_in_basic_scheme")
    private Integer noOfWorkersInBasicScheme;

    @Column(name = "region")
    private String region;

    @Column(name = "department")
    private String department;

    @Column(name = "arondissement")
    private String arondissement;

    @Column(name = "commune")
    private String commune;

    @Column(name = "qartier")
    private String qartier;

    @Column(name = "address")
    private String address;

    @Column(name = "postbox_no")
    private String postboxNo;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "website")
    private String website;

    @Column(name = "zone_css")
    private String zoneCss;

    @Column(name = "zone_ipres")
    private String zoneIpres;

    @Column(name = "sector_css")
    private String sectorCss;

    @Column(name = "sector_ipres")
    private String sectorIpres;

    @Column(name = "agency_css")
    private String agencyCss;

    @Column(name = "agency_ipres")
    private String agencyIpres;

    @Column(name = "taux_at")
    private String tauxAT;

    @Column(name = "process_flow_id")
    private String processFlowId;

    @Column(name = "statut_dossier")
    private String statutDossier;

    @Column(name = "statut_immatriculation")
    private Boolean statutImmatriculation;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @OneToMany(mappedBy = "employeur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DocumentUrl> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("employeurs")
    private User user;

    @OneToOne
    @JoinColumn(name = "manager_id")
    @JsonIgnoreProperties("employeurs")
    private User manager;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployerType() {
        return employerType;
    }

    public Employeur employerType(String employerType) {
        this.employerType = employerType;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setEmployerType(String employerType) {
        this.employerType = employerType;
    }

    public String getTypeEtablissement() {
        return typeEtablissement;
    }

    public Employeur typeEtablissement(String typeEtablissement) {
        this.typeEtablissement = typeEtablissement;
        return this;
    }

    public void setTypeEtablissement(String typeEtablissement) {
        this.typeEtablissement = typeEtablissement;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public Employeur employerName(String employerName) {
        this.employerName = employerName;
        return this;
    }

    public String getMaisonMere() {
        return maisonMere;
    }

    public Employeur maisonMere(String maisonMere) {
        this.maisonMere = maisonMere;
        return this;
    }

    public void setMaisonMere(String maisonMere) {
        this.maisonMere = maisonMere;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String prenom) {
        this.firstName = prenom;
    }

    public Employeur prenom(String prenom) {
        this.firstName = prenom;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String nom) {
        this.lastName = nom;
    }

    public Employeur nom(String nom) {
        this.lastName = nom;
        return this;
    }

    public String getTypeOfIdentity() {
        return typeOfIdentity;
    }

    public void setTypeOfIdentity(String typeIdentifiant) {
        this.typeOfIdentity = typeIdentifiant;
    }

    public Employeur typeIdentifiant(String typeIdentifiant) {
        this.typeOfIdentity = typeIdentifiant;
        return this;
    }

    public String getNumeroIdentifiant() {
        return numeroIdentifiant;
    }

    public Employeur numeroIdentifiant(String numeroIdentifiant) {
        this.numeroIdentifiant = numeroIdentifiant;
        return this;
    }

    public void setNumeroIdentifiant(String numeroIdentifiant) {
        this.numeroIdentifiant = numeroIdentifiant;
    }

    public String getLegalStatus() {
        return legalStatus;
    }

    public Employeur legalStatus(String legalStatus) {
        this.legalStatus = legalStatus;
        return this;
    }

    public void setLegalStatus(String legalStatus) {
        this.legalStatus = legalStatus;
    }

    public String getShortName() {
        return shortName;
    }

    public Employeur shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getBusinessSector() {
        return businessSector;
    }

    public Employeur businessSector(String businessSector) {
        this.businessSector = businessSector;
        return this;
    }

    public void setBusinessSector(String businessSector) {
        this.businessSector = businessSector;
    }

    public String getMainLineOfBusiness() {
        return mainLineOfBusiness;
    }

    public Employeur mainLineOfBusiness(String mainLineOfBusiness) {
        this.mainLineOfBusiness = mainLineOfBusiness;
        return this;
    }

    public void setMainLineOfBusiness(String mainLineOfBusiness) {
        this.mainLineOfBusiness = mainLineOfBusiness;
    }

    public Integer getNoOfWorkersInGenScheme() {
        return noOfWorkersInGenScheme;
    }

    public Employeur noOfWorkersInGenScheme(Integer noOfWorkersInGenScheme) {
        this.noOfWorkersInGenScheme = noOfWorkersInGenScheme;
        return this;
    }

    public void setNoOfWorkersInGenScheme(Integer noOfWorkersInGenScheme) {
        this.noOfWorkersInGenScheme = noOfWorkersInGenScheme;
    }

    public Integer getNoOfWorkersInBasicScheme() {
        return noOfWorkersInBasicScheme;
    }

    public Employeur noOfWorkersInBasicScheme(Integer noOfWorkersInBasicScheme) {
        this.noOfWorkersInBasicScheme = noOfWorkersInBasicScheme;
        return this;
    }

    public void setNoOfWorkersInBasicScheme(Integer noOfWorkersInBasicScheme) {
        this.noOfWorkersInBasicScheme = noOfWorkersInBasicScheme;
    }

    public String getRegion() {
        return region;
    }

    public Employeur region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDepartment() {
        return department;
    }

    public Employeur department(String department) {
        this.department = department;
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArondissement() {
        return arondissement;
    }

    public Employeur arondissement(String arondissement) {
        this.arondissement = arondissement;
        return this;
    }

    public void setArondissement(String arondissement) {
        this.arondissement = arondissement;
    }

    public String getCommune() {
        return commune;
    }

    public Employeur commune(String commune) {
        this.commune = commune;
        return this;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getQartier() {
        return qartier;
    }

    public Employeur qartier(String qartier) {
        this.qartier = qartier;
        return this;
    }

    public void setQartier(String qartier) {
        this.qartier = qartier;
    }

    public String getAddress() {
        return address;
    }

    public Employeur address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostboxNo() {
        return postboxNo;
    }

    public Employeur postboxNo(String postboxNo) {
        this.postboxNo = postboxNo;
        return this;
    }

    public void setPostboxNo(String postboxNo) {
        this.postboxNo = postboxNo;
    }

    public String getTelephone() {
        return telephone;
    }

    public Employeur telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public Employeur email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public Employeur website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getZoneCss() {
        return zoneCss;
    }

    public Employeur zoneCss(String zoneCss) {
        this.zoneCss = zoneCss;
        return this;
    }

    public void setZoneCss(String zoneCss) {
        this.zoneCss = zoneCss;
    }

    public String getZoneIpres() {
        return zoneIpres;
    }

    public Employeur zoneIpres(String zoneIpres) {
        this.zoneIpres = zoneIpres;
        return this;
    }

    public void setZoneIpres(String zoneIpres) {
        this.zoneIpres = zoneIpres;
    }

    public String getSectorCss() {
        return sectorCss;
    }

    public Employeur sectorCss(String sectorCss) {
        this.sectorCss = sectorCss;
        return this;
    }

    public void setSectorCss(String sectorCss) {
        this.sectorCss = sectorCss;
    }

    public String getSectorIpres() {
        return sectorIpres;
    }

    public Employeur sectorIpres(String sectorIpres) {
        this.sectorIpres = sectorIpres;
        return this;
    }

    public void setSectorIpres(String sectorIpres) {
        this.sectorIpres = sectorIpres;
    }

    public String getAgencyCss() {
        return agencyCss;
    }

    public Employeur agencyCss(String agencyCss) {
        this.agencyCss = agencyCss;
        return this;
    }

    public void setAgencyCss(String agencyCss) {
        this.agencyCss = agencyCss;
    }

    public String getAgencyIpres() {
        return agencyIpres;
    }

    public Employeur agencyIpres(String agencyIpres) {
        this.agencyIpres = agencyIpres;
        return this;
    }

    public void setAgencyIpres(String agencyIpres) {
        this.agencyIpres = agencyIpres;
    }

    public String getTauxAT() {
        return tauxAT;
    }

    public Employeur tauxAT(String tauxAT) {
        this.tauxAT = tauxAT;
        return this;
    }

    public void setTauxAT(String tauxAT) {
        this.tauxAT = tauxAT;
    }

    public String getProcessFlowId() {
        return processFlowId;
    }

    public Employeur processFlowId(String processFlowId) {
        this.processFlowId = processFlowId;
        return this;
    }

    public void setProcessFlowId(String processFlowId) {
        this.processFlowId = processFlowId;
    }

    public String getStatutDossier() {
        return statutDossier;
    }

    public Employeur statutDossier(String statutDossier) {
        this.statutDossier = statutDossier;
        return this;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }


    public void setStatutDossier(String statutDossier) {
        this.statutDossier = statutDossier;
    }

    public Boolean isStatutImmatriculation() {
        return statutImmatriculation;
    }

    public Employeur statutImmatriculation(Boolean statutImmatriculation) {
        this.statutImmatriculation = statutImmatriculation;
        return this;
    }

    public void setStatutImmatriculation(Boolean statutImmatriculation) {
        this.statutImmatriculation = statutImmatriculation;
    }

    public Set<DocumentUrl> getDocuments() {
        return documents;
    }

    public Employeur documents(Set<DocumentUrl> documentUrls) {
        this.documents = documentUrls;
        return this;
    }

    public Employeur addDocument(DocumentUrl documentUrl) {
        this.documents.add(documentUrl);
        documentUrl.setEmployeur(this);
        return this;
    }

    public Employeur removeDocument(DocumentUrl documentUrl) {
        this.documents.remove(documentUrl);
        documentUrl.setEmployeur(null);
        return this;
    }

    public void setDocuments(Set<DocumentUrl> documentUrls) {
        this.documents = documentUrls;
    }

    public User getUser() {
        return user;
    }

    public Employeur user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employeur merge(Employeur employeur) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method[] methods = this.getClass().getMethods();
        if (employeur == null)
            return this;
        for (Method m : methods)
            if (m.getName().startsWith("get") && m.getParameterTypes().length == 0) {
                final Object base = m.invoke(this);
                if (base == null && m.invoke(employeur) != null) {
                    String methodname = m.getName().replaceFirst("get", "set");
                    Optional<Method> maybe = Arrays.stream(methods).filter(method -> method.getName().equals(methodname)).findFirst();

                    if (maybe.isPresent()) {
                        Class[] aarg = maybe.get().getParameterTypes();
                        Method setMethod = this.getClass().getMethod(methodname, aarg);
                        setMethod.invoke(this, m.invoke(employeur));
                    }
                }
                // do your thing with r
            }
        return this;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employeur)) {
            return false;
        }
        return id != null && id.equals(((Employeur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Employeur{" +
            "id=" + getId() +
            ", employerType='" + getEmployerType() + "'" +
            ", typeEtablissement='" + getTypeEtablissement() + "'" +
            ", raisonSociale='" + getEmployerName() + "'" +
            ", maisonMere='" + getMaisonMere() + "'" +
            ", prenom='" + getFirstName() + "'" +
            ", nom='" + getLastName() + "'" +
            ", typeIdentifiant='" + getTypeOfIdentity() + "'" +
            ", numeroIdentifiant='" + getNumeroIdentifiant() + "'" +
            ", legalStatus='" + getLegalStatus() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", businessSector='" + getBusinessSector() + "'" +
            ", mainLineOfBusiness='" + getMainLineOfBusiness() + "'" +
            ", noOfWorkersInGenScheme=" + getNoOfWorkersInGenScheme() +
            ", noOfWorkersInBasicScheme=" + getNoOfWorkersInBasicScheme() +
            ", region='" + getRegion() + "'" +
            ", department='" + getDepartment() + "'" +
            ", arondissement='" + getArondissement() + "'" +
            ", commune='" + getCommune() + "'" +
            ", qartier='" + getQartier() + "'" +
            ", address='" + getAddress() + "'" +
            ", postboxNo='" + getPostboxNo() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", website='" + getWebsite() + "'" +
            ", zoneCss='" + getZoneCss() + "'" +
            ", zoneIpres='" + getZoneIpres() + "'" +
            ", sectorCss='" + getSectorCss() + "'" +
            ", sectorIpres='" + getSectorIpres() + "'" +
            ", agencyCss='" + getAgencyCss() + "'" +
            ", agencyIpres='" + getAgencyIpres() + "'" +
            ", tauxAT='" + getTauxAT() + "'" +
            ", processFlowId='" + getProcessFlowId() + "'" +
            ", statutDossier='" + getStatutDossier() + "'" +
            ", statutImmatriculation='" + isStatutImmatriculation() + "'" +
            "}";
    }
}
