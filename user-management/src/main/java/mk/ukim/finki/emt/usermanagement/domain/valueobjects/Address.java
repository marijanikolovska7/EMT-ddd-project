package mk.ukim.finki.emt.usermanagement.domain.valueobjects;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.base.ValueObject;

@Getter
public class Address implements ValueObject {
        private final String street;
        private final String city;

        private final String zipCode;

        public Address(String street, String city, String zipCode) {
            this.street = street;
            this.city = city;
            this.zipCode = zipCode;
        }
}
