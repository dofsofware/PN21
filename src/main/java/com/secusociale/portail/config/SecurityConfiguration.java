package com.secusociale.portail.config;

import com.secusociale.portail.beans.SessionAuthenticationStrategy;
import com.secusociale.portail.security.*;
import com.secusociale.portail.security.jwt.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    private final CorsFilter corsFilter;
    private final SecurityProblemSupport problemSupport;

    public SecurityConfiguration(TokenProvider tokenProvider, CorsFilter corsFilter, SecurityProblemSupport problemSupport) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.problemSupport = problemSupport;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .antMatchers("/app/**/*.{js,html}")
            .antMatchers("/i18n/**")
            .antMatchers("/content/**")
            .antMatchers("/swagger-ui/index.html")
            .antMatchers("/test/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf()
            .disable()
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
            .authenticationEntryPoint(problemSupport)
            .accessDeniedHandler(problemSupport)
            .and()
            .headers()
            .contentSecurityPolicy("default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:")
            .and()
            .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
            .and()
            .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; speaker 'none'; fullscreen 'self'; payment 'none'")
            .and()
            .frameOptions()
            .deny()
            .and()
            .sessionManagement().sessionAuthenticationStrategy(sessionControlStrategy())
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers(HttpMethod.POST,"/api/piece-jointes/**").permitAll()
            .antMatchers(HttpMethod.POST,"/api/salaries/renvoieCodeOTP/**").permitAll()
            .antMatchers("/api/apg-callback").permitAll()
            .antMatchers("/api/apg-paiments/prelevements/**").permitAll()
            .antMatchers("/api/callback").permitAll()
            .antMatchers("/api/register").permitAll()
            .antMatchers("/api/register-salarie").permitAll()
            .antMatchers("/api/verification").permitAll()
            .antMatchers("/api/register-employeur").permitAll()
            .antMatchers("/api/activate").permitAll()
            .antMatchers("/api/account/reset-password/init").permitAll()
            .antMatchers("/api/account/reset-password/finish").permitAll()
            .antMatchers("/api/account/check").permitAll()
            .antMatchers("/api/extractions/migrate-old-immats").permitAll()
            .antMatchers("/api/extractions/migrate-from-recuperee-to-new-immats").permitAll()
            .antMatchers("/api/extractions/migrate-new-immats").permitAll()
            .antMatchers("/api/extractions/migrate-all-in-one/**").permitAll()
            .antMatchers("/api/helpers/**").permitAll()
            .antMatchers("/api/ged/**").permitAll()
            .antMatchers("/websocket/tracker").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/websocket/tracker").hasAuthority(AuthoritiesConstants.SUPER_ADMIN)
            .antMatchers("/websocket/**").permitAll()
            .antMatchers("/management/health").permitAll()
            .antMatchers("/management/info").permitAll()
            .antMatchers("/management/prometheus").permitAll()
            .antMatchers("/api/attestation/create/**").hasAnyAuthority(AuthoritiesConstants.USER,AuthoritiesConstants.AGENT,AuthoritiesConstants.SUPER_ADMIN)
            .antMatchers("/api/statutDossierImmatriculation/**").hasAnyAuthority(AuthoritiesConstants.USER,AuthoritiesConstants.AGENT,AuthoritiesConstants.SUPER_ADMIN)
            .antMatchers("/api/attestation/getUrl/**").permitAll()
            .antMatchers("/api/listDeclarations/getFacture/**").permitAll()
            .antMatchers("/api/listDeclarations/**").permitAll()
            .antMatchers("/api/succursales/**").permitAll()
            .antMatchers("/api/certifactFile/**").permitAll()
            .antMatchers("/api/duplicata/recu/**").permitAll()
            .antMatchers("/api/duplicata/facture/**").permitAll()
            .antMatchers("/v2/api-docs").permitAll()
            .antMatchers("/api/cessation_suspension/urlReception/**").permitAll()
            .antMatchers("/api/dns").hasAnyAuthority(AuthoritiesConstants.USER,AuthoritiesConstants.SUPER_ADMIN,AuthoritiesConstants.ADMIN,AuthoritiesConstants.AGENT,AuthoritiesConstants.CHEF_AGENCE,AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR,AuthoritiesConstants.GESTIONNAIRE_SALARIE)
            .antMatchers("/api/preDNS").hasAnyAuthority(AuthoritiesConstants.USER,AuthoritiesConstants.AGENT,AuthoritiesConstants.SUPER_ADMIN)
            .antMatchers("/api/immatPortail").hasAuthority(AuthoritiesConstants.USER)
            .antMatchers("/api/employeurExistant").hasAnyAuthority(AuthoritiesConstants.USER,AuthoritiesConstants.AGENT,AuthoritiesConstants.SUPER_ADMIN)
            .antMatchers("/api/certificatImmatriculation/**").hasAnyAuthority(AuthoritiesConstants.USER,AuthoritiesConstants.AGENT,AuthoritiesConstants.SUPER_ADMIN)
            .antMatchers("/api/checkExistenceEmployeur/**").hasAnyAuthority(AuthoritiesConstants.USER,AuthoritiesConstants.AGENT,AuthoritiesConstants.SUPER_ADMIN)
            .antMatchers("/api/attestation/getStatus/**").hasAnyAuthority(AuthoritiesConstants.USER,AuthoritiesConstants.AGENT,AuthoritiesConstants.SUPER_ADMIN)
            .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.SUPER_ADMIN)
            .antMatchers("/api/helpers/**").permitAll()
            .antMatchers("/api/**").authenticated()
            .and()
            .httpBasic()
            .and()
            .apply(securityConfigurerAdapter());
        // @formatter:on
        //http.sessionManagement().maximumSessions(1);
    }

    @Bean
    public SessionAuthenticationStrategy sessionControlStrategy() {
        return new SessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
}
