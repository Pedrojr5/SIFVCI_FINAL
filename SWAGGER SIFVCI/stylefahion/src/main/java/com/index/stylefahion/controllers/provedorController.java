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

import com.index.stylefahion.dtos.ProvedorDTO;
import com.index.stylefahion.models.Categoria;
import com.index.stylefahion.models.Provedor;
import com.index.stylefahion.repositorys.repoProvedor;

@RestController
@PreAuthorize("denyAll()")
@RequestMapping("prov")
public class provedorController {

    @Autowired
    private repoProvedor prov;

    @PostMapping("crear/provedor")
    @PreAuthorize("hasAuthority('CREATE')") // Acceso para: santiago, daniel, anyi
    public String crearproducto(@RequestBody Provedor pr) {
        prov.save(pr);
        return "Provedor Guardado";
    }

    @GetMapping("get/provedores")
    @PreAuthorize("hasAuthority('READ')") // Acceso para: santiago, andrea, anyi, daniel
    public List<Provedor> ListarProvedores() {
        return prov.findAll();
    }

    @PutMapping("put/provedores/{id}")
    @PreAuthorize("hasAuthority('UPDATE')") // Acceso para: santiago, anyi
    public String editprovedores(@PathVariable Integer id, @RequestBody Provedor p, @RequestBody Categoria c) {
        Provedor actualizaProvedor = prov.findById(id).get();
        actualizaProvedor.setNom_prov(p.getNom_prov());
        actualizaProvedor.setTel_prov(p.getTel_prov());
        actualizaProvedor.setCorreo_prov(p.getCorreo_prov());
        actualizaProvedor.setDirecc_prov(p.getDirecc_prov());
        prov.save(actualizaProvedor);
        return "Provedor Actualizado";
    }

    @DeleteMapping("delete/provedor/{id}")
    @PreAuthorize("hasAuthority('DELETE')") // Acceso para: santiago, anyi
    public String borrarprov(@PathVariable Integer id) {
        Provedor borrapProvedor = prov.findById(id).get();
        prov.delete(borrapProvedor);
        return "Provedor Eliminado";

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')") // Acceso para: santiago, andrea, anyi, daniel
    public ResponseEntity<ProvedorDTO> findProvedor(@PathVariable Integer id) {
        Provedor provedor = prov.findProvedorByProvid(id);
        if (provedor == null) {
            return ResponseEntity.notFound().build();
        }

        ProvedorDTO dto = new ProvedorDTO();
        dto.setProvid(provedor.getProvid());
        dto.setNom_prov(provedor.getNom_prov());
        dto.setTel_prov(provedor.getTel_prov());
        dto.setCorreo_prov(provedor.getCorreo_prov());

        return ResponseEntity.ok(dto);
    }

}
