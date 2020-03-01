package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PolicyInfoDtoFinder {

    private final JdbcTemplate jdbcTemplate;

    public List<PolicyInfoDto> findByFilter(PolicyFilter filter) {
        PolicyFilterQueryBuilder policyQueryBuilder = new PolicyFilterQueryBuilder();
        return policyQueryBuilder.where()
                .policyNumberEquals(filter.getNumber())
                .policyHolderLike(filter.getHolderFirstName())
                .policyHolderLike(filter.getHolderLastName())
                .policyStartDateFrom(filter.getStartDateFrom())
                .policyStartDateTo(filter.getStartDateTo())
                .policyCarPlateNumberLike(filter.getCarPlateNumber())
                .execute(jdbcTemplate, new PolicyInfoDtoMapRow());
    }

}
