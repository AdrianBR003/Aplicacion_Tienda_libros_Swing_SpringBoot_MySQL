package gm.tienda_libros;

import gm.tienda_libros.presentacion.LibroForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

@SpringBootApplication
public class TiendaLibrosApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext contextoSpring =
				new SpringApplicationBuilder(TiendaLibrosApplication.class)
						.headless(false)
						.web(WebApplicationType.NONE)// Ya que no va a ser una aplicacion web, necesitamos instanciar otra configuracion
						.run(args);

		// Ejecutamos el codigo para cargar el formulario
		EventQueue.invokeLater(()->{
			// Obtenemos el objeto form a traves de Spring || IMPORTANTE!!
			LibroForm libroForm = contextoSpring.getBean(LibroForm.class);
			libroForm.setVisible(true);
		});
	}


}
