package mk.ukim.finki.emt.usermanagement.domain.repository;

import mk.ukim.finki.emt.usermanagement.domain.models.User;
import mk.ukim.finki.emt.usermanagement.domain.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UserId> {
    User findByEmail(String email);
}

