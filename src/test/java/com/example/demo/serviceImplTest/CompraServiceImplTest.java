package com.example.demo.serviceImplTest;

import com.example.demo.model.Compra;
import com.example.demo.repository.CompraRepository;
import com.example.demo.service.CompraServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CompraServiceImplTest {

    @InjectMocks
    private CompraServiceImpl compraService;

    @Mock
    private CompraRepository compraRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllComprasSize() {
        Compra compra1 = new Compra();
        compra1.setId(1L);
        compra1.setNombreCliente("Cliente 1");

        Compra compra2 = new Compra();
        compra2.setId(2L);
        compra2.setNombreCliente("Cliente 2");

        when(compraRepository.findAll()).thenReturn(Arrays.asList(compra1, compra2));

        List<Compra> result = compraService.getAllCompras();
        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteCompra() {
        Long compraId = 1L;
        doNothing().when(compraRepository).deleteById(compraId);
        compraService.deleteCompra(compraId);
        verify(compraRepository).deleteById(compraId);
    }
}

