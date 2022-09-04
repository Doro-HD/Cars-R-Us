package dat3.cars_r_us.service;

import dat3.cars_r_us.dto.CarRequest;
import dat3.cars_r_us.dto.CarResponse;
import dat3.cars_r_us.entity.Car;
import dat3.cars_r_us.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarServiceTest {

    private CarService carService;
    private static CarRepository carRepositoryStatic;
    private static int carCount;

    @BeforeAll
    static void setup(@Autowired CarRepository carRepository) {
        carRepositoryStatic = carRepository;
        carRepositoryStatic.deleteAll();

        List<Car> cars = List.of(
                new Car("Toyota", "Corolla"),
                new Car("Suzuki", "Civic")
        );

        carCount = cars.size();

        carRepositoryStatic.saveAll(cars);
    }

    @BeforeEach
    void setCarService() {
        this.carService = new CarService(carRepositoryStatic);
    }

    @Test
    void createCar() {
        Car car = new Car("test", "test");
        this.carService.createCar(new CarRequest(car));

        List<CarResponse> carResponses = this.carService.getCars();

        assertEquals(carResponses.size(), carCount + 1);
    }

    @Test
    void getCars() {
        List<CarResponse> cars = this.carService.getCars();

        assertEquals(cars.size(), carCount);
    }

    @Test
    void updateCar() {
        final String brand = "Actual Brand";
        Car car = new Car("Test", "Test");
        CarRequest carRequest = new CarRequest(car);

        CarResponse carResponse = this.carService.createCar(carRequest);
        carRequest.setId(carResponse.getId());
        carRequest.setBrand(brand);

        this.carService.updateCar(carRequest);
        carResponse = this.carService.findById(carResponse.getId());

        assertEquals(brand, carResponse.getBrand());
    }

    @Test
    void deleteById() {
        Car car = new Car("test","test");
        CarRequest carRequest = new CarRequest(car);

        CarResponse carResponse = this.carService.createCar(carRequest);
        final int expectedId = carResponse.getId();

        this.carService.deleteById(expectedId);

        assertThrows(ResponseStatusException.class, () -> {
            this.carService.findById(carResponse.getId());
        });


    }
}