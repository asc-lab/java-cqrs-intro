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
    public ResponseEntity<CreatePolicyResult> create(@RequestBody CreatePolicyRequest request) {
        CreatePolicyResult result = policyService.createPolicy(request);
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
