package de.hsrm.mi.web.bratenbank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.bratenbank.benutzer.Benutzer;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzerRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired BenutzerUserDetailService bs;

    @Bean PasswordEncoder getPasswordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authmanagerbuilder) throws Exception{
      //  PasswordEncoder pwenc = getPasswordEncoder();

        authmanagerbuilder.userDetailsService(bs).passwordEncoder(getPasswordEncoder());


      /*  authmanagerbuilder.inMemoryAuthentication()
            .withUser("friedfert")
            .password(pwenc.encode("dingdong"))
            .roles("USER")
        .and()
            .withUser("joghurta")
            .password(pwenc.encode("geheim123"))
            .roles("USER");*/

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
            .antMatchers("/css/*").permitAll()
            .antMatchers("/benutzer/*", "/benutzer").permitAll()
            .antMatchers("/api/*").permitAll()
            .antMatchers("/stompbroker").permitAll()
            .antMatchers("/logout").permitAll()
            .antMatchers("/braten/*").authenticated()
            .anyRequest().authenticated()
        .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/braten/angebot")
            .permitAll()
        .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .permitAll()
        .and()
            .csrf()
            .disable();
    

    }
    @Service
    public class BenutzerUserDetailService implements UserDetailsService {
    @Autowired BenutzerRepository br;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Benutzer ben = br.findByLoginname(username);
        if(ben == null){ throw new UsernameNotFoundException(username);
        }
        return org.springframework.security.core.userdetails.User
            .withUsername(username)
            .password(getPasswordEncoder().encode(ben.getPasswort()))
            .roles("USER")
            .build();        
    }
    
}
    
}
