package com.index.stylefahion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.index.stylefahion.dtos.FacturaProductoDTO;
import com.index.stylefahion.models.Factura;
import com.index.stylefahion.repositorys.repoFactura;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@PreAuthorize("denyAll()")
@RequestMapping("fact")
public class facturaController {

    @Autowired
    private repoFactura fact;

    @PostMapping("crear/factura")
    @PreAuthorize("hasAuthority('CREATE')") // Acceso para: santiago, daniel, anyi
    public String crearfactura(@RequestBody Factura f) {
        fact.save(f);
        return "Factura Creada";
    }

    @GetMapping("get/facturas")
    @PreAuthorize("hasAuthority('READ')") // Acceso para: santiago, andrea, anyi, daniel
    public List<Factura> ListarFacturas() {
        return fact.findAll();
    }

    @PutMapping("put/facturas/{id}")
    @PreAuthorize("hasAuthority('UPDATE')") // Acceso para: santiago, anyi
    public String editFacturas(@PathVariable Integer id, @RequestBody Factura f) {
        Factura actualizaFactura = fact.findById(id).get();
        actualizaFactura.setFacturaid(f.getFacturaid());
        actualizaFactura.setProductid(f.getProductid());
        actualizaFactura.setClienteid(f.getClienteid());
        actualizaFactura.setEmpid(f.getEmpid());
        actualizaFactura.setTotal(f.getTotal());
        actualizaFactura.setFecha(f.getFecha());
        fact.save(actualizaFactura);
        return "Factura Actualizada";
    }

    @DeleteMapping("delete/factura/{id}")
    @PreAuthorize("hasAuthority('DELETE')") // Acceso para: santiago, anyi
    public String borrarfact(@PathVariable Integer id) {
        Factura borrapFactura = fact.findById(id).get();
        fact.delete(borrapFactura);
        return "Factura Eliminado";

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')") // Acceso para: santiago, andrea, anyi, daniel
    public ResponseEntity<FacturaProductoDTO> findFactura(@PathVariable Integer id) {
        Factura Factura = fact.findFacturaByFacturaid(id);
        if (Factura == null) {
            return ResponseEntity.notFound().build();
        }

        FacturaProductoDTO dto = new FacturaProductoDTO();
        dto.setFacturaid(Factura.getFacturaid());
        dto.setProductid(Factura.getProductid());
        dto.setCantidad(Factura.getCantidad());
        dto.setSubtotal(Factura.getPrecio_product() * Factura.getCantidad());
        dto.setFecha(Factura.getFecha());

        return ResponseEntity.ok(dto);
    }

}
