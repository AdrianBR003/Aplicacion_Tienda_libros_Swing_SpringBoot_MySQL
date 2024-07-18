package gm.tienda_libros.servicio;

import gm.tienda_libros.modelo.Libro;

import java.util.List;

// Declaramos los metodos basicos de anyadir - editar - eliminar - etc
public interface ILibroServicio {

    public List<Libro> listarLibros();

    public Libro buscarLibroPorId(Integer idLibro);

    public void guardarLibro(Libro libro);

    public void eliminarLibro(Libro libro);

}
