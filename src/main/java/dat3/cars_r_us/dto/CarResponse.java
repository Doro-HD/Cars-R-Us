package dat3.cars_r_us.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars_r_us.entity.Car;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {

    private int id;
    private String brand;
    private String model;
    private double pricePerDay;
    private double bestDiscount;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDate dateCreated;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDate lastDateEdited;

    public CarResponse(Car car) {
        this(car, false);
    }

    public CarResponse(Car car, boolean includeAll) {
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.pricePerDay = car.getPricePerDay();
        this.bestDiscount = car.getBestDiscount();

        if (includeAll) {
            this.dateCreated = car.getDateCreated();
            this.lastDateEdited = car.getLastDateEdited();
        }
    }
}
