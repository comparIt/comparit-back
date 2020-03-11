package com.pepit.security;

import com.pepit.constants.Routes;
import com.pepit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.RouteMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("Authorization");
        configuration.addAllowedHeader("Content-type");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers(Routes.AUTH).permitAll()
                .antMatchers(Routes.HEARTBEAT).permitAll()
                .antMatchers(Routes.USER + "/saveUser").permitAll()
                .antMatchers(Routes.USER + "/currentUser").authenticated()
                .antMatchers(Routes.PRODUCT + "/**").permitAll()
                .antMatchers(Routes.COMPANY+ "/supplier").permitAll()
                .antMatchers(Routes.COMPANY+ "/getCategoriesOfWebsite").permitAll()
                .antMatchers(Routes.SWAGGER_DOCS).permitAll()
                .antMatchers(Routes.FAVICON).permitAll()
                .antMatchers(Routes.SWAGGER_DOCS_INTERFACE).permitAll()
                .antMatchers(Routes.SWAGGER_HTML).permitAll()
                .antMatchers(Routes.SWAGGER_RESSOURCES).permitAll()
                .antMatchers(Routes.SWAGGER_SECURITY).permitAll()
                .antMatchers(Routes.SWAGGER_WEBJAR).permitAll()
                .antMatchers(HttpMethod.GET, Routes.REVIEW + "/**").permitAll()
                .antMatchers(HttpMethod.GET, Routes.USER + "/**").permitAll()
                .antMatchers(HttpMethod.GET, Routes.WEBSITE_CONFIG+"/*").permitAll()
                .antMatchers(HttpMethod.PUT, Routes.WEBSITE_CONFIG+"/saveWebsiteConfiguration").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
