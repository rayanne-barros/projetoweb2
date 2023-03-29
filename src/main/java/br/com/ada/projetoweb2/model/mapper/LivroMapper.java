package br.com.ada.projetoweb2.model.mapper;

import br.com.ada.projetoweb2.model.dto.LivroDTO;
import br.com.ada.projetoweb2.model.entity.LivroEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivroMapper {

    private CategoriaMapper categoriaMapper = new CategoriaMapper();
    private EditoraMapper editoraMapper = new EditoraMapper();
    public LivroDTO update(LivroEntity livro) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livro.getId());
        livroDTO.setCategoria(categoriaMapper.update(livro.getCategoria()));
        livroDTO.setEditora(editoraMapper.update(livro.getEditora()));
        livroDTO.setNome(livro.getNome());
        livroDTO.setIsbn(livro.getIsbn());
        return livroDTO;
    }

    public LivroEntity update(LivroDTO livro) {
        LivroEntity livroEntity = new LivroEntity();
        livroEntity.setId(livro.getId());
        livroEntity.setCategoria(categoriaMapper.update(livro.getCategoria()));
        livroEntity.setEditora(editoraMapper.update(livro.getEditora()));
        livroEntity.setNome(livro.getNome());
        livroEntity.setIsbn(livro.getIsbn());
        return livroEntity;
    }

    public List<LivroEntity> updateListEntity(List<LivroDTO> listaDTO){
        return listaDTO.stream()
                .map(livroDTO->
                        this.update(livroDTO))
                .toList();
    }

    public List<LivroDTO> updateListDTO(List<LivroEntity> listaEntity){
        return listaEntity.stream()
                .map(livroEntity->
                        this.update(livroEntity))
                .toList();
    }
}
