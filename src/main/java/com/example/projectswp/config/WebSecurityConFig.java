package com.example.projectswp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConFig extends WebSecurityConfigurerAdapter {
    @Autowired
    private FirebaseAuthenticationFilter firebaseAuthenticationFilter;

    @Autowired
    private AuthorizationFilter authorizationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(firebaseAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().antMatchers(HttpMethod.GET,"/api/Items/**", "/api/Category/**", "/api/SubCategory/**", "/api/blogs/**", "/api/category-sub/**", "/api/blogcategory/**", "/api/comment/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/useraccount/login").permitAll()
                .antMatchers("/swagger-ui.html/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .cors();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().mvcMatchers(HttpMethod.OPTIONS, "/**");
//        web.ignoring().mvcMatchers("/swagger-ui.html/**", "/configuration/**", "/swagger-resources/**", "/v2/api-docs","/webjars/**");
//    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // Cho ph??p truy c???p t??? t???t c??? c??c ngu???n
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")); // Cho ph??p c??c ph????ng th???c
        configuration.setAllowedHeaders(Arrays.asList("*")); // Cho ph??p t???t c??? c??c header
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
