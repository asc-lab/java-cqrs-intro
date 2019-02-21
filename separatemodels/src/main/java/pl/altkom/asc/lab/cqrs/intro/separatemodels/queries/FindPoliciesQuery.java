package pl.altkom.asc.lab.cqrs.intro.separatemodels.queries;

import lombok.Getter;
import lombok.Setter;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.cqs.Query;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.PolicyInfoDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FindPoliciesQuery implements Query<List<PolicyInfoDto>> {
    private String policyNumber;
    private LocalDate policyStartFrom;
    private LocalDate policyStartTo;
    private String carPlateNumber;
    private String policyHolderFirstName;
    private String policyHolderLastName;
}
