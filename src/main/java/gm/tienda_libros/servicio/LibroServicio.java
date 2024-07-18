package gm.tienda_libros.servicio;

import gm.tienda_libros.modelo.Libro;
import gm.tienda_libros.repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LibroServicio implements ILibroServicio{

    @Autowired // Para que Spring inyecte una instancia de este objeto
    private LibroRepositorio libroRepositorio;



    // Metodos Implementados por la interfaz
    @Override
    public List<Libro> listarLibros() {
        return libroRepositorio.findAll(); // Nos devuelve todo lo que encuentre en la BD
    }

    @Override
    public Libro buscarLibroPorId(Integer idLibro) {
        Libro libro = libroRepositorio.findById(idLibro).orElse(null);
        return libro;
    }

    @Override
    public void guardarLibro(Libro libro) {
        libroRepositorio.save(libro); // De manera automatica se efecturara un inject o save, dependiendo de si la id es nula o no
    }

    @Override
    public void eliminarLibro(Libro libro) {
        libroRepositorio.delete(libro);
    }
}
