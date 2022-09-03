package dat3.cars_r_us.dto;

import dat3.cars_r_us.entity.Car;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarRequest {

    private int id;
    private String brand;
    private String model;
    private double pricePerDay;
    private double bestDiscount;

    public CarRequest(Car car) {
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.pricePerDay = car.getPricePerDay();
        this.bestDiscount = car.getBestDiscount();
    }

    public Car getCarEntity() {
        Car car = new Car();

        car.setId(this.id);
        car.setBrand(this.brand);
        car.setModel(this.model);
        car.setPricePerDay(this.pricePerDay);
        car.setBestDiscount(this.bestDiscount);

        return car;
    }
}
