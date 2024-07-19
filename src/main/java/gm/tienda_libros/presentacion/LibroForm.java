package gm.tienda_libros.presentacion;

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
    private DefaultTableModel tablaModeloLibros;

    // Para injectar Spring vamos a hacerlo a traves del constructo en vez de un atributo
    @Autowired
    public LibroForm(LibroServicio libroServicio){
        this.libroServicio = libroServicio;
        iniciarForma();
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

    private void createUIComponents() { // Personalizar los componentes de la tabla
        // TODO: place custom component creation code here
        this.tablaModeloLibros = new DefaultTableModel(0,5); // En un inicio 0 filas, 5 columnas
        String[] cabeceros = {"Id", "Libro", "Autor", "Precio", "Existencias"};
        this.tablaModeloLibros.setColumnIdentifiers(cabeceros);
        // Instancia el objeto JTable
        this.tablaLibros = new JTable(tablaModeloLibros); // Este es el modelo que debe de seguir

    }
}
