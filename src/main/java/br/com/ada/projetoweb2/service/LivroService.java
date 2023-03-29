package br.com.ada.projetoweb2.service;

import br.com.ada.projetoweb2.model.dto.CategoriaDTO;
import br.com.ada.projetoweb2.model.dto.LivroDTO;
import br.com.ada.projetoweb2.model.entity.CategoriaEntity;
import br.com.ada.projetoweb2.model.entity.EditoraEntity;
import br.com.ada.projetoweb2.model.entity.LivroEntity;
import br.com.ada.projetoweb2.model.mapper.LivroMapper;
import br.com.ada.projetoweb2.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private LivroMapper mapper;

    public LivroDTO pegarPorId(Long id) {
        Optional<LivroEntity> livroEntityOp = repository.findById(id);

        if(livroEntityOp.isPresent()) {
            LivroEntity livroEntity = livroEntityOp.get();
            return mapper.update(livroEntity);
        }

        throw new EntityNotFoundException("Livro não encontrado");
    }

    public LivroDTO criar(LivroDTO livroDTO) {
        LivroEntity livro = mapper.update(livroDTO);
        livro = repository.save(livro);
        return mapper.update(livro);
    }

    public LivroDTO editar(LivroDTO livroDTO, Long id) {
        if(repository.existsById(id)) {
            LivroEntity livroEntity = mapper.update(livroDTO);
            livroEntity.setId(id);
            livroEntity = repository.save(livroEntity);
            return mapper.update(livroEntity);
        }
        throw new EntityNotFoundException("Livro não encontrado");
    }

    public void deletar(Long id) {
        Optional<LivroEntity> livroEntityOp = repository.findById(id);
        if(livroEntityOp.isPresent()) {
            LivroEntity livroEntity = livroEntityOp.get();
            repository.delete(livroEntity);
            return;
        }
        throw new EntityNotFoundException("Livro não encontrado");
    }

    public List<LivroDTO> listar() {
        List<LivroEntity> livroEntities = repository.findAll();
        return mapper.updateListDTO(livroEntities);
    }

    public List<LivroDTO> listarPorCategoria(Long idCategoria) {
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setId(idCategoria);
        List<LivroEntity> livroEntities = repository.findByCategoria(categoria);
        return mapper.updateListDTO(livroEntities);
    }

    public List<LivroDTO> listarPorEditora(Long idEditora) {
        EditoraEntity editora = new EditoraEntity();
        editora.setId(idEditora);
        List<LivroEntity> livroEntities = repository.findByEditora(editora);
        return mapper.updateListDTO(livroEntities);
    }

    public List<LivroDTO> filtrar(String nome, String isbn) {
        List<LivroEntity> livroEntities = repository.findByNomeOrIsbn(nome,isbn);
        return mapper.updateListDTO(livroEntities);
    }
}
