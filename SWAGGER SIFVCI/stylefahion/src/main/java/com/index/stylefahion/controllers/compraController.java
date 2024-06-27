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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.index.stylefahion.dtos.CompraDTO;
import com.index.stylefahion.models.Compra;
import com.index.stylefahion.models.Empleado;
import com.index.stylefahion.models.Producto;
import com.index.stylefahion.models.Provedor;
import com.index.stylefahion.repositorys.repoCompra;
import com.index.stylefahion.repositorys.repoEmpleado;
import com.index.stylefahion.repositorys.repoProducto;
import com.index.stylefahion.repositorys.repoProvedor;

@RestController
@PreAuthorize("denyAll()")
@RequestMapping("compra")
public class compraController {

    @Autowired
    private repoCompra comp;

    @Autowired
    private repoProducto prod;

    @Autowired
    private repoProvedor prov;

    @Autowired
    private repoEmpleado emp;

    @PostMapping("crear/compra")
    @PreAuthorize("hasAuthority('CREATE')") // Acceso para: santiago, daniel, anyi
    public String crearcompra(@RequestBody Compra co) {
        comp.save(co);
        return "Compra Agregada";
    }

    @GetMapping("get/compras")
    @PreAuthorize("hasAuthority('READ')") // Acceso para: santiago, andrea, anyi, daniel
    public List<Compra> ListarCompras() {
        return comp.findAll();
    }

    @PutMapping("put/compras/{id}")
    @PreAuthorize("hasAuthority('UPDATE')") // Acceso para: santiago, anyi
    public String editCompraes(@PathVariable Integer id, @RequestBody Compra c) {
        Compra actualizaCompra = comp.findById(id).get();
        actualizaCompra.setFecha(c.getFecha());
        actualizaCompra.setProvid(c.getProvid());
        actualizaCompra.setEmpid(c.getEmpid());
        actualizaCompra.setProductid(c.getProductid());
        comp.save(actualizaCompra);
        return "Compra Actualizada";
    }

    @DeleteMapping("delete/compra/{id}")
    @PreAuthorize("hasAuthority('DELETE')") // Acceso para: santiago, anyi
    public String borrarcompra(@PathVariable Integer id) {
        Compra borrapCompra = comp.findById(id).get();
        comp.delete(borrapCompra);
        return "Compra Eliminada";

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')") // Acceso para: santiago, andrea, anyi, daniel
    public ResponseEntity<CompraDTO> findCompra(@PathVariable Integer id) {
        Compra Compra = comp.findCompraByCompraid(id);
        if (Compra == null) {
            return ResponseEntity.notFound().build();
        }
        Provedor Provedor = prov.findProvedorByProvid(id);
        Producto Producto = prod.findProductoById(id);
        Empleado Empleado = emp.findEmpleadoByEmpid(id);

        CompraDTO dto = new CompraDTO();
        dto.setCompraid(Compra.getCompraid());
        dto.setFecha(Compra.getFecha());
        dto.setProvedor(Provedor.getNom_prov());
        dto.setProducto(Producto.getNom_product() + Producto.getDescrip_product());
        dto.setEmpleado(Empleado.getNom_emp() + Empleado.getAp_emp());

        return ResponseEntity.ok(dto);
    }

}
