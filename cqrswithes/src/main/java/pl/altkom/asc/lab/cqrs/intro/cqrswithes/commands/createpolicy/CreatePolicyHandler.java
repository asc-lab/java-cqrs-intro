package pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.createpolicy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.offer.Offer;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.offer.OfferRepository;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.Policy;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs.CommandHandler;

@Component
@RequiredArgsConstructor
public class CreatePolicyHandler implements CommandHandler<CreatePolicyResult, CreatePolicyCommand> {

    private final OfferRepository offerRepository;
    private final PolicyRepository policyRepository;

    @Override
    public CreatePolicyResult handle(CreatePolicyCommand command) {
        Offer offer = offerRepository.withNumber(command.getOfferNumber());

        Policy policy = Policy.convertOffer(offer, command.getPurchaseDate(), command.getPolicyStartDate());
        policyRepository.save(policy, -1);

        return new CreatePolicyResult(policy.getId(), policy.getNumber());
    }
}
