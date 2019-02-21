package pl.altkom.asc.lab.cqrs.intro.cqrswithes.init;


import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.Car;

class InitCarsBuilder {
    static Car oldFordFocus() {
        return new Car("Ford Focus", "WAW1010", 2005);
    }
}
