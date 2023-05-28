package mk.ukim.finki.emt.usermanagement.domain.service.forms;


import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginForm {

    private String email;

    private String password;


}
