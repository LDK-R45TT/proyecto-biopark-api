package com.bootcamp.biopark.infraestructure.security;


import com.bootcamp.biopark.application.port.out.UserSessionRepositoryPort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserSessionRepositoryPort users;

    public CustomUserDetailsService(UserSessionRepositoryPort users) {
        this.users = users;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return users.findByEmail(email)
                .map(SecurityUser::toSecurityUserFromDomain)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }
}
