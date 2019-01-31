package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.createpolicy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Offer;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.OfferRepository;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.CommandHandler;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreatePolicyHandler implements CommandHandler<CreatePolicyResult, CreatePolicyCommand> {

    private final OfferRepository offerRepository;
    private final PolicyRepository policyRepository;

    @Override
    public CreatePolicyResult handle(CreatePolicyCommand command) {
        Offer offer = offerRepository.withNumber(command.getOfferNumber());
        Policy policy = Policy.convertOffer(offer, UUID.randomUUID().toString(), command.getPurchaseDate(), command.getPolicyStartDate());
        policyRepository.add(policy);
        return new CreatePolicyResult(policy.getNumber());
    }
}
