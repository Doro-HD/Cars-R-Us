package dat3.cars_r_us.api;

import dat3.cars_r_us.dto.CarRequest;
import dat3.cars_r_us.dto.CarResponse;
import dat3.cars_r_us.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    //Security User
    @PostMapping
    private CarResponse createMember(@RequestBody CarRequest carRequest) {
        return this.carService.createCar(carRequest);
    }

    //Security Admin
    @GetMapping
    public List<CarResponse> getCars() {
        return this.carService.getCars();
    }

    //Security Admin
    @GetMapping("/{id}")
    public CarResponse findById(@PathVariable("id") int carId) {
        return this.carService.findById(carId);
    }

    //Security Admin
    @PutMapping
    public CarResponse updateCar(@RequestBody CarRequest carRequest) {
        return this.carService.updateCar(carRequest);
    }

    //Security Admin
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int carId) {
        this.carService.deleteById(carId);
    }
}
