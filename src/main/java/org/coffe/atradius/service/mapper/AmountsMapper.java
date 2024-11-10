package org.coffe.atradius.service.mapper;

import org.coffe.atradius.data.model.PaymentsDTO;
import org.coffe.atradius.domain.UsersAmountInfo;
import org.coffe.atradius.rest.generated.model.UserOwedAmount;
import org.coffe.atradius.rest.generated.model.UserPaidAmount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AmountsMapper {

    @Mapping(source = "user", target = "name")
    @Mapping(source = "amount", target = "paid")
    UsersAmountInfo paymentsDTOToUsersAmountInfo(PaymentsDTO usersInfo);




    @Mapping(source = "name", target = "name")
    @Mapping(source = "owed", target = "amountOwed")
    UserOwedAmount usersAmountInfoToUserOwedAmount(UsersAmountInfo usersAmountInfo);


    @Mapping(source = "name", target = "name")
    @Mapping(source = "paid", target = "amountPaid")
    UserPaidAmount usersAmountInfoToUserPaidAmount(UsersAmountInfo usersAmountInfo);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "amountPaid", target = "paid")
    UsersAmountInfo userPaidAmountToUsersAmountInfo(UserPaidAmount usersAmountInfo);

}

