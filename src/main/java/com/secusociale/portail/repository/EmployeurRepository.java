package com.secusociale.portail.repository;

import com.secusociale.portail.domain.Employeur;

import com.secusociale.portail.domain.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Employeur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeurRepository extends JpaRepository<Employeur, Long> {

    @Query("select employeur from Employeur employeur where employeur.user.login = ?#{principal.username}")
    List<Employeur> findByUserIsCurrentUser();

    //List<Employeur> findAllByUserLogin(String login);
    Employeur findByProcessFlowIdAndUserLogin(String processFlowId, String login);

    Employeur findByTypeOfIdentityAndNumeroIdentifiantAndUserLogin(String typeIdentifiant, String numeroIdentifiant, String login);

    Optional<Employeur> findByTypeOfIdentityAndNumeroIdentifiant(String typeIdentifiant, String numeroIdentifiant);

    Optional<Employeur> findByTypeOfIdentityAndNumeroIdentifiantAndUserId(String typeIdentifiant, String numeroIdentifiant, Long userId);

    Employeur findByUser(User user);
}
