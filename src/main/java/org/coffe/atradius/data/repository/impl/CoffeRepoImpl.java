package org.coffe.atradius.data.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.coffe.atradius.data.model.OrdersDTO;
import org.coffe.atradius.data.model.PaymentsDTO;
import org.coffe.atradius.data.model.ProductsDTO;
import org.coffe.atradius.data.repository.CoffeRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Repository
public class CoffeRepoImpl implements CoffeRepository {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public List<OrdersDTO> getOrders() {
        return readJsonData("orders.json", OrdersDTO[].class);
    }

    @Override
    public List<PaymentsDTO> getPayments() {
        return readJsonData("payments.json", PaymentsDTO[].class);
    }

    @Override
    public List<ProductsDTO> getProducts() {
        return readJsonData("products.json", ProductsDTO[].class);
    }

    private <T> List<T> readJsonData(String fileName, Class<T[]> clazz) {
        try {
            File file = new File("src/main/resources/data/" + fileName);
            return Arrays.asList(objectMapper.readValue(file, clazz));
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON data", e);
        }
    }
}
