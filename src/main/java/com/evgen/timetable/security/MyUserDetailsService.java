package com.evgen.timetable.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.model.entity.User;
import com.evgen.timetable.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user = userRepository.findByLoginIgnoreCase(login)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("user with username %s not found", login)));

    return UserPrincipal.create(user);
  }

  // This method is used by JWTAuthenticationFilter
  @Transactional(readOnly = true)
  public UserDetails loadUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow(
        () -> new UsernameNotFoundException("User not found with id : " + id));

    return UserPrincipal.create(user);
  }

}