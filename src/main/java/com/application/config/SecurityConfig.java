package com.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@ComponentScan("com.application")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable();
       http.authorizeRequests().antMatchers("/login").permitAll();
       http.authorizeRequests().antMatchers("/main").access("hasRole('ROLE_ADMIN')");
       http.authorizeRequests().antMatchers("/main/add-order").access("hasRole('ROLE_ADMIN')");
       http.authorizeRequests().antMatchers("/main/add-product").access("hasRole('ROLE_ADMIN')");
       http.authorizeRequests().antMatchers("/main/update-order").access("hasRole('ROLE_ADMIN')");
       http.authorizeRequests().antMatchers("/main/update-product").access("hasRole('ROLE_ADMIN')");
       http.authorizeRequests().and().formLogin()
               .loginPage("/login")
               .defaultSuccessUrl("/main")
               .failureUrl("/login?error=true")
               .usernameParameter("username")
               .passwordParameter("password")
               .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
    }
}
