package pl.altkom.asc.lab.cqrs.intro.separatemodels.queries.getpolicydetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDto {

    private UUID policyId;
    private String policyNumber;
    private PolicyVersionDto currentVersion;
    private List<PolicyVersionDto> versions;

    @Getter
    @Builder
    public static class PolicyVersionDto {
        private int versionNumber;
        private String versionStatus;
        private String policyStatus;
        private String policyHolder;
        private String insured;
        private String car;
        private LocalDate coverFrom;
        private LocalDate coverTo;
        private LocalDate versionFrom;
        private LocalDate versionTo;
        private BigDecimal totalPremium;
        private List<CoverDto> covers;
        private List<String> changes;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoverDto {
        private String code;
        private LocalDate coverFrom;
        private LocalDate coverTo;
        private BigDecimal premiumAmount;
    }
}
