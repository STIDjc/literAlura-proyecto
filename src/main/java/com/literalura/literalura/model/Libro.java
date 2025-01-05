package com.literalura.literalura.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    @ManyToOne
    private Autor autor;
    private String idioma;
    private long numeroDeDescargas;
    public Libro() {}

    public Libro(DatosLibros datosLibros) {
//        this.id = datosLibros.id();
        this.titulo = datosLibros.titulo();
        try{
            this.autor = datosLibros.datosAutores().stream().map(Autor::new).collect(Collectors.toList()).get(0);
        }catch(Exception e){
            this.autor = new Autor();
        }
        this.idioma = datosLibros.idiomas().get(0);
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public long getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(long numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "_________________________________________________________\n"+
                "LIBRO ENCONTRADO: \n" +
                " id: " + id +"\n"+
                " Titulo del libro: " + titulo +"\n"+
                "_________________________________________________________\n"+
                "DATOS DEL AUTOR " + autor +"\n"+
                " Idioma del libro: " + idioma +"\n"+
                " Numero de descargas del libro: " + numeroDeDescargas +"\n"+
                "_________________________________________________________"
                ;
    }
}
