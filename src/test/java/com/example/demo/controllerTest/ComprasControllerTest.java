package com.example.demo.controllerTest;

import com.example.demo.model.Compra;
import com.example.demo.service.CompraService;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.ComprasController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ComprasController.class)
public class ComprasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompraService compraServiceMock;

    @Test
    public void getComprasByStatusTest() throws Exception {
        Compra compra1 = new Compra();
        compra1.setId(1L);
        compra1.setEstado("PENDIENTE");

        Compra compra2 = new Compra();
        compra2.setId(2L);
        compra2.setEstado("PENDIENTE");

        List<Compra> compras = Arrays.asList(compra1, compra2);

        Mockito.when(compraServiceMock.getComprasByStatus("PENDIENTE")).thenReturn(compras);

        mockMvc.perform(get("/compras/estado/PENDIENTE"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].estado").value("PENDIENTE"))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].estado").value("PENDIENTE"));
    }

    @Test
    public void testDeleteCompra() throws Exception {
        Mockito.doNothing().when(compraServiceMock).deleteCompra(1L);

        mockMvc.perform(delete("/compras/1"))
            .andExpect(status().isNoContent());
    }
}
