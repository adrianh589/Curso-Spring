package mx.com.gm.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Clase que nos ayudara a encriptar las contras de los usuarios
 */
public class EncriptarPassword {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        var password = "123";
        System.out.println("password: " + password);
        System.out.println("password encriptado: " + encriptarPassword(password));
    }

    public static String encriptarPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}
