package mk.ukim.finki.emt.usermanagement.domain.service;

import mk.ukim.finki.emt.usermanagement.domain.models.User;
import mk.ukim.finki.emt.usermanagement.domain.service.forms.RegisterForm;
import mk.ukim.finki.emt.usermanagement.domain.valueobjects.Address;
import mk.ukim.finki.emt.usermanagement.domain.valueobjects.UserId;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(RegisterForm registerForm);

    User login(String email, String password);

    void updateUser(UserId id, String firstName, String lastName, String email, Address address);


    void deleteUser(UserId id);

    User findByEmail(String email);
    List<User> findAll();

}
