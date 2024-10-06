package com.example.demo.service;

import com.example.demo.model.Compra;
import com.example.demo.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Override
    public List<Compra> getAllCompras() {
        return compraRepository.findAll();
    }

    @Override
    public Optional<Compra> getCompraById(Long id) {
        return compraRepository.findById(id);
    }

    @Override
    public Compra createCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    @Override
    public Compra updateCompra(Long id, Compra compra) {
        Optional<Compra> compraExistente = compraRepository.findById(id);
        if (compraExistente.isPresent()) {
            Compra compraToUpdate = compraExistente.get();
            compraToUpdate.setNombreCliente(compra.getNombreCliente());
            compraToUpdate.setTipoProducto(compra.getTipoProducto());
            compraToUpdate.setMetodoPago(compra.getMetodoPago());
            compraToUpdate.setDireccionEntrega(compra.getDireccionEntrega());
            compraToUpdate.setTipoEntrega(compra.getTipoEntrega());
            compraToUpdate.setFechaEntrega(compra.getFechaEntrega());
            return compraRepository.save(compraToUpdate);
        }
        return null;  
    }

    @Override
    public void deleteCompra(Long id) {
        compraRepository.deleteById(id);
    }

    @Override
    public List<Compra> getComprasByStatus(String status) {
        return compraRepository.findByEstado(status);  
    }
}