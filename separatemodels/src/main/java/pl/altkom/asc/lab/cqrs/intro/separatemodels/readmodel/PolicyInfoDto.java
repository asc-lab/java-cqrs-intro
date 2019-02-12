package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class PolicyInfoDto {
    @Id
    private Long id;
    private UUID policyId;
    private String policyNumber;
    private LocalDate coverFrom;
    private LocalDate coverTo;
    private String vehicle;
    private String policyHolder;
    private BigDecimal totalPremiumAmount;

    PolicyInfoDto(UUID policyId,
                  String policyNumber,
                  LocalDate coverFrom,
                  LocalDate coverTo,
                  String vehicle,
                  String policyHolder,
                  BigDecimal totalPremiumAmount) {
        this.policyId = policyId;
        this.policyNumber = policyNumber;
        this.coverFrom = coverFrom;
        this.coverTo = coverTo;
        this.vehicle = vehicle;
        this.policyHolder = policyHolder;
        this.totalPremiumAmount = totalPremiumAmount;
    }
}
