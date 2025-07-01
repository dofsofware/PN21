package com.secusociale.portail.validators;

import com.secusociale.portail.domain.DeclaredEmployer;
import com.secusociale.portail.domain.enumeration.StatutJuridique;
import com.secusociale.portail.repository.DeclaredEmployerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class DeclaredEmployerValidator {

    private final DeclaredEmployerRepository declaredEmployerRepository;

    public DeclaredEmployerValidator(DeclaredEmployerRepository declaredEmployerRepository) {
        this.declaredEmployerRepository = declaredEmployerRepository;
    }

    public static void DeclaredEmployerValidators(List<DeclaredEmployer> employers, DeclaredEmployerRepository declaredEmployerRepository) {
        Set<String> uniqueNineaSet = new HashSet<>();
        Set<String> uniqueNumeroUniqueSet = new HashSet<>();
        int index = 2;
        for (DeclaredEmployer employer : employers) {
            if (!uniqueNineaSet.add(employer.getNinea())) {
                throw new ConstraintViolationException("Duplication détectée : Le NINEA '" + employer.getNinea() + "' est déjà présent dans votre fichier. Cellule A"+index);
            }

            if (!uniqueNumeroUniqueSet.add(employer.getNumeroUnique())) {
                throw new ConstraintViolationException("Duplication détectée : Le numéro unique '" + employer.getNumeroUnique() + "' est déjà présent dans votre fichier.. Cellule C"+index);
            }

            validateEmployer(employer, declaredEmployerRepository,index);
            index++;
        }
    }

    private static void validateEmployer(DeclaredEmployer employer, DeclaredEmployerRepository declaredEmployerRepository,Integer ligne) {
        if (employer.getAncienNumIpres() == null) {
            throw new ConstraintViolationException("Le champ 'ancienNumIpres' est obligatoire. Cellule D"+ligne);
        }
        if (employer.getAncienNumCss() == null) {
            throw new ConstraintViolationException("Le champ 'ancienNumCss' est obligatoire. Cellule E"+ligne);
        }
        if (employer.getAdresse() == null || employer.getAdresse().isEmpty()) {
            throw new ConstraintViolationException("Le champ 'adresse' est obligatoire. Cellule F"+ligne);
        }
        if (employer.getRegion() == null || employer.getRegion().isEmpty()) {
            throw new ConstraintViolationException("Le champ 'region' est obligatoire. Cellule G"+ligne);
        }
        if (employer.getEffectif() == null) {
            throw new ConstraintViolationException("Le champ 'effectif' est obligatoire. Cellule I"+ligne);
        }
        if (employer.getStatutJuridique() == null) {
            throw new ConstraintViolationException("Le champ 'statutJuridique' est obligatoire et compris dans " + Arrays.toString(StatutJuridique.values())+"  Cellule J"+ligne);
        }
        if (employer.getSecteurActivite() == null || employer.getSecteurActivite().isEmpty()) {
            throw new ConstraintViolationException("Le champ 'secteurActivite' est obligatoire. Cellule K"+ligne);
        }
        if (employer.getRccm() == null || employer.getRccm().isEmpty()) {
            throw new ConstraintViolationException("Le champ 'rccm' est obligatoire. Cellule M"+ligne);
        }
        if (employer.getAgenceIpres() == null || employer.getAgenceIpres().isEmpty()) {
            throw new ConstraintViolationException("Le champ 'agenceIpres' est obligatoire. Cellule N"+ligne);
        }
        if (employer.getAgenceCss() == null || employer.getAgenceCss().isEmpty()) {
            throw new ConstraintViolationException("Le champ 'agenceCss' est obligatoire. Cellule O"+ligne);
        }
        if (employer.getNumeroUnique() == null) {
            throw new ConstraintViolationException("Le champ 'numeroUnique' est obligatoire. Cellule C"+ligne);
        }
        if (declaredEmployerRepository.existsByNumeroUnique(employer.getNumeroUnique())) {
            throw new ConstraintViolationException("Le champ 'numeroUnique' doit être unique. Valeur '" + employer.getNumeroUnique() + "' déjà présente dans la base. Cellule C"+ligne);
        }
        if (employer.getNinea() == null) {
            throw new ConstraintViolationException("Le champ 'ninea' est obligatoire et unique. Cellule A"+ligne);
        }
        if (declaredEmployerRepository.existsByNinea(employer.getNinea())) {
            throw new ConstraintViolationException("Le champ 'ninea' doit être unique. Valeur '" + employer.getNinea() + "' déjà présente dans la base. Cellule A"+ligne);
        }
    }
}
