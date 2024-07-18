package gm.tienda_libros.presentacion;

import gm.tienda_libros.modelo.Libro;
import gm.tienda_libros.servicio.LibroServicio;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

/*
    Utilizamos JFrame para visualiar el formulario

*/
@Component
public class LibroForm extends JFrame {

    LibroServicio libroServicio;
    private JPanel panel;

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
}
