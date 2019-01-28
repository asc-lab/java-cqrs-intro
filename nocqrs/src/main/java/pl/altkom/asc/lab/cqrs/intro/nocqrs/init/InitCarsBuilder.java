package pl.altkom.asc.lab.cqrs.intro.nocqrs.init;

import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Car;

class InitCarsBuilder {
    static Car oldFordFocus() {
        return new Car("Ford Focus", "WAW1010", 2005);
    }
}
