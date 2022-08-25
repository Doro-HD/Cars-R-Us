package dat3.cars_r_us.repository;

import dat3.cars_r_us.entity.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;
    private static int carId1;
    private static int carId2;
    private static int carId3;

    @BeforeAll
    public static void setupData(@Autowired CarRepository carRepository) {
        Car car1 = new Car("Toyota", "Corolla");
        Car car2 = new Car("Suzuki", "Test");
        Car car3 = new Car("Honda", "Test");

        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);

        carId1 = car1.getId();
        carId2 = car2.getId();
        carId3 = car3.getId();
    }

    @Test
    public void findById() {
        Optional<Car> car1 = this.carRepository.findById(carId1);
        Optional<Car> car2 = this.carRepository.findById(carId2);
        Optional<Car> car3 = this.carRepository.findById(carId3);


    }

}