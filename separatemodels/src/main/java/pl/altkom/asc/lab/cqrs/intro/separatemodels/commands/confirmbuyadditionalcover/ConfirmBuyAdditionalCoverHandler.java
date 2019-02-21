package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.confirmbuyadditionalcover;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyEvents;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.cqs.CommandHandler;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.events.EventPublisher;

@Component
@Transactional
@RequiredArgsConstructor
public class ConfirmBuyAdditionalCoverHandler implements CommandHandler<ConfirmBuyAdditionalCoverResult, ConfirmBuyAdditionalCoverCommand> {

    private final PolicyRepository policyRepository;
    private final EventPublisher eventPublisher;

    @Override
    public ConfirmBuyAdditionalCoverResult handle(ConfirmBuyAdditionalCoverCommand command) {
        Policy policy = policyRepository.withNumber(command.getPolicyNumber());
        policy.confirmChanges(command.getVersionToConfirmNumber());

        eventPublisher.publish(new PolicyEvents.PolicyAnnexed(this,
                policy,
                policy.getPolicyVersions().withNumber(command.getVersionToConfirmNumber()).get())
        );

        return new ConfirmBuyAdditionalCoverResult(
                policy.getNumber(),
                policy.getPolicyVersions().latestActive().getVersionNumber()
        );
    }
}
