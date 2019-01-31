package pl.altkom.asc.lab.cqrs.intro.separatemodels.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private String make;
    private String plateNumber;
    private int productionYear;

    public Car copy() {
        return new Car(make, plateNumber, productionYear);
    }
}
