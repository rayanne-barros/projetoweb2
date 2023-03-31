package br.com.ada.projetoweb2.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "editora")
public class EditoraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "editora")
    private List<LivroEntity> livros;
    @Column(name = "nome", nullable = false, unique = true)
    @Size(max = 255, message = "O 'nome' da 'editora' deve ter no máximo 255 caracteres")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
}
