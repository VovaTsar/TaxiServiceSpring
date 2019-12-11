package ua.company.taxi.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.company.taxi.model.service.ClientService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ClientService clientService;
    private final BCryptPasswordEncoder encoder;
    private final LoginSuccessHandler loginSuccessHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index", "/register", "/register/**", "/js/**", "/css/**", "/sources/**", "/login/**").permitAll()
                .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("ROLE_USER")

                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").successHandler(loginSuccessHandler).permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .and().exceptionHandling()
                .accessDeniedPage("/error");

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clientService)
                .passwordEncoder(encoder);
    }

}
