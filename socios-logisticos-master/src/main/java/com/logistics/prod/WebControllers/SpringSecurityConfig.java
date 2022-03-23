package com.logistics.prod.WebControllers;


import com.logistics.prod.Servlets.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${partner}")
    private String vID;

    @Value("${passwordPartner}")
    private String pass;

    // @Value("${}")


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/*.css", "/js/*.js").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .loginPage("/login")
                .defaultSuccessUrl("/listPartners",true)
                .permitAll()
                .and()
                .logout()
                .permitAll();

    }
    //@Override
    public void configure2(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/restrictions");
         }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {


        String password = passwordEncoder.encode(pass);
        auth.inMemoryAuthentication()

                .withUser(vID).password(password).roles("admin");
        System.out.println(password);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}