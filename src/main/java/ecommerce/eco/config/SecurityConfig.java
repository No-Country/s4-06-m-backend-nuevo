package ecommerce.eco.config;

import ecommerce.eco.config.filters.CustomAccessDeniedHandler;
import ecommerce.eco.config.filters.CustomAuthenticationEntryPoint;
import ecommerce.eco.config.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(publicEndpoint).permitAll()
                /*Auth*/
                .antMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers(HttpMethod.GET, "/auth/logout").permitAll()
                //Category
                .antMatchers(HttpMethod.POST, "/category/create").permitAll()
                .antMatchers(HttpMethod.DELETE, "/category/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/category/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/category/description").permitAll()
                .antMatchers(HttpMethod.GET, "/category/productWith/{newOffersId}").permitAll()
                .antMatchers(HttpMethod.GET,"/category/product/{lightningDealId}").permitAll()
                .antMatchers(HttpMethod.GET,"/category/all").permitAll()
                .antMatchers(HttpMethod.GET,"/category/filter").permitAll()
                /*User*/
                .antMatchers(HttpMethod.GET, "/user/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/user/all").permitAll()
                .antMatchers(HttpMethod.DELETE, "/user/{id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/user/update").permitAll()
                // Review
                .antMatchers(HttpMethod.POST, "/review/add").permitAll()
                .antMatchers(HttpMethod.PUT, "/review/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/review/{idReview}").permitAll()
                .antMatchers(HttpMethod.GET,"/review/all").permitAll()
                /*Product*/
                .antMatchers(HttpMethod.POST, "/product/add").permitAll()
                .antMatchers(HttpMethod.GET, "/product/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/product/all").permitAll()
                .antMatchers(HttpMethod.DELETE, "/product/{id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/product/update").permitAll()
                .antMatchers(HttpMethod.GET, "/product/filter/details").permitAll()
                .antMatchers(HttpMethod.GET,"/product/review/{idProduct}").permitAll()
                /*Color*/
                .antMatchers(HttpMethod.GET, "/color/all").permitAll()
                /*Size*/
                .antMatchers(HttpMethod.GET, "/size/all").permitAll()
                /*Paypal*/
                .antMatchers(HttpMethod.POST, "/paypal/pay").permitAll()
                .antMatchers(HttpMethod.GET, "/paypal/success").permitAll()
                .antMatchers(HttpMethod.GET, "/paypal/cancel").permitAll()
                .antMatchers(HttpMethod.GET, "/paypal/home").permitAll()
                /*paypalRest*/
                .antMatchers(HttpMethod.POST,"/paypalRest/pay").permitAll()
                .antMatchers(HttpMethod.GET, "/paypalRest/success").permitAll()
                .antMatchers(HttpMethod.GET, "/paypalRest/cancel").permitAll()
                .antMatchers(HttpMethod.GET, "/paypalRest/home").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint());

        // For development environment only
        http.headers().frameOptions().disable();
        return http.build();
    }

    private static final String[] publicEndpoint = {
            "/swagger-resources/**",
            "/swagger-ui/**", "/v2/api-docs",
            "/v3/api-docs",
            "/api/docs",
            "/api/docs/**",
            "/api/docs/swagger-ui",
            "/swagger-ui.html",
            "/**/swagger-ui/**",
            "/swagger-ui"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // For development enviroment only
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/h2-console/**");
    }
}
