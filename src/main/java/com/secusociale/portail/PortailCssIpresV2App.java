package com.secusociale.portail;

import com.secusociale.portail.config.ApplicationProperties;
import com.secusociale.portail.domain.Authority;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.AuthorityRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.service.GeoInfoService;
import com.secusociale.portail.service.TauxATService;
import io.github.jhipster.config.DefaultProfileUtil;
import io.github.jhipster.config.JHipsterConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class PortailCssIpresV2App implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(PortailCssIpresV2App.class);

    private final Environment env;
    @Autowired
    private GeoInfoService geoInfoService;
    @Autowired
    private TauxATService tauxATService;

    public PortailCssIpresV2App(Environment env) {
        this.env = env;
    }

    /**
     * Initializes PortailCssIpresV2.
     * <p>
     * Spring profiles can be configured with a program argument --spring.profiles.active=your-active-profile
     * <p>
     * You can find more information on how profiles work with JHipster on <a href="https://www.jhipster.tech/profiles/">https://www.jhipster.tech/profiles/</a>.
     */
    @PostConstruct
    public void initApplication() {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {
            log.error("You have misconfigured your application! It should not run " +
                "with both the 'dev' and 'prod' profiles at the same time.");
        }
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_CLOUD)) {
            log.error("You have misconfigured your application! It should not " +
                "run with both the 'dev' and 'cloud' profiles at the same time.");
        }
    }

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PortailCssIpresV2App.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

    @Bean
    CommandLineRunner runner(AuthorityRepository authorityRepository, UserRepository userRepository) {
        return args -> {
            List<Authority> authorities = Arrays.asList(
                new Authority(AuthoritiesConstants.ADMIN),
                new Authority(AuthoritiesConstants.USER),
                new Authority(AuthoritiesConstants.EMPLOYEUR),
                new Authority(AuthoritiesConstants.EMPLOYE),
                new Authority(AuthoritiesConstants.CONSULTATION),
                new Authority(AuthoritiesConstants.AGENT),
                new Authority(AuthoritiesConstants.SALARIE),
                new Authority(AuthoritiesConstants.GESTIONNAIRE_SALARIE),
                new Authority(AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR),
                new Authority(AuthoritiesConstants.CHEF_AGENCE),
                new Authority(AuthoritiesConstants.SUPER_ADMIN),
                new Authority(AuthoritiesConstants.CABINET)
            );
            for (Authority authority : authorities) {
                if (!authorityRepository.existsByName(authority.getName())) {
                    authorityRepository.save(authority);
                }
            }
            User adminUser = User.builder()
                .login("admin")
                .password("$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC")
                .firstName("Admin")
                .lastName("Admin")
                .email("adminci@yopmail.com")
                .imageUrl("http://placehold.it/50x50")
                .langKey("fr")
                .hasPasswordUpdated(true)
                .activated(true)
                .typeCompte("ADMIN")
                .locked(false)
                .authorities(new HashSet<>(Collections.singletonList(authorityRepository.findByName(AuthoritiesConstants.ADMIN))))
                .build();

            if (!userRepository.findOneByLogin("admin").isPresent()) {
                userRepository.save(adminUser);
            }
        };
    }

    @Override
    public void run(String... args) throws Exception {
        geoInfoService.initializeGeoInfos();
        tauxATService.initializeTauxAT();
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isBlank(contextPath)) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\t{}://localhost:{}{}\n\t" +
                "External: \t{}://{}:{}{}\n\t" +
                "Profile(s): \t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            protocol,
            serverPort,
            contextPath,
            protocol,
            hostAddress,
            serverPort,
            contextPath,
            env.getActiveProfiles());
    }
}
