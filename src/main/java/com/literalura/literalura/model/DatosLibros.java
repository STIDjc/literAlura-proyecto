package com.literalura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("id") long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors")List<DatosAutor> datosAutores,
        @JsonAlias("languages")List<String> idiomas,
        @JsonAlias("download_count") long numeroDeDescargas
        ) {
}
