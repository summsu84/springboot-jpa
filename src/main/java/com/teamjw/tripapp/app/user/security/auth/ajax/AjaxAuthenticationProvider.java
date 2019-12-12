package com.teamjw.tripapp.app.user.security.auth.ajax;

import java.util.List;
import java.util.stream.Collectors;

import com.teamjw.tripapp.app.user.domain.User;
import com.teamjw.tripapp.app.user.domain.UserAuthToken;
import com.teamjw.tripapp.app.user.service.DatabaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


import com.teamjw.tripapp.app.user.security.model.UserContext;

/**
 *  Ajax 인증 프로바이더
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    private final BCryptPasswordEncoder encoder;
    private final DatabaseUserService userService;

    @Autowired
    public AjaxAuthenticationProvider(final DatabaseUserService userService, final BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = userService.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        
        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());
        
        UserContext userContext = UserContext.create(user.getName(), authorities);
        //UserContext userContext = UserContext.create(user.getId().toString(), authorities);

        Authentication tmp = new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());



        return tmp;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
