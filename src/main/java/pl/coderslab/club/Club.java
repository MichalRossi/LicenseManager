package pl.coderslab.club;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.pl.NIP;
import pl.coderslab.commons.AgeCategory;
import pl.coderslab.address.Address;
import pl.coderslab.coach.Coach;
import pl.coderslab.player.Player;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "clubs")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "[0-9]{9}")
    private String phoneNumber;

    @URL
    private String webAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "streetName", column = @Column(name = "address_street_name")),
            @AttributeOverride( name = "streetNumber", column = @Column(name = "address_street_number")),
            @AttributeOverride( name = "zipCode", column = @Column(name = "address_zip_code")),
            @AttributeOverride( name = "city", column = @Column(name = "address_city"))
    })
    private Address address;

    @NIP
    private String nip;

    @Enumerated(EnumType.STRING)
    private AgeCategory ageCategory;

    @ManyToMany (mappedBy = "clubs")
    private List<Coach> coaches= new ArrayList<>();


    @ManyToMany (mappedBy = "clubs")
    private List<Player> players= new ArrayList<>();

    public Club() {
    }
}
