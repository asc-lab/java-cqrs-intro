package pl.altkom.asc.lab.cqrs.intro.nocqrs.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.OfferRepository;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Product;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.ProductRepository;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InitDatabase {

    private final ProductRepository productRepository;
    private final OfferRepository offerRepository;

    @Transactional
    public void init() {
        Product standardCarInsurance = InitProductsBuilder.standardCarInsurance();
        productRepository.add(standardCarInsurance);

        Product productFromRepo = productRepository.withCode(standardCarInsurance.getCode());

        offerRepository.add(InitOffersBuilder.standardOneYearOCOfferValidUntil(productFromRepo, "OFF-001", LocalDate.now().plusDays(15)));
        offerRepository.add(InitOffersBuilder.standardOneYearOCOfferValidUntil(productFromRepo, "OFF-002", LocalDate.now().minusDays(3)));


    }
}
