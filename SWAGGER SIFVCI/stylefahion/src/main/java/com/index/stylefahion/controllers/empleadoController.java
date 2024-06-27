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

import com.index.stylefahion.dtos.EmpleadoDTO;
import com.index.stylefahion.models.Cargo;
import com.index.stylefahion.models.Empleado;
import com.index.stylefahion.repositorys.repoCargo;
import com.index.stylefahion.repositorys.repoEmpleado;

@RestController
@PreAuthorize("denyAll()")
@RequestMapping("emp")
public class empleadoController {

    @Autowired
    private repoEmpleado emp;

    @Autowired
    private repoCargo car;

    @PostMapping("crear/empleado")
    @PreAuthorize("hasAuthority('CREATE')") // Acceso para: santiago, daniel, anyi
    public String crearEmpleado(@RequestBody Empleado e) {
        emp.save(e);
        return "Empleado Guardado";
    }

    @GetMapping("get/empleados")
    @PreAuthorize("hasAuthority('READ')") // Acceso para: santiago, andrea, anyi, daniel
    public List<Empleado> ListarEmpleadoes() {
        return emp.findAll();
    }

    @PutMapping("put/empleado/{id}")
    @PreAuthorize("hasAuthority('UPDATE')") // Acceso para: santiago, anyi
    public String editEmpleado(@PathVariable Integer id, @RequestBody Empleado e) {
        Empleado actualizaEmpleado = emp.findById(id).get();
        actualizaEmpleado.setNom_emp(e.getNom_emp());
        actualizaEmpleado.setAp_emp(e.getAp_emp());
        actualizaEmpleado.setCargoid(e.getCargoid());
        actualizaEmpleado.setTel_emp(e.getTel_emp());
        actualizaEmpleado.setCorreo_emp(e.getCorreo_emp());
        actualizaEmpleado.setDirecc_emp(e.getDirecc_emp());
        emp.save(actualizaEmpleado);
        return "Empleado Actualizado";
    }

    @DeleteMapping("delete/empleado/{id}")
    @PreAuthorize("hasAuthority('DELETE')") // Acceso para: santiago, anyi
    public String borraremp(@PathVariable Integer id) {
        Empleado borrapEmpleado = emp.findById(id).get();
        emp.delete(borrapEmpleado);
        return "Empleado Eliminado";

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')") // Acceso para: santiago, andrea, anyi, daniel
    public ResponseEntity<EmpleadoDTO> findEmpleado(@PathVariable Integer id) {
        Empleado Empleado = emp.findEmpleadoByEmpid(id);
        if (Empleado == null) {
            return ResponseEntity.notFound().build();
        }
        Cargo Cargo = car.findCargoByCargoid(id);

        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setEmpid(Empleado.getEmpid());
        dto.setNombre_completo(Empleado.getNom_emp() + Empleado.getAp_emp());
        dto.setCargo(Cargo.getNomcargo());
        dto.setTelefono(Empleado.getTel_emp());
        dto.setDireccion(Empleado.getDirecc_emp());

        return ResponseEntity.ok(dto);
    }

}
