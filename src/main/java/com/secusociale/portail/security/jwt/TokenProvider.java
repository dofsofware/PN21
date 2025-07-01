package com.secusociale.portail.security.jwt;

import com.secusociale.portail.domain.consultation.EmployeurConsultation;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.ImmatriculationRecupereeService;
import com.secusociale.portail.service.NouvelleImmatriculationService;
import com.secusociale.portail.service.consultation.ConsultationService;
import com.secusociale.portail.web.rest.vm.CustomResponse;
import io.github.jhipster.config.JHipsterProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {
    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    private final UserRepository userRepository;
    private final NouvelleImmatriculationService localImmatriculationService;
    private final ImmatriculationRecupereeService oldImmatriculationService;
    private final ConsultationService consultationService;
    private static final String AUTHORITIES_KEY = "auth";

    private Key key;

    private long tokenValidityInMilliseconds;

    private long tokenValidityInMillisecondsForRememberMe;

    private final JHipsterProperties jHipsterProperties;

    public TokenProvider(UserRepository userRepository, NouvelleImmatriculationService localImmatriculationService, ImmatriculationRecupereeService oldImmatriculationService, ConsultationService consultationService, JHipsterProperties jHipsterProperties) {
        this.userRepository = userRepository;
        this.localImmatriculationService = localImmatriculationService;
        this.oldImmatriculationService = oldImmatriculationService;
        this.consultationService = consultationService;
        this.jHipsterProperties = jHipsterProperties;
    }

    @PostConstruct
    public void init() {
        byte[] keyBytes;
        String secret = jHipsterProperties.getSecurity().getAuthentication().getJwt().getSecret();
        if (!StringUtils.isEmpty(secret)) {
            log.warn("Warning: the JWT key used is not Base64-encoded. " +
                "We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security.");
            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        } else {
            log.debug("Using a Base64-encoded JWT secret key");
            keyBytes = Decoders.BASE64.decode(jHipsterProperties.getSecurity().getAuthentication().getJwt().getBase64Secret());
        }
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds =
            1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
        this.tokenValidityInMillisecondsForRememberMe =
            1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt()
                .getTokenValidityInSecondsForRememberMe();
    }

    public CustomResponse createToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        Integer nbLocals = localImmatriculationService.countLocalImmatsByUser(((UserDetails) authentication.getPrincipal()).getUsername());
        Integer nbOlds = oldImmatriculationService.countOldImmatsByUser(((UserDetails) authentication.getPrincipal()).getUsername());

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        String[] roles = authorities.split(",");
        String username = authentication.getName();
        com.secusociale.portail.domain.User user = userRepository.findByLogin(username);
        String numeroUnique = consultationService.findByUserId(user.getId()).map(EmployeurConsultation::getNumeroUnique).orElse(null);
        String fullName = String.format("%s %s", user.getFirstName(), user.getLastName());
        Long id = user.getId();
        String typeCompte = user.getTypeCompte();
        String agence = user.getAgence();
        String email = user.getEmail();
        Long expiresIn = rememberMe ? this.tokenValidityInMillisecondsForRememberMe : this.tokenValidityInMilliseconds;
        String accessToken = Jwts.builder()
            .setSubject(authentication.getName())
            .claim(AUTHORITIES_KEY, authorities)
            .claim("fullname", fullName)
            .claim("typecompte", typeCompte)
            .claim("numeroUnique", numeroUnique)
            .claim("agence", agence)
            .claim("institution", user.getInstitution())
            .claim("email", email)
            .claim("lang", user.getLangKey())
            .claim("firstconnexion", !user.getHasPasswordUpdated())
            .claim("validity", new SimpleDateFormat().format(validity))
            .claim("userid", user.getId())
            .claim("nblocals", nbLocals)
            .claim("nbolds", nbOlds)
            .claim("nbimmats", nbOlds + nbLocals)
            .claim("cachet", user.getCachet())
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact();
        CustomResponse cr = new CustomResponse(id, typeCompte, agence, email, username, numeroUnique, !user.getHasPasswordUpdated(), fullName, roles, accessToken, nbLocals, nbOlds, expiresIn, user.getCachet());
        return cr;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(token)
            .getBody();

        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace.", e);
        }
        return false;
    }

}
