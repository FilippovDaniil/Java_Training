package m110_spring_test_security_async.practice.task05;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task05.class)
// TODO: @org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
class ExternalIntegrationTest05 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean PaymentGateway05 gateway;

    @Test
    void places_order_without_real_payment() throws Exception {
        // TODO: when(gateway.charge(anyInt())).thenReturn("OK");
        // TODO: mockMvc.perform(post("/api/orders").contentType(APPLICATION_JSON).content("{\"amount\":100}"))
        //              .andExpect(status().isCreated());
        // TODO: verify(gateway).charge(100);
    }
}
