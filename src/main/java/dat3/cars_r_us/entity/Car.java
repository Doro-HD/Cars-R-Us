package dat3.cars_r_us.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private double pricePerDay;
    @Column(nullable = false)
    private double bestDiscount;
    @CreationTimestamp
    private LocalDate dateCreated;
    @UpdateTimestamp
    private LocalDate lastDateEdited;
    @OneToMany(mappedBy = "car", cascade = CascadeType.PERSIST)
    private List<Reservation> reservations = new ArrayList<>();

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
