package pl.altkom.asc.lab.cqrs.intro.separatemodels.eventhandlers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyEvents;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.PolicyInfoDtoProjection;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.PolicyVersionDtoProjection;

@Component
@RequiredArgsConstructor
public class PolicyEventsProjectionsHandler {

    private final PolicyInfoDtoProjection policyInfoDtoProjection;
    private final PolicyVersionDtoProjection policyVersionDtoProjection;

    @EventListener
    public void handlePolicyCreated(PolicyEvents.PolicyCreated event) {
        policyInfoDtoProjection.createPolicyInfoDto(event.getNewPolicy());
        policyVersionDtoProjection.createPolicyVersionDto(event.getNewPolicy(), event.getNewPolicy().getPolicyVersions().withNumber(1).get());
    }

    @EventListener
    public void handlePolicyTerminated(PolicyEvents.PolicyTerminated event) {
        policyInfoDtoProjection.updatePolicyInfoDto(event.getTerminatedPolicy(), event.getTerminatedVersion());
        policyVersionDtoProjection.createPolicyVersionDto(event.getTerminatedPolicy(), event.getTerminatedVersion());
    }

    @EventListener
    public void handlePolicyAnnexed(PolicyEvents.PolicyAnnexed event) {
        policyInfoDtoProjection.updatePolicyInfoDto(event.getAnnexedPolicy(), event.getAnnexVersion());
        policyVersionDtoProjection.createPolicyVersionDto(event.getAnnexedPolicy(), event.getAnnexVersion());
    }

    @EventListener
    public void handlePolicyAnnexCancelled(PolicyEvents.PolicyAnnexCancelled event) {
        policyInfoDtoProjection.updatePolicyInfoDto(event.getPolicy(), event.getCurrentVersionAfterAnnexCancellation());
        policyVersionDtoProjection.updatePolicyVersionDto(event.getCancelledAnnexVersion());
    }
}
