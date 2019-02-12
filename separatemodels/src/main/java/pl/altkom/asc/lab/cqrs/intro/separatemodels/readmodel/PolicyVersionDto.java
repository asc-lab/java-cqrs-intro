package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class PolicyVersionDto {
    @Id
    private Long id;
    private final UUID policyVersionId;
    private final UUID policyId;
    private final String policyNumber;
    private final String productCode;
    private final int versionNumber;
    private final String versionStatus;
    private final String policyStatus;
    private final String policyHolder;
    private final String insured;
    private final String car;
    private final LocalDate coverFrom;
    private final LocalDate coverTo;
    private final LocalDate versionFrom;
    private final LocalDate versionTo;
    private final BigDecimal totalPremiumAmount;
    private final List<PolicyVersionCoverDto> covers;

    @RequiredArgsConstructor
    static class PolicyVersionCoverDto {
        @Id
        private Long id;
        private final String code;
        private final LocalDate coverFrom;
        private final LocalDate coverTo;
        private final BigDecimal premiumAmount;
    }
}
