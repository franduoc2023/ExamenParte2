package com.example.demo.controller;

import com.example.demo.model.Compra;
import com.example.demo.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class ComprasController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public CollectionModel<EntityModel<Compra>> getAllCompras() {
        List<Compra> compras = compraService.getAllCompras();

        List<EntityModel<Compra>> compraModels = compras.stream()
            .map(compra -> EntityModel.of(compra,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ComprasController.class).getCompraById(compra.getId())).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ComprasController.class).getAllCompras()).withRel("compras")))
            .collect(Collectors.toList());

        
        return CollectionModel.of(compraModels,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ComprasController.class).getAllCompras()).withSelfRel());
    }

     @GetMapping("/{id}")
    public ResponseEntity<Compra> getCompraById(@PathVariable Long id) {
        return compraService.getCompraById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

     @PostMapping
    public ResponseEntity<Compra> createCompra(@RequestBody Compra compra) {
        return ResponseEntity.ok(compraService.createCompra(compra));
    }

     @PutMapping("/{id}")
    public ResponseEntity<Compra> updateCompra(@PathVariable Long id, @RequestBody Compra compra) {
        return Optional.ofNullable(compraService.updateCompra(id, compra))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompra(@PathVariable Long id) {
        compraService.deleteCompra(id);
        return ResponseEntity.noContent().build();
    }

     @GetMapping("/estado/{status}")
    public List<Compra> getComprasByStatus(@PathVariable String status) {
        return compraService.getComprasByStatus(status);
    }}