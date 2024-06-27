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

import com.index.stylefahion.models.Cargo;
import com.index.stylefahion.repositorys.repoCargo;

@RestController
@PreAuthorize("denyAll()")
@RequestMapping("cargo")
public class cargoContoller {

    @Autowired
    public repoCargo car;

    @PostMapping("crearcargo")
    @PreAuthorize("hasAuthority('CREATE')") // Acceso para: santiago, daniel, anyi
    public ResponseEntity<Object> crearCargo(@RequestBody Cargo c) {
        Optional<Cargo> res = car.findCargoByNomcargo(c.getNomcargo());
        HashMap<String, Object> datos = new HashMap<>();

        if (res.isPresent()) {
            datos.put("error", "true");
            datos.put("mensaje", "El nombre de Cargo pertenece a otro Cargo");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }
        car.save(c);
        datos.put("data", c);
        datos.put("mensaje", "Cargo creado");
        return new ResponseEntity<>(datos, HttpStatus.ACCEPTED);
    }

    @GetMapping("get/cargos")
    @PreAuthorize("hasAuthority('READ')") // Acceso para: santiago, andrea, anyi, daniel
    public List<Cargo> getCargos() {
        return car.findAll();
    }

    @PutMapping("put/cargo/{id}")
    @PreAuthorize("hasAuthority('UPDATE')") // Acceso para: santiago, anyi
    public String editarCargo(@PathVariable Integer id, @RequestBody Cargo c) {
        Cargo actualizaCargo = car.findById(id).get();
        actualizaCargo.setNomcargo(c.getNomcargo());
        car.save(actualizaCargo);
        return "Cargo Actualizado";
    }

    @DeleteMapping("delete/cargo/{id}")
    @PreAuthorize("hasAuthority('DELETE')") // Acceso para: santiago, anyi
    public String borrarCargo(@PathVariable Integer id) {
        Cargo borrarCargo = car.findById(id).get();
        car.delete(borrarCargo);
        return "Cargo Eliminado";

    }

}
