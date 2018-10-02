package com.screw.domain.customer;

import com.screw.domain.ValueObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"province", "city", "zone", "address", "postCode"}, callSuper = false)
public class Address implements ValueObject<Address> {
    private String province;
    private String city;
    private String zone;
    private String address;
    private String postCode;

    @Override
    public boolean sameValueAs(Address other) {
        return this.equals(other);
    }
}
