package pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.confirmtermination;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.Policy;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs.CommandHandler;

@Component
@RequiredArgsConstructor
public class ConfirmTerminationHandler implements CommandHandler<ConfirmTerminationResult, ConfirmTerminationCommand> {

    private final PolicyRepository policyRepository;

    @Override
    public ConfirmTerminationResult handle(ConfirmTerminationCommand command) {
        Policy policy = policyRepository.getById(command.getPolicyId());

        policy.confirmTermination(command.getVersionToConfirmNumber());
        policyRepository.save(policy, policy.getVersion());

        return new ConfirmTerminationResult(
                policy.getId(),
                policy.getPolicyVersions().latestActive().getVersionNumber()
        );
    }
}
