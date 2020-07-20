package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    static class PolicyInfoDtoRowMapper implements RowMapper<PolicyInfoDto> {
        @Override
        public PolicyInfoDto mapRow(ResultSet resultSet, int i) throws SQLException {
            return new PolicyInfoDto(
                    UUID.fromString(resultSet.getString("policy_id")),
                    resultSet.getString("policy_number"),
                    resultSet.getDate("cover_from").toLocalDate(),
                    resultSet.getDate("cover_to").toLocalDate(),
                    resultSet.getString("vehicle"),
                    resultSet.getString("policy_holder"),
                    resultSet.getBigDecimal("total_premium_amount")
            );
        }
    }
}
