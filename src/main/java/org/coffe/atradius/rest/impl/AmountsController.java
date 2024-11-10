package org.coffe.atradius.rest.impl;

import lombok.RequiredArgsConstructor;
import org.coffe.atradius.rest.generated.AmountsApi;
import org.coffe.atradius.rest.generated.model.UserOwedAmount;
import org.coffe.atradius.rest.generated.model.UserPaidAmount;
import org.coffe.atradius.service.AmountsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AmountsController implements AmountsApi {
    private final AmountsService amountsService;
    @Override
    public ResponseEntity<List<UserOwedAmount>> getOwedAmounts() {
        return ResponseEntity.ok(amountsService.getOwedAmounts());
    }

    @Override
    public ResponseEntity<List<UserPaidAmount>> getPaidAmounts() {
        return ResponseEntity.ok(amountsService.getPaidAmounts());
    }
}
