package wat.ai.security2.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import wat.ai.security2.AuthProvider;
import wat.ai.security2.JwtAuthorizer;
import wat.ai.security2.LoginFilter;
import wat.ai.security2.repositories.SecurityRepository;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    static final private Logger LOGGER = Logger.getLogger(SecurityConfig.class.getName());

    @Autowired
    private SecurityRepository securityRepository;
    private String secret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .addFilter(loginFilter())
                .addFilter(jwtAuthorizer())
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public LoginFilter loginFilter() {
        final LoginFilter loginFilter = new LoginFilter(secret);
        loginFilter.setAuthenticationManager(authProvider());
        loginFilter.setFilterProcessesUrl("/api/login");

        return loginFilter;
    }

    @Bean
    public AuthProvider authProvider() {
        return new AuthProvider(securityRepository);
    }

    @Bean
    public JwtAuthorizer jwtAuthorizer() {
        return new JwtAuthorizer(authProvider(), secret, securityRepository);
    }

    @PostConstruct
    public void getSecretKey() {
        Properties properties = new Properties();
        try {
            InputStream input = this.getClass().getResourceAsStream("/settings/security.properties");
            properties.load(input);
            secret = properties.getProperty("secretKey");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }


}
