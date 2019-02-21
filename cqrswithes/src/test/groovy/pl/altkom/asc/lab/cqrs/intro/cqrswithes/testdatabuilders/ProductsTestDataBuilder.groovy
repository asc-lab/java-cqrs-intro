package pl.altkom.asc.lab.cqrs.intro.cqrswithes.testdatabuilders

import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.product.Cover
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.product.Product


class ProductsTestDataBuilder {

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
        )
    }
}
