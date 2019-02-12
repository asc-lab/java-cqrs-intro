package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.createpolicy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.*;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.CommandHandler;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.events.EventPublisher;

import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class CreatePolicyHandler implements CommandHandler<CreatePolicyResult, CreatePolicyCommand> {

    private final OfferRepository offerRepository;
    private final PolicyRepository policyRepository;
    private final EventPublisher eventPublisher;

    @Override
    public CreatePolicyResult handle(CreatePolicyCommand command) {
        Offer offer = offerRepository.withNumber(command.getOfferNumber());
        Policy policy = Policy.convertOffer(offer, UUID.randomUUID().toString(), command.getPurchaseDate(), command.getPolicyStartDate());
        policyRepository.add(policy);

        eventPublisher.publish(new PolicyEvents.PolicyCreated(this, policy));

        return new CreatePolicyResult(policy.getNumber());
    }
}
