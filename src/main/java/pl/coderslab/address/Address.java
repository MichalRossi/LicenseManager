package pl.coderslab.address;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Address {

    private String streetName;
    private Integer streetNumber;
    private Integer zipCode;
    private String city;


}
