package br.com.ada.projetoweb2.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaDTO {
    private Long id;
    @Size(max=100,message="Tamanho do nome ácima do permitido")
    @NotBlank(message="Nome deve conter algum valor")
    private String nome;
}
