package pl.altkom.asc.lab.cqrs.intro.nocqrs.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.services.*;

import java.util.Collection;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class PoliciesController {

    private final PolicyService policyService;

    @PostMapping
    public ResponseEntity<CreatePolicyResult> createPolicy(@RequestBody CreatePolicyRequest request) {
        CreatePolicyResult result = policyService.createPolicy(request);
        return ok(result);
    }

    @PostMapping("/buyAdditionalCover")
    public ResponseEntity<BuyAdditionalCoverResult> buyAdditionalCover(@RequestBody BuyAdditionalCoverRequest request) {
        BuyAdditionalCoverResult result = policyService.buyAdditionalCover(request);
        return ok(result);
    }

    @PostMapping("/confirmBuyAdditionalCover")
    public ResponseEntity<ConfirmBuyAdditionalCoverResult> buyAdditionalCover(@RequestBody ConfirmBuyAdditionalCoverRequest request) {
        ConfirmBuyAdditionalCoverResult result = policyService.confirmBuyAdditionalCover(request);
        return ok(result);
    }

    @PostMapping("/terminate")
    public ResponseEntity<TerminatePolicyResult> terminatePolicy(@RequestBody TerminatePolicyRequest request) {
        TerminatePolicyResult result = policyService.terminatePolicy(request);
        return ok(result);
    }

    @PostMapping("/confirmTermination")
    public ResponseEntity<ConfirmTerminationResult> terminatePolicy(@RequestBody ConfirmTerminationRequest request) {
        ConfirmTerminationResult result = policyService.confirmTermination(request);
        return ok(result);
    }

    @PostMapping("/cancelLastAnnex")
    public ResponseEntity<CancelLastAnnexResult> cancelLastAnnex(@RequestBody CancelLastAnnexRequest request) {
        CancelLastAnnexResult result = policyService.cancelLastAnnex(request);
        return ok(result);
    }

    @GetMapping("/{policyNumber}")
    public ResponseEntity<PolicyDto> getPolicyDetails(@PathVariable String policyNumber) {
        PolicyDto policyDetails = policyService.getPolicyDetails(policyNumber);
        return ok(policyDetails);
    }

    @GetMapping("/find")
    public Collection<PolicyInfoDto> find(@RequestBody SearchPolicyRequest request) {
        return policyService.searchPolicies(request);
    }
}
