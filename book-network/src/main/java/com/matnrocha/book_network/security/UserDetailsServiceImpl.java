package com.matnrocha.book_network.security;

import com.matnrocha.book_network.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String UserEmail) throws UsernameNotFoundException {
        return repository.findByEmail(UserEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
