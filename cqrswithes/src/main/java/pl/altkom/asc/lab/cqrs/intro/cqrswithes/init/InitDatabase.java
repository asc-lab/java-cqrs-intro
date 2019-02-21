package pl.altkom.asc.lab.cqrs.intro.cqrswithes.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.offer.OfferRepository;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.product.Product;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.product.ProductRepository;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InitDatabase {

    private final ProductRepository productRepository;
    private final OfferRepository offerRepository;

    public void init() {
        Product standardCarInsurance = InitProductsBuilder.standardCarInsurance();
        productRepository.add(standardCarInsurance);

        Product productFromRepo = productRepository.withCode(standardCarInsurance.getCode());

        offerRepository.add(InitOffersBuilder.standardOneYearOCOfferValidUntil(
                productFromRepo,
                "OFF-001",
                LocalDate.now().plusDays(15)
        ));
        offerRepository.add(InitOffersBuilder.standardOneYearOCOfferValidUntil(
                productFromRepo,
                "OFF-002",
                LocalDate.now().minusDays(3)
        ));
    }
}
