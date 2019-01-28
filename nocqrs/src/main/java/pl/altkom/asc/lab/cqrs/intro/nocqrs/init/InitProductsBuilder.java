package pl.altkom.asc.lab.cqrs.intro.nocqrs.init;

import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Cover;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Product;

import java.util.Arrays;
import java.util.UUID;

class InitProductsBuilder {
    static Product standardCarInsurance() {
        return new Product(
                UUID.randomUUID(),
                "STD_CAR_INSURANCE",
                "Standard Car Insurance",
                Arrays.asList(
                        new Cover(UUID.randomUUID(), "OC", "Third party liability"),
                        new Cover(UUID.randomUUID(), "AC", "Auto casco"),
                        new Cover(UUID.randomUUID(), "ASSITANCE", "Assistance")
                )
        );
    }
}
