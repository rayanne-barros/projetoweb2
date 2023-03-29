package br.com.ada.projetoweb2.controller;

import br.com.ada.projetoweb2.model.dto.LivroDTO;
import br.com.ada.projetoweb2.model.dto.MensagemDTO;
import br.com.ada.projetoweb2.service.LivroService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
@Slf4j
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> pegarUm(@PathVariable("id") Long id) {

        try {

            return ResponseEntity.ok(livroService.pegarPorId(id));

        } catch (EntityNotFoundException ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new MensagemDTO(ex.getMessage()));

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody @Valid LivroDTO livroDTO) {

        try {

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(livroService.criar(livroDTO));

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(
            @RequestBody @Valid LivroDTO livroDTO,
            @PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok(
                    livroService.editar(livroDTO, id));

        } catch (EntityNotFoundException ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO(ex.getMessage()));

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(
            @PathVariable("id") Long id) {

        try {
            livroService.deletar(id);
            return ResponseEntity
                    .ok(new MensagemDTO("Categoria com id " + id + " removido com sucesso!"));

        } catch (EntityNotFoundException ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO(ex.getMessage()));

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Object> listar() {

        try {
            return ResponseEntity.ok(livroService.listar());

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<Object> pegarPorCategoria(@PathVariable Long idCategoria) {
        try {
            return ResponseEntity.ok(livroService.listarPorCategoria(idCategoria));

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @GetMapping("/editora/{idEditora}")
    public ResponseEntity<Object> pegarPorEditora(@PathVariable Long idEditora) {
        try {
            return ResponseEntity.ok(livroService.listarPorEditora(idEditora));

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }
    @GetMapping("/filter")
    public ResponseEntity<Object> pegarPorNomeOuIsbn(@RequestParam(name = "nome", defaultValue = "") String nome,
                                                     @RequestParam(name = "isbn", defaultValue = "") String isbn) {
        try {
            return ResponseEntity.ok(livroService.filtrar(nome, isbn));

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }
}
