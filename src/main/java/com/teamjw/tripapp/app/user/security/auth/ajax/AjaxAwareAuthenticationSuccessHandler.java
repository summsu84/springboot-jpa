package com.teamjw.tripapp.app.user.security.auth.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamjw.tripapp.app.user.domain.User;
import com.teamjw.tripapp.app.user.domain.UserAuthToken;
import com.teamjw.tripapp.app.user.security.model.UserContext;
import com.teamjw.tripapp.app.user.security.model.token.JwtToken;
import com.teamjw.tripapp.app.user.security.model.token.JwtTokenFactory;
import com.teamjw.tripapp.app.user.service.DatabaseUserService;
import com.teamjw.tripapp.app.user.service.UserAuthTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 *  Ajax 인증 성공 핸들러
 *  AuthenticationSuccessHandler를 구현한다.
 */
@Component
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper mapper;
    private final JwtTokenFactory tokenFactory;

    private final DatabaseUserService userService;
    private final UserAuthTokenService userAuthTokenService;

    @Autowired
    public AjaxAwareAuthenticationSuccessHandler(final UserAuthTokenService userAuthTokenService, DatabaseUserService userService, final ObjectMapper mapper, final JwtTokenFactory tokenFactory) {
        this.userAuthTokenService = userAuthTokenService;
        this.userService = userService;
        this.mapper = mapper;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        UserContext userContext = (UserContext) authentication.getPrincipal();
        
        JwtToken accessToken = tokenFactory.createAccessJwtToken(userContext);
        JwtToken refreshToken = tokenFactory.createRefreshToken(userContext);
        
        Map<String, String> tokenMap = new HashMap<String, String>();
        tokenMap.put("token", accessToken.getToken());
        tokenMap.put("refreshToken", refreshToken.getToken());

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), tokenMap);

        //Id를 기반으로 user 정보 가져오기
        Optional<User> user = userService.getByUsername(userContext.getUsername());

        UserAuthToken userAuthToken = new UserAuthToken();
        userAuthToken.setUser(user.get());
        userAuthToken.setToken(accessToken.getToken());
        userAuthTokenService.createUserRoles(userAuthToken);

        clearAuthenticationAttributes(request);
    }

    /**
     * Removes temporary authentication-related data which may have been stored
     * in the session during the authentication process..
     * 
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }


}
