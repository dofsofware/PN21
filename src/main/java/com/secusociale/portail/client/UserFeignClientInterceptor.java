package com.secusociale.portail.client;

import com.secusociale.portail.domain.User;
import com.secusociale.portail.security.SecurityUtils;
import com.secusociale.portail.service.UserService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientInterceptor implements RequestInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer";

    @Autowired
    UserService userService;

    @Override
    public void apply(RequestTemplate template) {
        //SecurityUtils.getCurrentUserJWT()
        //.ifPresent(s -> template.header(AUTHORIZATION_HEADER,String.format("%s %s", BEARER, s)));
        if (SecurityUtils.getCurrentUserJWT().isPresent()) {
            String jwt = SecurityUtils.getCurrentUserJWT().get();
            User user = userService.getLoggerUser();
            if (user != null) {
                if (!user.getHasPasswordUpdated()) {
                    throw new RuntimeException("Veuillez changer votre mot de passe");
                } else {
                    template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER, jwt));
                }
            }
        }
    }
}
