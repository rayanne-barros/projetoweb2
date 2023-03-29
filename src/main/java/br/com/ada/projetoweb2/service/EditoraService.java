package br.com.ada.projetoweb2.service;

import br.com.ada.projetoweb2.model.dto.EditoraDTO;
import br.com.ada.projetoweb2.model.entity.EditoraEntity;
import br.com.ada.projetoweb2.model.mapper.EditoraMapper;
import br.com.ada.projetoweb2.repository.EditoraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {
    @Autowired
    private EditoraRepository repository;
    @Autowired
    private EditoraMapper mapper;

    public EditoraDTO pegarPorId(Long id) {

        Optional<EditoraEntity> editoraEntityOp =
                repository.findById(id);

        if(editoraEntityOp.isPresent()) {
            EditoraEntity editoraEntity = editoraEntityOp.get();
            return mapper.update(editoraEntity);
        }

        throw new EntityNotFoundException("Editora não encontrada");
    }

    public EditoraDTO criar(EditoraDTO editoraDTO) {

        EditoraEntity editora = mapper.update(editoraDTO);

        editora = repository.save(editora);

        return mapper.update(editora);
    }

    public EditoraDTO editar(EditoraDTO editoraDTO, Long id) {

        if(repository.existsById(id)) {

            EditoraEntity editoraEntity = mapper.update(editoraDTO);
            editoraEntity.setId(id);
            editoraEntity = repository.save(editoraEntity);

            return mapper.update(editoraEntity);
        }

        throw new EntityNotFoundException("Editora não encontrada");
    }

    public void deletar(Long id){
        Optional<EditoraEntity> editoraEntityOp =
                repository.findById(id);

        if(editoraEntityOp.isPresent()) {
            EditoraEntity editoraEntity = editoraEntityOp.get();
            repository.delete(editoraEntity);
            return;
        }

        throw new EntityNotFoundException("Editora não encontrada");
    }

    public List<EditoraDTO> listar() {
        List<EditoraEntity> listaEntities =  repository.findAll();
        return mapper.updateListDTO(listaEntities);
    }
}
