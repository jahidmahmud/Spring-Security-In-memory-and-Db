package com.security.main.config;

import com.security.main.services.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
// for annotation based role mapping in controller-check home controller
//then you no need to write antmatcher()
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserService service;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //basic auth
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/public/**").hasRole("NORMAL")
//                .antMatchers("/users/**").hasRole("ADMIN")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();

        //form based auth
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/signin").permitAll()
                .antMatchers("/public/**").hasRole("NORMAL")
                .antMatchers("/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/dologin")
                .defaultSuccessUrl("/users/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password(this.passwordEncoder().encode("1234")).roles("NORMAL");
//
//        auth.inMemoryAuthentication().withUser("king").password(this.passwordEncoder().encode("1234")).roles("ADMIN");
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        //return NoOpPasswordEncoder.getInstance();
        //encode password
        return new BCryptPasswordEncoder(10);
    }
}
