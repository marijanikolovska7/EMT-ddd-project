package mk.ukim.finki.emt.usermanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.emt.sharedkernel.base.AbstractEntity;
import mk.ukim.finki.emt.usermanagement.domain.valueobjects.Address;
import mk.ukim.finki.emt.usermanagement.domain.valueobjects.UserId;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
public class User extends AbstractEntity<UserId> {

    private String email;
    private String password;

    private String firstName;

    private String lastName;


    @Embedded
    private Address address;

    public User() {
        super(UserId.randomId(UserId.class));
    }

    public static User build(String email, String password,String firstName,String lastName,Address address){
        User u = new User();
        u.email = email;
        u.password = password;
        u.firstName = firstName;
        u.lastName = lastName;
        return u;
    }
}