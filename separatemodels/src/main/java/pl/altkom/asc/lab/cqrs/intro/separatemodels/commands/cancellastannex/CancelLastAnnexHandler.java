package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.cancellastannex;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyEvents;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyVersion;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.CommandHandler;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.events.EventPublisher;

@Component
@Transactional
@RequiredArgsConstructor
public class CancelLastAnnexHandler implements CommandHandler<CancelLastAnnexResult, CancelLastAnnexCommand> {

    private final PolicyRepository policyRepository;
    private final EventPublisher eventPublisher;

    @Override
    public CancelLastAnnexResult handle(CancelLastAnnexCommand command) {
        Policy policy = policyRepository.withNumber(command.getPolicyNumber());
        PolicyVersion lastAnnex = policy.getPolicyVersions().latestActive();
        policy.cancelLastAnnex();

        eventPublisher.publish(
                new PolicyEvents.PolicyAnnexCancelled(this,
                        policy,
                        lastAnnex,
                        policy.getPolicyVersions().latestActive())
        );

        return new CancelLastAnnexResult(
                policy.getNumber(),
                policy.getPolicyVersions().latestActive().getVersionNumber()
        );
    }
}
