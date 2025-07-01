package com.secusociale.portail.repository;

import com.secusociale.portail.domain.TauxAT;
import com.secusociale.portail.domain.TauxAT_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TauxATRepository extends JpaRepository<TauxAT, Long>, JpaSpecificationExecutor<TauxAT_> {
    long count();
    @Query("SELECT t.tauxAT FROM TauxAT t WHERE TRIM(t.numeroUnique) = Trim(:numeroUnique)")
    String findTauxATByNumeroUnique(@Param("numeroUnique") String numeroUnique);

    TauxAT findByNumeroUnique(String numeroUnique);
}
