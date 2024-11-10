package org.coffe.atradius.rest.impl;

import org.coffe.atradius.rest.generated.AmountsApi;
import org.coffe.atradius.rest.generated.model.UserOwedAmount;
import org.coffe.atradius.rest.generated.model.UserPaidAmount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AmountsController implements AmountsApi {

    @Override
    public ResponseEntity<List<UserOwedAmount>> getOwedAmounts() {
        return null;
    }

    @Override
    public ResponseEntity<List<UserPaidAmount>> getPaidAmounts() {
        return null;
    }
}
