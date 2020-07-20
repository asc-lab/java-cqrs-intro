package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class PolicyInfoDtoMapRow implements RowMapper<PolicyInfoDto> {

  static final String POLICY_ID = "policy_id";

  static final String POLICY_NUMBER = "policy_number";

  static final String COVER_FROM = "cover_from";

  static final String COVER_TO = "cover_to";

  static final String VEHICLE = "vehicle";

  static final String POLICY_HOLDER = "policy_holder";

  static final String TOTAL_PREMIUM_AMOUNT = "total_premium_amount";

  @Override
  public PolicyInfoDto mapRow(ResultSet resultSet, int i) throws SQLException {
    UUID policyId = UUID.fromString(resultSet.getString(POLICY_ID));
    String policyNumber = resultSet.getString(POLICY_NUMBER);
    LocalDate coverFrom = resultSet.getDate(COVER_FROM).toLocalDate();
    LocalDate coverTo = resultSet.getDate(COVER_TO).toLocalDate();
    String vehicle = resultSet.getString(VEHICLE);
    String policyHolder = resultSet.getString(POLICY_HOLDER);
    BigDecimal totalPremiumAmount = resultSet.getBigDecimal(TOTAL_PREMIUM_AMOUNT);
    PolicyInfoDto policyInfoDto = new PolicyInfoDto(policyId, policyNumber, coverFrom, coverTo, vehicle, policyHolder, totalPremiumAmount);
    return policyInfoDto;
  }
}
