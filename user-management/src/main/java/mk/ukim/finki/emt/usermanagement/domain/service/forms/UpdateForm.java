package mk.ukim.finki.emt.usermanagement.domain.service.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class UpdateForm {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
