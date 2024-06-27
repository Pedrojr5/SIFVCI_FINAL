package com.index.stylefahion.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.index.stylefahion.models.Categoria;
import com.index.stylefahion.repositorys.repoCategoria;

@RestController
@PreAuthorize("denyAll()")
@RequestMapping("cat")
public class CategoriaController {

    @Autowired
    public repoCategoria cat;

    @GetMapping("inicio/categoria")
    @PreAuthorize("permitAll()") // Acceso para: santiago, andrea, anyi, daniel
    public String iniciocategoria() {
        return "Conectado";
    }

    @PostMapping("crearcategoria")
    @PreAuthorize("hasAuthority('CREATE')") // Acceso para: santiago, daniel, anyi
    public ResponseEntity<Object> creacategoria(@RequestBody Categoria c) {
        Optional<Categoria> res = cat.findByNomcat(c.getNomcat());
        HashMap<String, Object> datos = new HashMap<>();

        if (res.isPresent()) {
            datos.put("error", "true");
            datos.put("mensaje", "El nombre de categoria pertenece a otra categoria");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }
        cat.save(c);
        datos.put("data", c);
        datos.put("mensaje", "categoria creada");
        return new ResponseEntity<>(datos, HttpStatus.ACCEPTED);
    }

    @GetMapping("get/categorias")
    @PreAuthorize("hasAuthority('READ')") // Acceso para: santiago, andrea, anyi, daniel
    public List<Categoria> getCategorias() {
        return cat.findAll();
    }

    @PutMapping("put/categoria/{id}")
    @PreAuthorize("hasAuthority('UPDATE')") // Acceso para: santiago, anyi
    public String editarcategoria(@PathVariable Integer id, @RequestBody Categoria c) {
        Categoria actualizaCategoria = cat.findById(id).get();
        actualizaCategoria.setNomcat(c.getNomcat());
        cat.save(actualizaCategoria);
        return "Categoria Actualizada";
    }

    @DeleteMapping("delete/categoria/{id}")
    @PreAuthorize("hasAuthority('DELETE')") // Acceso para: santiago, anyi
    public String borrarcat(@PathVariable Integer id) {
        Categoria borraCategoria = cat.findById(id).get();
        cat.delete(borraCategoria);
        return "Categoria Eliminada";

    }

}
