package mk.ukim.finki.emt.sharedkernel.events.users;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.events.DomainEvent;

import java.time.LocalDateTime;

@Getter
public class UserCreated extends DomainEvent {
  private final String email;
  private final LocalDateTime registrationTime;

  public UserCreated(String email, LocalDateTime registrationTime, String email1, String email2, LocalDateTime registrationTime1){
      super(TopicHolder.TOPIC_USER_REGISTERED);
      this.email = email;
      this.registrationTime = registrationTime;
  }

}
