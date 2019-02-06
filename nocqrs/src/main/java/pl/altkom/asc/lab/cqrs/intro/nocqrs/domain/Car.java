package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private String make;
    private String plateNumber;
    private int productionYear;

    Car copy() {
        return new Car(make, plateNumber, productionYear);
    }
}
