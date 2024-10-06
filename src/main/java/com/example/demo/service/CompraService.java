package com.example.demo.service;



import java.util.List;
import java.util.Optional;

import com.example.demo.model.Compra;

public interface CompraService {
    List<Compra> getAllCompras();
    Optional<Compra> getCompraById(Long id);
    Compra createCompra(Compra compra);
    Compra updateCompra(Long id, Compra compra);
    void deleteCompra(Long id);
    List<Compra> getComprasByStatus(String status);
}
