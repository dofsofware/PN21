package com.secusociale.portail.repository;

import com.secusociale.portail.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the User entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NewUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

}
