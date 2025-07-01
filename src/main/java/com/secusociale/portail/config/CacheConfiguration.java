package com.secusociale.portail.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.secusociale.portail.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.secusociale.portail.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.secusociale.portail.domain.User.class.getName());
            createCache(cm, com.secusociale.portail.domain.Authority.class.getName());
            createCache(cm, com.secusociale.portail.domain.User.class.getName() + ".authorities");
            createCache(cm, com.secusociale.portail.domain.Employeur.class.getName());
            createCache(cm, com.secusociale.portail.domain.Employeur.class.getName() + ".idDossiers");
            createCache(cm, com.secusociale.portail.domain.Employeur.class.getName() + ".documents");
            createCache(cm, com.secusociale.portail.domain.DocumentUrl.class.getName());
            createCache(cm, com.secusociale.portail.domain.DemandeService.class.getName());
            createCache(cm, com.secusociale.portail.domain.Employeur.class.getName() + ".demandes");
            createCache(cm, com.secusociale.portail.domain.Localimmatriculation.class.getName());
            createCache(cm, com.secusociale.portail.domain.Localimmatriculation.class.getName() + ".immatriculations");
            createCache(cm, com.secusociale.portail.domain.TestEntity.class.getName());
            createCache(cm, com.secusociale.portail.domain.AttemptCache.class.getName());
            createCache(cm, com.secusociale.portail.domain.OldImmatriculation.class.getName());
            createCache(cm, com.secusociale.portail.domain.DeclarationManquante.class.getName());
            createCache(cm, com.secusociale.portail.domain.Declaration.class.getName());
            createCache(cm, com.secusociale.portail.domain.APGPaiment.class.getName());
            createCache(cm, com.secusociale.portail.domain.Agence.class.getName());
            createCache(cm, com.secusociale.portail.domain.TraceInfos.class.getName());
            createCache(cm, com.secusociale.portail.domain.Helpers.class.getName());
            createCache(cm, com.secusociale.portail.domain.DMT.class.getName());
            createCache(cm, com.secusociale.portail.domain.Configcompte.class.getName());
            createCache(cm, com.secusociale.portail.domain.EmployeurCompte.class.getName());
            createCache(cm, com.secusociale.portail.domain.EmailNotif.class.getName());
            createCache(cm, com.secusociale.portail.domain.ServerCheck.class.getName());
            createCache(cm, com.secusociale.portail.domain.Salarie.class.getName());
            createCache(cm, com.secusociale.portail.domain.GrappeMember.class.getName());
            createCache(cm, com.secusociale.portail.domain.Carriere.class.getName());
            createCache(cm, com.secusociale.portail.domain.PieceJointe.class.getName());
            createCache(cm, com.secusociale.portail.domain.DemandeServiceSalarie.class.getName());
            createCache(cm, com.secusociale.portail.domain.DemandeServiceSalarie.class.getName() + ".salaries");
            createCache(cm, com.secusociale.portail.domain.CronSetting.class.getName());
            createCache(cm, com.secusociale.portail.domain.Activities.class.getName());
            createCache(cm, com.secusociale.portail.domain.GedUpdate.class.getName());
            createCache(cm, com.secusociale.portail.domain.NouvelleImmatriculation.class.getName());
            createCache(cm, com.secusociale.portail.domain.ImmatriculationRecuperee.class.getName());
            createCache(cm, com.secusociale.portail.domain.FactureImpayee.class.getName());
            createCache(cm, com.secusociale.portail.domain.FactureDNS.class.getName());
            createCache(cm, com.secusociale.portail.domain.SuiviJob.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
