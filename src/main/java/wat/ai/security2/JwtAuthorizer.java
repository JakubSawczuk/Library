package wat.ai.security2;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import wat.ai.models.entities.Librarian;
import wat.ai.security2.repositories.SecurityRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class JwtAuthorizer extends BasicAuthenticationFilter {

    private final String secret;
    private final SecurityRepository securityRepository;

    public JwtAuthorizer(AuthenticationManager authenticationManager, String secret, SecurityRepository securityRepository) {
        super(authenticationManager);
        this.secret = secret;
        this.securityRepository = securityRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String authorization = request.getHeader("Authorization");

        if (StringUtils.isBlank(authorization) || !authorization.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final UsernamePasswordAuthenticationToken auth = getAuth(request);

        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken getAuth(HttpServletRequest request) {
        final String token = request.getHeader("Authorization");

        final String subject = JWT.require(Algorithm.HMAC256(secret.getBytes()))
                .build()
                .verify(token.replace("Bearer ", ""))
                .getSubject();

        if (StringUtils.isNotBlank(subject)) {
            final Optional<Librarian> user = securityRepository.findByUsername(subject);
            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        }

        return null;
    }

}
