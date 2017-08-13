package app.Services;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.Form.UserRegistration;
import app.Entity.User.User;
import app.Entity.User.UserRepository;
import app.Entity.Role.Role;
import app.Entity.Role.RoleRepository;

@Service("userService")
public class UserService {

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void userRegistration(UserRegistration userRegistration) {
        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode(userRegistration.getPassword()));
        user.setUsername(userRegistration.getUsername());
        user.setEmail(userRegistration.getEmail());
        Role userRole = roleRepository.findByName("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

}
