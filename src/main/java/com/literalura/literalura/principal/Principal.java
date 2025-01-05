package com.literalura.literalura.principal;

import com.literalura.literalura.model.Autor;
import com.literalura.literalura.model.Datos;
import com.literalura.literalura.model.DatosLibros;
import com.literalura.literalura.model.Libro;
import com.literalura.literalura.repository.AutorRepository;
import com.literalura.literalura.repository.LibroRepository;
import com.literalura.literalura.service.ConsumoAPI;
import com.literalura.literalura.service.ConvierteDatos;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    Scanner scan = new Scanner(System.in);
    private final String URL_API ="https://gutendex.com/books/?search=";
    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;
    private String json;
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private List<DatosLibros> datosLibros;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.repositorioLibro = libroRepository;
        this.repositorioAutor = autorRepository;
    }


    public void menu() {
//        json = consumoAPI.obtenerDatosApi(URL_API);
//        datosLibros = convierteDatos.convertirDatosJson(json, Datos.class).datosLibros();
//        Libro libroApi = datosLibros.stream()
//                .map(Libro::new).toList().get(0);
//        System.out.printf("Consumo: %s\n", json);
//        System.out.printf("Libro: %s\n", libroApi);
        var opcion = -1;
        while (opcion != 0) {

            var menu ="""
                    
                    _____________________________________________
                    Elija la opcion a través de su número
                    
                    1- Buscar libro por titulo
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos en un determinado año
                    5- Listar libros por idioma 
                    0- Salir
                    _______________________________________________
                    """;
            System.out.println(menu);
            while (true){
                try {
                    opcion = Integer.parseInt(scan.nextLine());
                    break;
                }catch (NumberFormatException e){
                    System.out.println("Introduzca un numero de la opcion deseada");
                }
            }
            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
            }

        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Introduzca el titulo del libro");
        var tituloLibrol = scan.nextLine();
        json = consumoAPI.obtenerDatosApi(URL_API + tituloLibrol.replace(" ", "%20"));
//        System.out.println(json);
        datosLibros = convierteDatos.convertirDatosJson(json, Datos.class).datosLibros();
        if (!datosLibros.isEmpty()) {
            var libroApi = datosLibros.stream().map(Libro::new).toList().get(0);
            System.out.println(libroApi);
            Optional<Libro> libroDB = repositorioLibro.findByTituloContainsIgnoreCase(libroApi.getTitulo());
            if (!libroDB.isPresent()) {
                Optional<Autor> autorDB = repositorioAutor.findByNombreContainsIgnoreCase(libroApi.getAutor().getNombre());
                if (!autorDB.isPresent()) {
                    repositorioAutor.save(libroApi.getAutor());
                    repositorioLibro.save(libroApi);
                }else {
                    libroApi.setAutor(autorDB.get());
                    repositorioLibro.save(libroApi);
                }
            }else{
                System.out.println("El libro ya esta registrado");
            }
        }else {
            System.out.println("no se pudo encontrar el libro");
        }
    }

    private void listarLibrosRegistrados() {
        repositorioLibro.findAll().forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        repositorioAutor.findAll().forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        int fecha;
        while (true){
            try{
                System.out.println("De que año desea conocer los autores vivos");
                fecha = Integer.parseInt(scan.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.println("Escriba una fecha (AÑO)");
            }
        }
        List<Autor> autores = repositorioAutor.buscarAutoresVivos(fecha);
        System.out.println(autores);
    }

    private void listarLibrosPorIdioma() {
        String idioma = "";
        int opcion = -1;
        var menu = """
              _________________________________________________________
                Ingresa la opcion del idioma para buscar el libro
              
               1- Español
               2- Ingles
               3- Frances
               4- Portugues
              ___________________________________________________________
              """;
        while (opcion < 1 || opcion > 4) {
            while (true){
                try{
                    System.out.println(menu);
                    opcion = Integer.parseInt(scan.nextLine());
                    break;
                }catch (NumberFormatException e){
                    System.out.println("Por favor ingrese la opcion deseada \n"+menu);

                }
            }
            switch (opcion) {
                case 1:
                    idioma = "es";
                    break;
                case 2:
                    idioma = "en";
                    break;
                case 3:
                    idioma = "fr";
                    break;
                case 4:
                    idioma = "pt";
                    break;

                default:
                    System.out.println("Opcion no valida intentelo de nuevo");
            }

        }
        //Buscar los libros en la base de datos por su idioma
        List<Libro> librosEncontrados = repositorioLibro.findByIdiomaContainsIgnoreCase(idioma);
        if (librosEncontrados.isEmpty()) {
            System.out.println("No hay libros encontrados por el idioma");
        }else {
            librosEncontrados.forEach(System.out::println);
        }
    }
}

