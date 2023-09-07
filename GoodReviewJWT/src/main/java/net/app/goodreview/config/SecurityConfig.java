package net.app.goodreview.config;

import net.app.goodreview.security.jwt.JwtConfigurer;
import net.app.goodreview.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/admin/**";

    private static final String LOGIN_ENDPOINT = "/login";

    private static final String REGISTER_ENDPOINT = "/register";

    private static final String VERIFY_ENDPOINT = "/login/is-verify";

    private static final String DASHBOARD = "/dashboard";

    private static final String REVIEWS_ENDPOINT = "/reviews/**";

    private static final String COMPANY_ENDPOINT = "/companies/**";

    private static final String CATEGORY_ENDPOINT = "/categories/**";

    private static final String USER_ENDPOINT = "/users/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT, REGISTER_ENDPOINT,
                        VERIFY_ENDPOINT,
                        DASHBOARD, REVIEWS_ENDPOINT, COMPANY_ENDPOINT, CATEGORY_ENDPOINT, USER_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
                //.and().exceptionHandling().accessDeniedHandler();
    }
}
