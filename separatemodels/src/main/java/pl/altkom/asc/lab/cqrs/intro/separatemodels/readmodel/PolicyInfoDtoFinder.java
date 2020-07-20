package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PolicyInfoDtoFinder {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<PolicyInfoDto> findByFilter(PolicyFilter filter) {
        return new PolicyFilterQueryBuilder()
                .where()
                .policyNumberEquals(filter.getNumber())
                .policyHolderLike(filter.getHolderFirstName())
                .policyHolderLike(filter.getHolderLastName())
                .policyStartDateFrom(filter.getStartDateFrom())
                .policyStartDateTo(filter.getStartDateTo())
                .policyCarPlateNumberLike(filter.getCarPlateNumber())
                .execute(jdbcTemplate, new PolicyInfoDto.PolicyInfoDtoRowMapper());
    }

}
