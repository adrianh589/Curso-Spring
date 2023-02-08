package mx.com.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity // Representar una tabla de la base de datos
@Data // Getters, Setters, haschode y el otro que no me acuerdo xD
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @OneToMany // Relacion uno a muchos, un usuario tiene 1 o muchos roles
    @JoinColumn(name = "id_usuario") // Llave foranea de la tabla
    private List<Rol> roles;
}
