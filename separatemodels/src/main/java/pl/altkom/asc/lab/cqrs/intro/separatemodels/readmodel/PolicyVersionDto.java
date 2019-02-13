package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class PolicyVersionDto {
    @Id
    private Long id;
    private UUID policyVersionId;
    private UUID policyId;
    private String policyNumber;
    private String productCode;
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
    private BigDecimal totalPremiumAmount;
    @Setter
    private List<PolicyVersionCoverDto> covers;

    @Getter
    @RequiredArgsConstructor
    static class PolicyVersionCoverDto {
        @Id
        private Long id;
        private final String code;
        private final LocalDate coverFrom;
        private final LocalDate coverTo;
        private final BigDecimal premiumAmount;
    }

    static class PolicyVersionDtoRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return PolicyVersionDto.builder()
                    .id(rs.getLong("id"))
                    .policyVersionId(UUID.fromString(rs.getString("policy_version_id")))
                    .policyId(UUID.fromString(rs.getString("policy_id")))
                    .policyNumber(rs.getString("policy_number"))
                    .productCode(rs.getString("product_code"))
                    .versionNumber(rs.getInt("version_number"))
                    .versionStatus(rs.getString("version_status"))
                    .policyStatus(rs.getString("policy_status"))
                    .policyHolder(rs.getString("policy_holder"))
                    .insured(rs.getString("insured"))
                    .car(rs.getString("car"))
                    .coverFrom(rs.getDate("cover_from").toLocalDate())
                    .coverTo(rs.getDate("cover_to").toLocalDate())
                    .versionFrom(rs.getDate("version_from").toLocalDate())
                    .versionTo(rs.getDate("version_to").toLocalDate())
                    .totalPremiumAmount(rs.getBigDecimal("total_premium_amount"))
                    .build();
        }
    }
}
