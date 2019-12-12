package com.teamjw.tripapp.app.user.security.endpoint;

import com.teamjw.tripapp.app.user.domain.Role;
import com.teamjw.tripapp.app.user.domain.User;
import com.teamjw.tripapp.app.user.domain.UserRoles;
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
import com.teamjw.tripapp.app.user.service.UserRolesService;
import com.teamjw.tripapp.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 사용자를 등록 한다.
 *
 */
@RestController
public class CreateUserEndpoint {
    @Autowired private JwtTokenFactory tokenFactory;
    @Autowired private JwtSettings jwtSettings;
    @Autowired private UserService userService;
    @Autowired private TokenVerifier tokenVerifier;
    @Autowired @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserRolesService userRolesService;

    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping(value="/api/auth/user", method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody
    User createUser(@RequestBody User user1, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String password = user1.getPassword();
        String encodedPassword = encoder.encode(password);
        user1.setPassword(encodedPassword);
        //User user = userService.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        User user = userService.createUser(user1);
        UserRoles roles = new UserRoles();
        roles.setRole(Role.MEMBER);
        roles.setUser(user);
        roles.getUser().getRoles().add(roles);

        UserRoles role1 = userRolesService.createUserRoles(roles);
        //return tokenFactory.createAccessJwtToken(userContext);

        return user;
    }
}
