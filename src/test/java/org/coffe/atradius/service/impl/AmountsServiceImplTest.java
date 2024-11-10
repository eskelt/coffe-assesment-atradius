package org.coffe.atradius.service.impl;

import org.coffe.atradius.data.model.OrdersDTO;
import org.coffe.atradius.data.model.PaymentsDTO;
import org.coffe.atradius.data.model.ProductsDTO;
import org.coffe.atradius.data.repository.CoffeRepository;
import org.coffe.atradius.domain.UsersAmountInfo;
import org.coffe.atradius.rest.generated.model.UserOwedAmount;
import org.coffe.atradius.rest.generated.model.UserPaidAmount;
import org.coffe.atradius.service.mapper.AmountsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class AmountsServiceImplTest {

    @Mock
    private CoffeRepository coffeRepository;

    private AmountsMapper amountsMapper = Mappers.getMapper(AmountsMapper.class);

    @InjectMocks
    private AmountsServiceImpl amountsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        amountsService = new AmountsServiceImpl(coffeRepository, amountsMapper);
    }

    @Test
    public void testGetPaidAmounts() {
        List<PaymentsDTO> payments = List.of(
                new PaymentsDTO("Alice", 100.0),
                new PaymentsDTO("Bob", 50.0),
                new PaymentsDTO("Alice", 50.0)
        );


        when(coffeRepository.getPayments()).thenReturn(payments);


        List<UserPaidAmount> result = amountsService.getPaidAmounts();

        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals(150.0, result.get(0).getAmountPaid());
        assertEquals("Bob", result.get(1).getName());
        assertEquals(50.0, result.get(1).getAmountPaid());

        verify(coffeRepository, times(1)).getPayments();
    }

    @Test
    public void testGetOwedAmounts() {
        // Example data
        List<OrdersDTO> orders = List.of(
                new OrdersDTO("Alice", "Latte", "small"),
                new OrdersDTO("Bob", "Espresso", "medium")
        );
        List<ProductsDTO> products = List.of(
                new ProductsDTO("Latte", Map.of("small", 3.0)),
                new ProductsDTO("Espresso", Map.of("medium", 2.5))
        );


        List<PaymentsDTO> payments = List.of(
                new PaymentsDTO("Alice", 2.0),
                new PaymentsDTO("Bob", 3.0)
        );

        when(coffeRepository.getOrders()).thenReturn(orders);
        when(coffeRepository.getProducts()).thenReturn(products);
        when(coffeRepository.getPayments()).thenReturn(payments);

        List<UserOwedAmount> result = amountsService.getOwedAmounts();

        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals(1.0, result.get(0).getAmountOwed());
        assertEquals("Bob", result.get(1).getName());
        assertEquals(0.0, result.get(1).getAmountOwed());

        verify(coffeRepository, times(1)).getOrders();
        verify(coffeRepository, times(1)).getProducts();
        verify(coffeRepository, times(1)).getPayments();
    }
}