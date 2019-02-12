package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.exceptions.BusinessException;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.primitives.MonetaryAmount;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class Offer {
    @Id
    @GeneratedValue
    private UUID id;
    private String number;
    private OfferStatus status;
    @ManyToOne(optional = false)
    private Product product;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "customer_first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "customer_last_name")),
            @AttributeOverride(name = "taxId", column = @Column(name = "customer_tax_id"))
    })
    private Person customer;
    private Car car;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "driver_first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "driver_last_name")),
            @AttributeOverride(name = "taxId", column = @Column(name = "driver_tax_id"))
    })
    private Person driver;
    private Period coverPeriod;
    private MonetaryAmount totalCost;
    private LocalDate creationDate;
    @OneToMany(cascade = CascadeType.ALL)
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
        this.product = product;
        this.customer = customer;
        this.driver = driver;
        this.car = car;
        this.coverPeriod = coverPeriod;
        this.totalCost = totalCost;
        this.creationDate = creationDate;
        coversPrices.forEach((key, value) -> covers.add(new CoverPrice(UUID.randomUUID(), key, value, coverPeriod)));
    }

    boolean isConverted() {
        return OfferStatus.Converted.equals(status);
    }

    boolean isExpired(LocalDate theDate) {
        return getValidityDate().isBefore(theDate);
    }

    boolean isRejected() {
        return OfferStatus.Rejected.equals(status);
    }

    public void reject() {
        //intentionally oversimplified
        if (!OfferStatus.New.equals(status))
            throw new BusinessException("Cannot reject offer in status other than New");

        status = OfferStatus.Rejected;
    }

    public void convert() {
        //intentionally oversimplified
        if (!OfferStatus.New.equals(status))
            throw new BusinessException("Cannot convert offer in status other than New");

        status = OfferStatus.Converted;
    }

    public enum OfferStatus {
        New, Converted, Rejected
    }
}
