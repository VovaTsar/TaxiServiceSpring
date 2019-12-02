package ua.company.taxi.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.company.taxi.model.mapper.ClientMapper;
import ua.company.taxi.model.repository.ClientRepository;
import ua.company.taxi.model.service.ClientService;
import ua.company.taxi.model.service.impl.ClientServiceImpl;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private ClientService clientServiceImpl;
    private LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();


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
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ClientService clientService(@Autowired ClientRepository clientRepository, @Autowired ClientMapper clientMapper) {
        clientServiceImpl = new ClientServiceImpl(clientRepository, clientMapper, passwordEncoder());
        return clientServiceImpl;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clientServiceImpl);
    }

}
