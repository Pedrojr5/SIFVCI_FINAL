package com.index.stylefahion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.index.stylefahion.dtos.ClienteDTO;
import com.index.stylefahion.models.Cliente;
import com.index.stylefahion.repositorys.repoCliente;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@PreAuthorize("denyAll()")
@RequestMapping("cli")
public class clienteController {
    @Autowired
    public repoCliente cli;

    @PostMapping("crear/cliente")
    @PreAuthorize("hasAuthority('CREATE')") // Acceso para: santiago, daniel, anyi
    public String crearCliente(@RequestBody Cliente cl) {
        cli.save(cl);
        return "Cliente Guardado";
    }

    @GetMapping("get/clientes")
    @PreAuthorize("hasAuthority('READ')") // Acceso para: santiago, andrea, anyi, daniel
    public List<Cliente> ListarClientes() {
        return cli.findAll();
    }

    @PutMapping("put/clientes/{id}")
    @PreAuthorize("hasAuthority('UPDATE')") // Acceso para: santiago, anyi
    public String editClientes(@PathVariable Integer id, @RequestBody Cliente c) {
        Cliente actualizaCliente = cli.findById(id).get();
        actualizaCliente.setNom_cli(c.getNom_cli());
        actualizaCliente.setAp_cli(c.getAp_cli());
        actualizaCliente.setDoc_cli(c.getDoc_cli());
        actualizaCliente.setTel_cli(c.getTel_cli());
        actualizaCliente.setCorreo_cli(c.getCorreo_cli());
        actualizaCliente.setDirecc_cli(c.getDirecc_cli());
        actualizaCliente.setCiudad(c.getCiudad());
        cli.save(actualizaCliente);
        return "Cliente Actualizado";
    }

    @DeleteMapping("delete/cliente/{id}")
    @PreAuthorize("hasAuthority('DELETE')") // Acceso para: santiago, anyi
    public String borrarCliente(@PathVariable Integer id) {
        Cliente borrarCliente = cli.findById(id).get();
        cli.delete(borrarCliente);
        return "Cliente Eliminado";

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')") // Acceso para: santiago, andrea, anyi, daniel
    public ResponseEntity<ClienteDTO> findCliente(@PathVariable Integer id) {
        Cliente Cliente = cli.findClienteByClienteid(id);
        if (Cliente == null) {
            return ResponseEntity.notFound().build();
        }

        ClienteDTO dto = new ClienteDTO();
        dto.setClienteid(Cliente.getClienteid());
        dto.setNombre_completo(Cliente.getNom_cli() + Cliente.getAp_cli());
        dto.setTel_cli(Cliente.getTel_cli());
        dto.setDireccion(Cliente.getDirecc_cli());

        return ResponseEntity.ok(dto);
    }

}
