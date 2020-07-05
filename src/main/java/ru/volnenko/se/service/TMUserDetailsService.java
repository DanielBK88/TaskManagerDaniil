package ru.volnenko.se.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.volnenko.se.api.repository.IUserRepository;
import ru.volnenko.se.entity.TMUser;
import ru.volnenko.se.entity.role.UserRole;

@Service("userDetailsService")
@Transactional
public class TMUserDetailsService implements UserDetailsService {
    
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        TMUser user = userRepository.findById(userName).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        return User.builder()
                .username(user.getName())
                .password(user.getPasswordHash())
                .roles(user.getRoles().stream().map(UserRole::getName).toArray(String[]::new))
                .build();
    }

}
