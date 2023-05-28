package mk.ukim.finki.emt.usermanagement.xport.event;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.events.users.UserCreated;
import mk.ukim.finki.emt.sharedkernel.events.users.UserLogins;
import mk.ukim.finki.emt.usermanagement.domain.service.UserService;
import mk.ukim.finki.emt.usermanagement.domain.valueobjects.UserId;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserEventListener {
    private final UserService userService;

   //@KafkaListener(topics = TopicHolder.TOPIC_USER_REGISTERED);
    public void userRegistrationEvent(String jsonMessage){
        try{
            UserCreated event = DomainEvent.fromJson(jsonMessage,UserCreated.class);
            userService.registerUser(UserId.of(event.getEmail(),event.getRegistrationTime()));
        }catch(Exception e){

        }
    }
    public void userLoginEvent(String jsonMessage){
        try{
            UserLogins event = DomainEvent.fromJson(jsonMessage,UserLogins.class);
            //userService.login(UserId.of(event.getEmail(), event.getLoggedTime()));
        }catch(Exception e){

        }
    }
}
