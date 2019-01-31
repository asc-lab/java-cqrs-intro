package pl.altkom.asc.lab.cqrs.intro.separatemodels.queries.findpolicies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyInfoDto {
    private UUID policyId;
    private String policyNumber;
    private LocalDate coverFrom;
    private LocalDate coverTo;
    private String vehicle;
    private String policyHolder;
    private BigDecimal totalPremiumAmount;
}
