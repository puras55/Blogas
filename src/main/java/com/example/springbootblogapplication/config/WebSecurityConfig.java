package com.example.springbootblogapplication.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions().disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/webjars/**").permitAll() //Leidžia visiems naudotojams pasiekti statinius resursus (CSS, JavaScript, paveikslėlius, šriftus ir webjars).
                        .requestMatchers("/").permitAll()  //. Leidžia visiems pasiekti pagrindinį puslapį.
                        .requestMatchers("/rss/**").permitAll() //Leidžia visiems pasiekti RSS kanalus. "Really Simple Syndication"
                        .requestMatchers("/register/**").permitAll() //Leidžia visiems pasiekti registracijos puslapius.
                        .requestMatchers("/posts/**").permitAll() //Leidžia visiems pasiekti įrašų puslapius.
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated()

                     //   .anyRequest().authenticated() - Visoms kitoms užklausoms reikalinga autentifikacija (naudotojas turi būti prisijungęs).
                )
                .formLogin(form -> form
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error")
                    .permitAll()
                );
        //.formLogin(form -> form ...) - Ši dalis konfigūruoja prisijungimo formą:
        //
        //.loginPage("/login") - Nurodo URL, kuriame yra prisijungimo puslapis.
        //.loginProcessingUrl("/login") - Nurodo URL, kuriame vyks prisijungimo apdorojimas.
        //.usernameParameter("email") - Nurodo,


        return http.build();
    }
}
