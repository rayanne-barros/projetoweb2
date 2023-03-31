package br.com.ada.projetoweb2.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "livro")
public class LivroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="categoria", nullable=false)
    private CategoriaEntity categoria;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="editora", nullable=false)
    private EditoraEntity editora;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "isbn", nullable = false, unique = true)
    @Size(max = 13, message = "O 'isbn' do 'livro' só pode ter no máximo 13 caracteres")
    private String isbn;

}
