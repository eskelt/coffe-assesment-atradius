package org.coffe.atradius.data.repository;

import org.coffe.atradius.data.model.OrdersDTO;
import org.coffe.atradius.data.model.PaymentsDTO;
import org.coffe.atradius.data.model.ProductsDTO;

import java.util.List;

public interface CoffeRepository {
    List<OrdersDTO> getOrders();
    List<PaymentsDTO> getPayments();
    List<ProductsDTO> getProducts();
}
