package pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders;

import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Car;

public class CarsTestDataBuilder {

    public static final String OLD_FORD_FOCUS_PLATE_NUMBER = "WAW1010";
    public static final String OLD_SKODA_FABIA_PLATE_NUMBER = "RZN1213";

    public static Car oldFordFocus() {
        return new Car("Ford Focus", OLD_FORD_FOCUS_PLATE_NUMBER, 2005);
    }

    public static Car oldSkodaFabia() {
        return new Car("Skoda Fabia", OLD_SKODA_FABIA_PLATE_NUMBER, 2009);
    }
}
