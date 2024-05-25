package index.sifvci.models;

import java.text.DecimalFormat;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idprodut;

    @Column
    private String nombreproduc;

    @Column
    private String descripcion;

    @Column
    private Integer precio;

    @Column
    private DecimalFormat Stock;

    @OneToMany(mappedBy = "productos")
    public List<CategoriaProducto> categorias;

}
