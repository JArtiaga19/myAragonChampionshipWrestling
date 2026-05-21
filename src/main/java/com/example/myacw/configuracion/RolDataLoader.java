package com.example.myacw.configuracion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.myacw.modelo.Rol;
import com.example.myacw.repositorio.Repositorio_Rol;

@Component
public class RolDataLoader implements CommandLineRunner {

    private final Repositorio_Rol repositorioRol;

    public RolDataLoader(Repositorio_Rol repositorioRol) {
        this.repositorioRol = repositorioRol;
    }

    @Override
    public void run(String... args) throws Exception {

        crearRolSiNoExiste("Presidente", "Acceso total al sistema", 1);
        crearRolSiNoExiste("Secretario", "Acceso a usuarios y cuotas", 2);
        crearRolSiNoExiste("Vocal", "Gestión de los espectáculos", 3);
        crearRolSiNoExiste("Fan/Luchador", "Acceso a información general", 4);
    }

    private void crearRolSiNoExiste(String nombre, String descripcion, int nivel) {
        if (!repositorioRol.existsByNombreRol(nombre)) {
            Rol rol = new Rol();
            rol.setNombreRol(nombre);
            rol.setDescripcion(descripcion);
            rol.setNivel(nivel);
            repositorioRol.save(rol);
        }
    }
}
