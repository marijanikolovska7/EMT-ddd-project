package mk.ukim.finki.emt.sharedkernel.events.users;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.events.DomainEvent;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
public class UserLogins extends DomainEvent {
    private String userId;
    private String email;
    private LocalDateTime loggedTime;

    public UserLogins(String userId, String email, LocalDateTime loggedTime) {
        super(TopicHolder.TOPIC_USER_LOGGED_IN);
        this.userId = userId;
        this.email = email;
        this.loggedTime = loggedTime;
    }

    public UserLogins(String topic) {
        super(TopicHolder.TOPIC_USER_LOGGED_IN);
    }
}
