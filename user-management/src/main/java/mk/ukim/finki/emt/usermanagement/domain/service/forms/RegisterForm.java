package mk.ukim.finki.emt.usermanagement.domain.service.forms;

import lombok.Data;
import lombok.Getter;
import mk.ukim.finki.emt.usermanagement.domain.valueobjects.Address;

@Data
@Getter
public class RegisterForm {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Address address;
}
