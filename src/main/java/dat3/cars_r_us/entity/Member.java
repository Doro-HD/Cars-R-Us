package dat3.cars_r_us.entity;

import dat3.security.entity.UserWithRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends UserWithRoles {

    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private boolean approved;
    private int ranking;

    public Member(
            String username,
            String email,
            String password,
            String firstName,
            String lastName,
            String street,
            String city,
            String zip
    ) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    };

    public Member(
            String username,
            String email,
            String password,
            String firstName,
            String lastName,
            String street,
            String city,
            String zip,
            boolean approved,
            int ranking
    ) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.approved = approved;
        this.ranking = ranking;
    }
}
