package com.evgen.timetable.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.evgen.timetable.model.name.RoleName;
import com.evgen.timetable.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService myUserDetailsService;
  private final AuthenticationSuccessHandler authenticationSuccessHandler;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  public SecurityConfig(UserDetailsService myUserDetailsService,
      AuthenticationSuccessHandler authenticationSuccessHandler, JwtAuthenticationFilter jwtAuthenticationFilter) {
    super(true);
    this.myUserDetailsService = myUserDetailsService;
    this.authenticationSuccessHandler = authenticationSuccessHandler;
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }

  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(myUserDetailsService)
        .passwordEncoder(encoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
        http
                .logout()
                .disable();
        http
                .cors();
        http
                .csrf()
                .disable();
        http
                .exceptionHandling()
                .and()
                .servletApi();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterAt(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .authorizeRequests()
                //user and admin
                .antMatchers(HttpMethod.GET,  "/api/groups/**").hasAnyRole(RoleName.STUDENT.name(), RoleName.ADMIN.name())
                .antMatchers(HttpMethod.GET,  "/api/lessons/**").hasAnyRole(RoleName.STUDENT.name(), RoleName.ADMIN.name())
                .antMatchers(HttpMethod.GET,  "/api/lessonDays/**").hasAnyRole(RoleName.STUDENT.name(), RoleName.ADMIN.name())
                .antMatchers(HttpMethod.GET,  "/api/students/**").hasAnyRole(RoleName.STUDENT.name(), RoleName.ADMIN.name())
                .antMatchers(HttpMethod.GET,  "/api/teachers/**").hasAnyRole(RoleName.STUDENT.name(), RoleName.ADMIN.name())
                .antMatchers(HttpMethod.GET,  "/api/timeTables/**").hasAnyRole(RoleName.STUDENT.name(), RoleName.ADMIN.name())
                .antMatchers(HttpMethod.GET,  "/api/users/**").hasAnyRole(RoleName.STUDENT.name(), RoleName.ADMIN.name())
                //admin
                .antMatchers("/api/groups/**").hasRole(RoleName.ADMIN.name())
                .antMatchers("/api/lessons/**").hasRole(RoleName.ADMIN.name())
                .antMatchers("/api/lessonDays/**").hasRole(RoleName.ADMIN.name())
                .antMatchers("/api/roles").hasRole(RoleName.ADMIN.name())
                .antMatchers("/api/students/**").hasRole(RoleName.ADMIN.name())
                .antMatchers("/api/teachers/**").hasRole(RoleName.ADMIN.name())
                .antMatchers("/api/timeTables/**").hasRole(RoleName.ADMIN.name())
                .antMatchers("/api/users/**").hasRole(RoleName.ADMIN.name())
                .antMatchers("/api/user-role/**").hasRole(RoleName.ADMIN.name());

        // @formatter:on
  }

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder(11);
  }

  @Bean
  UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
    UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
    usernamePasswordAuthenticationFilter.setRequiresAuthenticationRequestMatcher(
        new AntPathRequestMatcher("/api/login", HttpMethod.POST.name()));
    usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
    usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
    usernamePasswordAuthenticationFilter.setAllowSessionCreation(false);
    return usernamePasswordAuthenticationFilter;
  }

  @Bean
  public FilterRegistrationBean jwtFilterRegistration() {
    final FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(jwtAuthenticationFilter);
    registrationBean.setEnabled(false);
    return registrationBean;
  }

  @Bean
  public FilterRegistrationBean usernamePasswordFilterRegistration() throws Exception {
    final FilterRegistrationBean<UsernamePasswordAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(usernamePasswordAuthenticationFilter());
    registrationBean.setEnabled(false);
    return registrationBean;
  }

}