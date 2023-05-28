package mk.ukim.finki.emt.usermanagement.domain.valueobjects;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.base.DomainObjectId;
import mk.ukim.finki.emt.usermanagement.domain.service.forms.RegisterForm;

import java.time.LocalDateTime;

public class UserId extends DomainObjectId {
    private UserId()
    {
        super(UserId.randomId(UserId.class).getId());
    }

    public UserId(@NonNull String uuid) {
        super(uuid);
    }

    public static RegisterForm of(String uuid, LocalDateTime registrationTime) {
        UserId userId = new UserId(uuid);
        return userId;
    }
}
