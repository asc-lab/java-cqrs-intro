package pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.terminatepolicy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.Policy;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs.CommandHandler;

@Component
@RequiredArgsConstructor
public class TerminatePolicyHandler implements CommandHandler<TerminatePolicyResult, TerminatePolicyCommand> {

    private final PolicyRepository policyRepository;

    @Override
    public TerminatePolicyResult handle(TerminatePolicyCommand command) {
        Policy policy = policyRepository.getById(command.getPolicyId());

        policy.terminatePolicy(command.getTerminationDate());
        policyRepository.save(policy, policy.getVersion());

        return new TerminatePolicyResult(policy.getId(), policy.getPolicyVersions().last().getVersionNumber());
    }
}
