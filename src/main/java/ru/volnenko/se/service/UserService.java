package ru.volnenko.se.service;

import java.time.LocalDate;
import java.util.Collections;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.volnenko.se.api.repository.IRoleRepository;
import ru.volnenko.se.api.repository.IUserRepository;
import ru.volnenko.se.api.service.IUserService;
import ru.volnenko.se.entity.TMUser;
import ru.volnenko.se.entity.role.UserRole;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private IRoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostConstruct
    private void initUsers() {
        addUser("Anton", "APW", "NORMAL_USER");
        addUser("Bert", "BPW", "NORMAL_USER");
    }
    
    @Override
    public TMUser addUser(String loginName, String password, String roleName) {
        TMUser user = userRepository.findById(loginName).orElse(null);
        if (user != null) {
            return null;
        }
        UserRole role = roleRepository.findById(roleName).orElse(null);
        if (role == null) {
            role = new UserRole();
            role.setName(roleName);
            roleRepository.save(role);
        }
        user = new TMUser();
        user.setDateJoined(LocalDate.now());
        user.setName(loginName);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
        return user;
    }

}
