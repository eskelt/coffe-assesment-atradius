package org.coffe.atradius.rest.impl;

import org.coffe.atradius.rest.generated.model.UserOwedAmount;
import org.coffe.atradius.rest.generated.model.UserPaidAmount;
import org.coffe.atradius.service.AmountsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AmountsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPaidAmounts() throws Exception {
        // Datos de ejemplo
        List<UserPaidAmount> paidAmounts = List.of(
                new UserPaidAmount("coach", 69.0),
                new UserPaidAmount("rochelle", 95.0),
                new UserPaidAmount("bill", 77.0),
                new UserPaidAmount("zoey", 101.0),
                new UserPaidAmount("nick", 143.0),
                new UserPaidAmount("francis", 112.0),
                new UserPaidAmount("louis", 12.0),
                new UserPaidAmount("ellis", 24.0)
        );

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/amounts/paid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("coach"))
                .andExpect(jsonPath("$[0].amountPaid").value(69.0))
                .andExpect(jsonPath("$[1].name").value("rochelle"))
                .andExpect(jsonPath("$[1].amountPaid").value(95.0))
                .andExpect(jsonPath("$[2].name").value("bill"))
                .andExpect(jsonPath("$[2].amountPaid").value(77.0))
                .andExpect(jsonPath("$[3].name").value("zoey"))
                .andExpect(jsonPath("$[3].amountPaid").value(101.0))
                .andExpect(jsonPath("$[4].name").value("nick"))
                .andExpect(jsonPath("$[4].amountPaid").value(143.0))
                .andExpect(jsonPath("$[5].name").value("francis"))
                .andExpect(jsonPath("$[5].amountPaid").value(112.0))
                .andExpect(jsonPath("$[6].name").value("louis"))
                .andExpect(jsonPath("$[6].amountPaid").value(12.0))
                .andExpect(jsonPath("$[7].name").value("ellis"))
                .andExpect(jsonPath("$[7].amountPaid").value(24.0));
    }


    @Test
    public void testGetOwedAmounts() throws Exception {
        // Datos de ejemplo
        List<UserOwedAmount> owedAmounts = List.of(
                new UserOwedAmount("coach", 4.0),
                new UserOwedAmount("rochelle", 0.0),
                new UserOwedAmount("bill", 0.0),
                new UserOwedAmount("zoey", 0.0),
                new UserOwedAmount("nick", 0.0),
                new UserOwedAmount("francis", 0.0),
                new UserOwedAmount("louis", 37.5),
                new UserOwedAmount("ellis", 41.75)
        );

        // Ejecutamos la solicitud GET y verificamos el resultado
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/amounts/owed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("coach"))
                .andExpect(jsonPath("$[0].amountOwed").value(4.0))
                .andExpect(jsonPath("$[1].name").value("rochelle"))
                .andExpect(jsonPath("$[1].amountOwed").value(0.0))
                .andExpect(jsonPath("$[2].name").value("bill"))
                .andExpect(jsonPath("$[2].amountOwed").value(0.0))
                .andExpect(jsonPath("$[3].name").value("zoey"))
                .andExpect(jsonPath("$[3].amountOwed").value(0.0))
                .andExpect(jsonPath("$[4].name").value("nick"))
                .andExpect(jsonPath("$[4].amountOwed").value(0.0))
                .andExpect(jsonPath("$[5].name").value("francis"))
                .andExpect(jsonPath("$[5].amountOwed").value(0.0))
                .andExpect(jsonPath("$[6].name").value("louis"))
                .andExpect(jsonPath("$[6].amountOwed").value(37.5))
                .andExpect(jsonPath("$[7].name").value("ellis"))
                .andExpect(jsonPath("$[7].amountOwed").value(41.75));
    }



}