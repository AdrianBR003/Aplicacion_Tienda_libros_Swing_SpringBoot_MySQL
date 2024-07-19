package gm.tienda_libros.presentacion;

import gm.tienda_libros.modelo.Libro;
import gm.tienda_libros.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/*
    Utilizamos JFrame para visualiar el formulario

*/
@Component
public class LibroForm extends JFrame {

    LibroServicio libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private JTextField libroTexto;
    private JTextField autorTexto;
    private JTextField precioTexto;
    private JTextField existenciasTexto;
    private JButton Agregar;
    private JButton modificarButton;
    private JButton eliminarButton;
    private DefaultTableModel tablaModeloLibros;

    // Para injectar Spring vamos a hacerlo a traves del constructo en vez de un atributo
    @Autowired
    public LibroForm(LibroServicio libroServicio){
        this.libroServicio = libroServicio;
        iniciarForma();

        // Boton Agregar - Accion Listener
        Agregar.addActionListener(e -> agregarLibro());
    }

    private void iniciarForma(){
        setContentPane(panel); // Apartado visual
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para que se cierre con exito
        setVisible(true);
        setSize(900,700);
        Toolkit toolkit = Toolkit.getDefaultToolkit(); // Obtener las dimensiones de la ventana
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth()/2);
        int y = (tamanioPantalla.height - getHeight()/2);
        setLocation(x,y); // El punto donde se muestra nuestra aplicacion

    }

    private void agregarLibro(){
        // Leer los valores del formulario
        if(libroTexto.getText().isEmpty()){
            mostrarMensaje("Proporciona el nombre del libro");
            libroTexto.requestFocusInWindow(); // Para cambiar el cursor
            return;
        }else if(autorTexto.getText().isEmpty()){
            mostrarMensaje("Proporciona el autor del libro");
            libroTexto.requestFocusInWindow(); // Para cambiar el cursor
            return;
        }else if(precioTexto.getText().isEmpty()){
            mostrarMensaje("Proporciona el precio del libro");
            libroTexto.requestFocusInWindow(); // Para cambiar el cursor
            return;
        }else if(existenciasTexto.getText().isEmpty()){
            mostrarMensaje("Proporciona el numero de existencias del libro");
            libroTexto.requestFocusInWindow(); // Para cambiar el cursor
            return;
        }else {
            // SE PUEDE SIMPLIFICAR!!!
            var nombreLibro = libroTexto.getText();
            var autor = autorTexto.getText();
            var precio = Double.parseDouble(precioTexto.getText());
            var existencias = Integer.parseInt(existenciasTexto.getText());
            // Crear el objeto libro
            var libro = new Libro(null, nombreLibro, autor, precio, existencias);
//        libro.setNombrelibro(nombreLibro);
//        libro.setAutor(autor);
//        libro.setPrecio(precio);
//        libro.setExistencias(existencias);
            this.libroServicio.guardarLibro(libro); // Como la id = 0, se creara una nueva instancia
            mostrarMensaje("Se agrego el Libro...");
            limpiarFormulario();
            // Tenemos que recargar de nuevo los valores de la tabla
            listarLibros();
        }
    }

    private void limpiarFormulario(){
        libroTexto.setText("");
        autorTexto.setText("");
        precioTexto.setText("");
        existenciasTexto.setText("");
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje); // Mandar un mensaje alerta
    }

    private void createUIComponents() { // Personalizar los componentes de la tabla
        // TODO: place custom component creation code here
        this.tablaModeloLibros = new DefaultTableModel(0,5); // En un inicio 0 filas, 5 columnas
        String[] cabeceros = {"Id", "Libro", "Autor", "Precio", "Existencias"};
        this.tablaModeloLibros.setColumnIdentifiers(cabeceros);
        // Instancia el objeto JTable
        this.tablaLibros = new JTable(tablaModeloLibros); // Este es el modelo que debe de seguir
        listarLibros();
    }

    // Metodo para listar los libros
    private void listarLibros(){
        // Limpiar la tabla
        tablaModeloLibros.setRowCount(0); // Asi se limpia cualquier registro
        // Obtener los libros de la BD
        var libros = libroServicio.listarLibros();
        libros.forEach((libro)->{
            Object[] renglonLibro = { //Informacion de cada una de las columnas
                    libro.getIdLibro(),
                    libro.getNombrelibro(),
                    libro.getAutor(),
                    libro.getPrecio(),
                    libro.getExistencias()
            };
            this.tablaModeloLibros.addRow(renglonLibro);
        });

    }


}
