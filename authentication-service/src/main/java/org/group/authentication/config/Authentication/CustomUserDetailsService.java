package org.group.authentication.config.Authentication;

import org.group.authentication.exception.wrapper.ResourceNotFoundException;
import org.group.authentication.model.Users;
import org.group.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public CustomUserDetails loadUserByUsername( String email) throws UsernameNotFoundException {
        // Use Optional directly from the repository method
        Users user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException (
                        String.format("User not found with email: %s", email)
                ));
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
	    return CustomUserDetails.builder().id(user.getId()).email(user.getEmail()).password(user.getPassword()).build();
    }

}