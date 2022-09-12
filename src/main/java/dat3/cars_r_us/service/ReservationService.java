package dat3.cars_r_us.service;

import dat3.cars_r_us.entity.Car;
import dat3.cars_r_us.entity.Member;
import dat3.cars_r_us.entity.Reservation;
import dat3.cars_r_us.repository.CarRepository;
import dat3.cars_r_us.repository.MemberRepository;
import dat3.cars_r_us.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final MemberRepository memberRepository;

    public ReservationService(
            ReservationRepository reservationRepository,
            CarRepository carRepository,
            MemberRepository memberRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }

    public void reserveCar(String username, int carId, LocalDate rentalDate) {
        boolean reservationExists = this.reservationRepository.existsByCar_IdAndRentalDate(carId, rentalDate);

        if (reservationExists) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Reservation with car id " + carId + ", already exists for date " + rentalDate
            );
        }
        Reservation reservation = new Reservation();
        reservation.setRentalDate(rentalDate);

        Car car = this.carRepository.findById(carId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with id + " + carId + ", not found")
        );
        Member member = this.memberRepository.findById(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with username " + username + ", not found")
        );

        reservation.setCar(car);
        reservation.setMember(member);
        this.reservationRepository.save(reservation);


    }
}
