package com.evgen.timetable.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.evgen.timetable.Constants;
import com.evgen.timetable.properties.JwtProperties;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private final JwtProperties jwtProperties;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      Authentication authentication) {
    //setting our's app specific jwt
    httpServletResponse.setHeader(Constants.AUTH_TOKEN_HEADER_NAME, jwtTokenProvider.generateToken(authentication));
    httpServletResponse.setHeader(Constants.AUTH_TYPE_HEADER_NAME, jwtProperties.getPrefix());
  }
}
