package dat3.cars_r_us.service;

import dat3.cars_r_us.dto.CarRequest;
import dat3.cars_r_us.dto.CarResponse;
import dat3.cars_r_us.entity.Car;
import dat3.cars_r_us.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarResponse createCar(CarRequest carRequest) {
        Car car = carRequest.getCarEntity();

        car = this.carRepository.save(car);

        return new CarResponse(car);
    }

    public List<CarResponse> getCars() {
        List<Car> cars = this.carRepository.findAll();

        return cars.stream().map(car -> new CarResponse(car)).toList();
    }

    public CarResponse findById(int id) {
        CarResponse carResponse;
        Optional<Car> carOptional = this.carRepository.findById(id);

        if (carOptional.isPresent()) {
            carResponse = new CarResponse(carOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find car with id " + id);
        }

        return carResponse;
    }

    public CarResponse updateCar(CarRequest carRequest) {
        Car car = carRequest.getCarEntity();

        car = this.carRepository.save(car);

        return new CarResponse(car);
    }

    public void deleteById(int id) {
        this.carRepository.deleteById(id);
    }
}
