package dat3.cars_r_us.configuration;

import dat3.cars_r_us.entity.Car;
import dat3.cars_r_us.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class SetupCars implements CommandLineRunner {

    private CarRepository carRepository;

    public SetupCars(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Car car1 = new Car();
        car1.setBrand("Toyota");
        car1.setModel("Yaris");
        car1.setPricePerDay(1000);
        car1.setBestDiscount(500);

        Car car2 = new Car();
        car2.setBrand("Toyota");
        car2.setModel("Corrolla");
        car2.setPricePerDay(500);
        car2.setBestDiscount(300);

        this.carRepository.save(car1);
        this.carRepository.save(car2);
    }
}
