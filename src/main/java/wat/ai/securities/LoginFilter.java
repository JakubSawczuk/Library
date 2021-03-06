package wat.ai.securities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import wat.ai.models.entities.Librarian;
import wat.ai.securities.dtos.LibrarianDetails;
import wat.ai.securities.dtos.UserLogin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    static final private Logger LOGGER = Logger.getLogger(LoginFilter.class.getName());
    private final String secret;


    @Autowired
    private ObjectMapper objectMapper;

    public LoginFilter(String secret) {
        this.secret = secret;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLogin userLogin = objectMapper.readValue(request.getInputStream(), UserLogin.class);
            return getAuthenticationManager()
                    .authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Librarian user = ((Librarian) authResult.getPrincipal());
        final String token = JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(secret.getBytes()));
        ModelMapper modelMapper = new ModelMapper();

        LibrarianDetails librarianDetails = modelMapper.map(user, LibrarianDetails.class);
        librarianDetails.setToken("Bearer " + token);

        response.getWriter().write(objectMapper.writeValueAsString(librarianDetails));
        response.getWriter().flush();
        response.getWriter().close();

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, failed.getMessage());
    }
}