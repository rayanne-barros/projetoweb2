package br.com.ada.projetoweb2.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LivroDTO {
    private Long id;
    private CategoriaDTO categoria;
    private EditoraDTO editora;
    @NotBlank(message="Nome deve conter algum valor")
    private String nome;
    @Size(max=13,message="Tamanho do nome acima do permitido")
    @NotBlank(message="ISBN deve conter algum valor")
    private String isbn;
}
