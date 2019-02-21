package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.offer;

import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.Car;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.MonetaryAmount;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.Person;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.product.Cover;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.product.Product;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class Offer {
    private UUID id;
    private String number;
    private OfferStatus status;
    private String productCode;
    private Person customer;
    private Car car;
    private Person driver;
    private Period coverPeriod;
    private MonetaryAmount totalCost;
    private LocalDate creationDate;
    private List<CoverPrice> covers = new ArrayList<>();

    private LocalDate getValidityDate() {
        return creationDate.plusDays(30);
    }

    public Offer(
            UUID id,
            String number,
            Product product,
            Person customer,
            Person driver,
            Car car,
            Period coverPeriod,
            MonetaryAmount totalCost,
            LocalDate creationDate,
            Map<Cover, MonetaryAmount> coversPrices) {
        this.id = id;
        this.number = number;
        this.status = OfferStatus.New;
        this.productCode = product.getCode();
        this.customer = customer;
        this.driver = driver;
        this.car = car;
        this.coverPeriod = coverPeriod;
        this.totalCost = totalCost;
        this.creationDate = creationDate;
        coversPrices.forEach((key, value) -> covers.add(new CoverPrice(key, value, coverPeriod)));
    }

    public boolean isConverted() {
        return OfferStatus.Converted.equals(status);
    }

    public boolean isExpired(LocalDate theDate) {
        return getValidityDate().isBefore(theDate);
    }

    public boolean isRejected() {
        return OfferStatus.Rejected.equals(status);
    }

    public enum OfferStatus {
        New, Converted, Rejected
    }
}
