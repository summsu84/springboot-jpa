package com.teamjw.tripapp.app.user.security.endpoint;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamjw.tripapp.app.user.domain.User;
import com.teamjw.tripapp.app.user.security.auth.jwt.extractor.TokenExtractor;
import com.teamjw.tripapp.app.user.security.auth.jwt.verifier.TokenVerifier;
import com.teamjw.tripapp.app.user.security.config.JwtSettings;
import com.teamjw.tripapp.app.user.security.config.WebSecurityConfig;
import com.teamjw.tripapp.app.user.security.exceptions.InvalidJwtToken;
import com.teamjw.tripapp.app.user.security.model.UserContext;
import com.teamjw.tripapp.app.user.security.model.token.JwtToken;
import com.teamjw.tripapp.app.user.security.model.token.JwtTokenFactory;
import com.teamjw.tripapp.app.user.security.model.token.RawAccessJwtToken;
import com.teamjw.tripapp.app.user.security.model.token.RefreshToken;
import com.teamjw.tripapp.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 토큰을 리프레쉬 한다.
 * 
 *
 */
@RestController
public class RefreshTokenEndpoint {
    @Autowired private JwtTokenFactory tokenFactory;
    @Autowired private JwtSettings jwtSettings;
    @Autowired private UserService userService;
    @Autowired private TokenVerifier tokenVerifier;
    @Autowired @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;
    
    @RequestMapping(value="/api/auth/token", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody
    JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.AUTHENTICATION_HEADER_NAME));
        
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        User user = userService.getByUsername(subject).orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));

        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getName(), authorities);

        return tokenFactory.createAccessJwtToken(userContext);
    }
}
