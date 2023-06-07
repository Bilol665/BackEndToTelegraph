package uz.pdp.backendtotelegraph.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.backendtotelegraph.exceptions.DataNotFoundException;
import uz.pdp.backendtotelegraph.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserEntitiesByUsername(username)
                .orElseThrow(
                        () -> new DataNotFoundException("User not found!")
                );
    }
}
