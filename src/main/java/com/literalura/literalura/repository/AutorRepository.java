package com.literalura.literalura.repository;

import com.literalura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreContainsIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeMuerte >= :fecha AND a.fechaDeNacimiento <= :fecha")
    List<Autor> buscarAutoresVivos(int fecha);
}
