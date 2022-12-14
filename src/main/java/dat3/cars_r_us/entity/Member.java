package dat3.cars_r_us.entity;

import dat3.security.entity.UserWithRoles;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
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
    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<Reservation> reservations = new ArrayList<>();

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

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
