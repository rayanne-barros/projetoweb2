package br.com.ada.projetoweb2.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categoria")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "categoria")
    private List<LivroEntity> livros;
    @Column(name = "nome", nullable = false, unique = true)
    @Size(max = 100, message = "O 'nome' da 'categoria' deve ter no máximo 100 caracteres")
    private String nome;
}
