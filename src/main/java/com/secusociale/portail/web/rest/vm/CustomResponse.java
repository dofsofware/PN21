package com.secusociale.portail.web.rest.vm;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
    public Long id;
    public String typeCompte;
    public String agence;
    public String email;
    public String username;
    public String numeroUnique;
    public boolean firstConnexion;
    public String fullName;
    public String[] roles;
    public String accessToken;
    public Integer nbLocalImmat;
    public Integer nbOldImmat;
    public Long expiresIn;
    public String cachet;
}
