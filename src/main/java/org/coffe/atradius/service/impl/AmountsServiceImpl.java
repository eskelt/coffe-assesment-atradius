package org.coffe.atradius.service.impl;

import lombok.RequiredArgsConstructor;
import org.coffe.atradius.data.model.OrdersDTO;
import org.coffe.atradius.data.model.PaymentsDTO;
import org.coffe.atradius.data.model.ProductsDTO;
import org.coffe.atradius.data.repository.CoffeRepository;
import org.coffe.atradius.domain.UsersAmountInfo;
import org.coffe.atradius.rest.generated.model.UserOwedAmount;
import org.coffe.atradius.rest.generated.model.UserPaidAmount;
import org.coffe.atradius.service.AmountsService;
import org.coffe.atradius.service.mapper.AmountsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AmountsServiceImpl implements AmountsService {
    private final CoffeRepository coffeRepository;
    private final AmountsMapper amountsMapper;
    @Override
    public List<UserPaidAmount> getPaidAmounts() {
        List<PaymentsDTO> payments = coffeRepository.getPayments();

        List<UsersAmountInfo> users = payments
                .stream()
                .map((user) -> new UsersAmountInfo(user.getUser()))
                .distinct()
                .toList();

        users.stream().forEach((user) -> {
            user.setPaid(
                    payments.stream()
                    .filter(payment -> payment.getUser().equals(user.getName()))
                    .mapToDouble(payment -> payment.getAmount()).sum()
            );
        });

        return users.stream()
                .map(amountsMapper::usersAmountInfoToUserPaidAmount)
                .toList();
    }

    @Override
    public List<UserOwedAmount> getOwedAmounts() {
        List<OrdersDTO> orders = coffeRepository.getOrders();
        List<ProductsDTO> products = coffeRepository.getProducts();

        List<UsersAmountInfo> usersAmountInfo = getPaidAmounts().stream()
                                                .map(amountsMapper::userPaidAmountToUsersAmountInfo)
                                                .toList();

        usersAmountInfo.stream().forEach((user) -> {
            orders.stream().filter(order -> order.getUser().equals(user.getName())).forEach((order) -> {
                ProductsDTO orderProduct = products.stream().filter(product -> product.getDrink_name().equals(order.getDrink()))
                        .findFirst()
                        .orElseGet(null);
                if(orderProduct != null){
                    user.setTotalPrice(user.getTotalPrice() + orderProduct.getPrices().get(order.getSize()));
                }
            });
            Double owed = user.getTotalPrice() - user.getPaid();

            // We'll consider that someone can't owe "negative money"
            // so we'll change to 0 any negative amount owed
            user.setOwed(owed > 0 ? owed : 0);
        });

        return usersAmountInfo.stream().map(amountsMapper::usersAmountInfoToUserOwedAmount).toList();
    }

}
