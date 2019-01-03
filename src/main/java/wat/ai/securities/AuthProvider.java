package wat.ai.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import wat.ai.securities.repositories.SecurityRepository;

import java.util.Base64;
import java.util.Collections;

public class AuthProvider implements AuthenticationManager {

    final SecurityRepository securityRepository;

    @Autowired
    public AuthProvider(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return securityRepository.findLibrarianByUsernameAndPasswordHash((String) authentication.getPrincipal(),
                Base64.getEncoder().encodeToString(((String) authentication.getCredentials()).getBytes()))
                .map(user -> new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()))
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException(null));
    }
}
