package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PolicyInfoDtoRepository extends CrudRepository<PolicyInfoDto, Long> {

    @Modifying
    @Query("UPDATE policy_info_dto " +
            "SET " +
            "cover_from = :policy.coverFrom, " +
            "cover_to = :policy.coverTo, " +
            "vehicle = :policy.vehicle, " +
            "policy_holder = :policy.policyHolder, " +
            "total_premium = :policy.totalPremium " +
            "WHERE " +
            "policy_id = :policy.policyId")
    void update(@Param("policy") PolicyInfoDto policy);

    @Query("SELECT * FROM policy_info_dto p WHERE p.policy_id = :policyId")
    Optional<PolicyInfoDto> findByPolicyId(@Param("policyId") UUID policyId);

}
