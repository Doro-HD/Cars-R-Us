package dat3.cars_r_us.repository;

import dat3.cars_r_us.entity.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private static CarRepository carRepository;
    private static int carId1;
    private static int carId2;
    private static int carId3;

    @BeforeAll
    public static void setupData(@Autowired CarRepository carRepository) {
        CarRepositoryTest.carRepository = carRepository;
        CarRepositoryTest.carRepository.deleteAll();

        Car car1 = new Car("Toyota", "Corolla");
        Car car2 = new Car("Suzuki", "Test");
        Car car3 = new Car("Honda", "Test");

        CarRepositoryTest.carRepository.save(car1);
        CarRepositoryTest.carRepository.save(car2);
        CarRepositoryTest.carRepository.save(car3);

        carId1 = car1.getId();
        carId2 = car2.getId();
        carId3 = car3.getId();
    }

    @Test
    public void findById() {
        Optional<Car> car1 = carRepository.findById(carId1);
        Optional<Car> car2 = carRepository.findById(carId2);
        Optional<Car> car3 = carRepository.findById(carId3);

        assertEquals(car1.orElseThrow().getId(), carId1);
        assertEquals(car2.orElseThrow().getId(), carId2);
        assertEquals(car3.orElseThrow().getId(), carId3);
    }

    @Test
    public void findAll() {
        Iterable<Car> cars = carRepository.findAll();
        Iterator<Car> carIterator = cars.iterator();

        int length = 0;
        while (carIterator.hasNext()) {
            length += 1;
            carIterator.next();
        }

        assertEquals(length, 3);
    }

}