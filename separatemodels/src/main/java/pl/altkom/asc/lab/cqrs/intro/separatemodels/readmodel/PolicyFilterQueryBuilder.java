package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

class PolicyFilterQueryBuilder {
    private static final String PERCENTAGE_SYMBOL = "%";
    private String query = "SELECT policy_id, policy_number, cover_from, cover_to, vehicle, policy_holder, total_premium_amount FROM policy_info_dto";
    private MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

    PolicyFilterQueryBuilder where() {
        query += " WHERE 1 = 1";
        return this;
    }

    PolicyFilterQueryBuilder policyNumberEquals(String policyNumber) {
        if (isNotEmpty(policyNumber)) {
            mapSqlParameterSource.addValue("policyNumber", policyNumber);
            query += " AND policy_number = :policyNumber";
        }
        return this;
    }

    PolicyFilterQueryBuilder policyHolderLike(String policyHolder) {
        if (isNotEmpty(policyHolder)) {
            mapSqlParameterSource.addValue("policyHolder", getValueForLike(policyHolder));
            query += " AND policy_holder like :policyHolder";
        }
        return this;
    }

    PolicyFilterQueryBuilder policyStartDateFrom(LocalDate startDateFrom) {
        if (startDateFrom != null) {
            mapSqlParameterSource.addValue("startDateFrom", startDateFrom);
            query += " AND cover_from >= :startDateFrom";
        }
        return this;
    }

    PolicyFilterQueryBuilder policyStartDateTo(LocalDate startDateTo) {
        if (startDateTo != null) {
            mapSqlParameterSource.addValue("startDateTo", startDateTo);
            query += " AND cover_from <= :startDateTo";
        }
        return this;
    }

    PolicyFilterQueryBuilder policyCarPlateNumberLike(String carPlateNumber) {
        if (isNotEmpty(carPlateNumber)) {
            mapSqlParameterSource.addValue("carPlateNumber", getValueForLike(carPlateNumber));
            query += " AND vehicle like :carPlateNumber";
        }
        return this;
    }

    List<PolicyInfoDto> execute(NamedParameterJdbcTemplate jdbcTemplate, PolicyInfoDtoMapRow policyInfoDtoMapRow) {
        return jdbcTemplate.query(query, mapSqlParameterSource, policyInfoDtoMapRow);
    }

    private String getValueForLike(String policyHolder) {
        return PERCENTAGE_SYMBOL + policyHolder + PERCENTAGE_SYMBOL;
    }

    private boolean isNotEmpty(String value) {
        return !StringUtils.isEmpty(value);
    }

}
