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

import com.index.stylefahion.dtos.ProductoDTO;
import com.index.stylefahion.models.Categoria;
import com.index.stylefahion.models.Producto;
import com.index.stylefahion.repositorys.repoCategoria;
import com.index.stylefahion.repositorys.repoProducto;

@RestController
@PreAuthorize("denyAll()")
@RequestMapping("produc")
public class ProductoController {

    @Autowired
    private repoProducto prod;

    @Autowired
    private repoCategoria cat;

    @GetMapping("inicio/producto")
    @PreAuthorize("permitAll()") // Acceso para: santiago, andrea, anyi, daniel
    public String inicio() {
        return "Conectado";
    }

    @PostMapping("crear/producto")
    @PreAuthorize("hasAuthority('CREATE')") // Acceso para: santiago, daniel, anyi
    public String crearproducto(@RequestBody Producto p) {
        prod.save(p);
        return "Producto Guardado";
    }

    @GetMapping("get/productos")
    @PreAuthorize("hasAuthority('READ')") // Acceso para: santiago, andrea, anyi, daniel
    public List<Producto> veProductos() {
        return prod.findAll();
    }

    @PutMapping("put/producto/{id}")
    @PreAuthorize("hasAuthority('UPDATE')") // Acceso para: santiago, anyi
    public String editproducto(@PathVariable Integer id, @RequestBody Producto p, @RequestBody Categoria c) {
        Producto actualizaProducto = prod.findById(id).get();
        actualizaProducto.setNom_product(p.getNom_product());
        actualizaProducto.setIdcat(c.getIdcat());
        actualizaProducto.setDescrip_product(p.getDescrip_product());
        actualizaProducto.setMarca(p.getMarca());
        actualizaProducto.setPrecio_product(p.getPrecio_product());
        prod.save(actualizaProducto);
        return "Producto Actualizado";
    }

    @DeleteMapping("delete/producto/{id}")
    @PreAuthorize("hasAuthority('DELETE')") // Acceso para: santiago, anyi
    public String borrarprod(@PathVariable Integer id) {
        Producto borrapProducto = prod.findById(id).get();
        prod.delete(borrapProducto);
        return "Producto Eliminado";

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')") // Acceso para: santiago, andrea, anyi, daniel
    public ResponseEntity<ProductoDTO> findProducto(@PathVariable Integer id) {
        Producto producto = prod.findProductoById(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }

        Categoria categoria = cat.findCategoriaByIdcat(id);

        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNom_product(producto.getNom_product());
        dto.setCategoria(categoria.getNomcat());
        dto.setDescrip_product(producto.getDescrip_product());
        dto.setMarca(producto.getMarca());
        dto.setPrecio_product(producto.getPrecio_product());

        return ResponseEntity.ok(dto);
    }

}
