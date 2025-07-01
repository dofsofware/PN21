package com.secusociale.portail.service.dto.consultation;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.service.dto.UserDTO;
import com.secusociale.portail.service.dto.custom.AgentDTO;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.secusociale.portail.domain.consultation.EmployeurConsultation} entity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmployeurConsultationDTO implements Serializable {

    private Long id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    private String activationFrontUrl;

    private UserDTO user;
    private AgentDTO agent;

    @NotNull
    private String numeroUnique;
    @NotNull
    private String numeroCni;

    private String prenom;

    private String nom;

    private String telephone;

    private String email;

    private Boolean active = false;

    private Long userId;

    private Long agentId;

    private String firstName;
    private String lastName;
    private String phone;


    public Set<String> getRoles() {
        Set<String> roles = new HashSet<>();
        roles.add(AuthoritiesConstants.CONSULTATION);
        return roles;
    }

}
