package gm.tienda_libros.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/*
    NOTA: Instalar el plugin de Lombok para que funcione perfectamente!
 */

@Entity
//lombok
@Data // Incluye -> Getter, Setter y ToString
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idLibro;
    String nombrelibro;
    String autor;
    Double precio;
    Integer existencias;
}
