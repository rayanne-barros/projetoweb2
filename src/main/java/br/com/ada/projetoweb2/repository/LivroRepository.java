package br.com.ada.projetoweb2.repository;

import br.com.ada.projetoweb2.model.entity.CategoriaEntity;
import br.com.ada.projetoweb2.model.entity.EditoraEntity;
import br.com.ada.projetoweb2.model.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, Long> {

    List<LivroEntity> findByCategoria(CategoriaEntity categoria);

    List<LivroEntity> findByEditora(EditoraEntity editora);


    @Query("SELECT l FROM LivroEntity l "
            + "WHERE UPPER(l.nome) LIKE CONCAT('%',UPPER(:nome),'%') "
            + "OR (l.isbn = :isbn) ")
    List<LivroEntity> findByNomeOrIsbn(@Param("nome") String nome, @Param("isbn")String isbn);

}
