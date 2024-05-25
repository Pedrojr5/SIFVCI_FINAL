package index.sifvci.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import index.sifvci.models.Cliente;
import index.sifvci.repository.repoCliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("cliente")
public class clienteController {
    @Autowired
    public repoCliente cli;

    @GetMapping("inicio")
    public String inicioCli() {
        return "Conectado";
    }

    @GetMapping("get")
    public List<Cliente> getClientes() {
        return cli.findAll();

    }

}
