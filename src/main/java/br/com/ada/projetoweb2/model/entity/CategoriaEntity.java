package br.com.ada.projetoweb2.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categoria")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<LivroEntity> livros;
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
}
