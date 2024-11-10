package org.coffe.atradius.service;


import org.coffe.atradius.rest.generated.model.UserOwedAmount;
import org.coffe.atradius.rest.generated.model.UserPaidAmount;

import java.util.List;

public interface AmountsService {

    public List<UserPaidAmount> getPaidAmounts();
    public List<UserOwedAmount> getOwedAmounts();

}
