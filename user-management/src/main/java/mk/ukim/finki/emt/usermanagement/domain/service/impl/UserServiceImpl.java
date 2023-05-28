package mk.ukim.finki.emt.usermanagement.domain.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.usermanagement.domain.exceptions.DuplicateUser;
import mk.ukim.finki.emt.usermanagement.domain.exceptions.InvalidPassword;
import mk.ukim.finki.emt.usermanagement.domain.exceptions.UserNotFound;
import mk.ukim.finki.emt.usermanagement.domain.models.User;
import mk.ukim.finki.emt.usermanagement.domain.repository.UserRepository;
import mk.ukim.finki.emt.usermanagement.domain.service.UserService;
import mk.ukim.finki.emt.usermanagement.domain.service.forms.RegisterForm;
import mk.ukim.finki.emt.usermanagement.domain.valueobjects.Address;
import mk.ukim.finki.emt.usermanagement.domain.valueobjects.UserId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterForm registerForm) {
        if(userRepository.findByEmail(registerForm.getEmail()).equals(registerForm.getEmail())){
            throw new DuplicateUser();
        }
        User newUser = new User(
        );
        return userRepository.save(newUser);
    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(!passwordEncoder.equals(user.getEmail())){
            throw new InvalidPassword();
        }
        return user;
    }

    @Override
    public void updateUser(UserId id, String firstName, String lastName, String email, Address address) {
        User user = userRepository.findById(id).orElseThrow(UserNotFound::new);
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAddress(new Address(user.getAddress().getStreet(),user.getAddress().getCity(),user.getAddress().getZipCode()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(UserId id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFound::new);
        userRepository.delete(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
