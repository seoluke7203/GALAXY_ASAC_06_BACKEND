package asac06.galaxy.jwt;

import asac06.galaxy.exception.JWTErrorType;
import asac06.galaxy.exception.JWTException;
import asac06.galaxy.model.User;
import asac06.galaxy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow();

        return new CustomUserDetails(user);
    }
}
