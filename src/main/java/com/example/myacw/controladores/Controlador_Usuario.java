package com.example.myacw.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myacw.modelo.Usuario;
import com.example.myacw.modelo.Rol;
import com.example.myacw.repositorio.Repositorio_Usuario;
import com.example.myacw.repositorio.Repositorio_Rol;
import com.example.myacw.servicio.Servicio_Usuario;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class Controlador_Usuario {

    private final Servicio_Usuario servicioUsuario;
    private final Repositorio_Usuario repositorioUsuario;
    private final Repositorio_Rol repositorioRol;

    public Controlador_Usuario(Servicio_Usuario servicioUsuario,
                               Repositorio_Usuario repositorioUsuario,
                               Repositorio_Rol repositorioRol) {
        this.servicioUsuario = servicioUsuario;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioRol = repositorioRol;
    }

    @GetMapping
    public List<Usuario> getAll() {
        return servicioUsuario.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id) {
        Usuario usuario = servicioUsuario.findById(id);
        if (usuario == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {

        List<String> errores = new ArrayList<>();

        if (usuario.getNombre() == null || usuario.getNombre().isBlank())
            errores.add("El NOMBRE es obligatorio");

        if (usuario.getApellidos() == null || usuario.getApellidos().isBlank())
            errores.add("Los APELLIDOS son obligatorios");

        if (usuario.getEdad() <= 0)
            errores.add("La EDAD debe ser mayor que 0");

        if (usuario.getDireccion() == null || usuario.getDireccion().isBlank())
            errores.add("La DIRECCION es obligatoria");

        if (usuario.getCiudad() == null || usuario.getCiudad().isBlank())
            errores.add("La CIUDAD es obligatoria");

        if (usuario.getCp() < 10000 || usuario.getCp() > 99999)
            errores.add("El CÓDIGO POSTAL debe tener 5 dígitos");

        if (usuario.getTelefono() < 600000000 || usuario.getTelefono() > 799999999)
            errores.add("El TELEFONO debe tener 9 dígitos");

        if (usuario.getEmail() == null || usuario.getEmail().isBlank())
            errores.add("El EMAIL es obligatorio");

        if (usuario.getUser() == null || usuario.getUser().isBlank())
            errores.add("El USUARIO es obligatorio");

        if (usuario.getPass() == null || usuario.getPass().isBlank())
            errores.add("La CONTRASEÑA es obligatoria");

        if (repositorioUsuario.existsByTelefono(usuario.getTelefono()))
            errores.add("El TELEFONO ya está registrado");

        if (repositorioUsuario.existsByEmail(usuario.getEmail()))
            errores.add("El EMAIL ya está registrado");

        if (repositorioUsuario.existsByUser(usuario.getUser()))
            errores.add("El USUARIO ya está registrado");

        if (usuario.getRol() == null || usuario.getRol().getIdRol() == 0) {
            errores.add("El ROL es obligatorio");
        } else if (usuario.getRol().getIdRol() != 4) {
            errores.add("Solo puedes registrarte como Fan/Luchador.");
        }

        if (!errores.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("errores", errores));

        Rol rol = repositorioRol.findById(usuario.getRol().getIdRol()).orElse(null);
        if (rol == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("errores", List.of("El ROL indicado no existe")));

        usuario.setRol(rol);

        // VALORES AUTOMÁTICOS COMO TEXTO
        usuario.setSocioEstado("Activo");
        usuario.setCuotaEstado("Pendiente");

        Usuario guardado = servicioUsuario.save(usuario);

        Map<String, Object> body = new HashMap<>();
        body.put("idUsuario", guardado.getIdUsuario());
        body.put("rol", Map.of("idRol", guardado.getRol().getIdRol()));

        return ResponseEntity.ok(body);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {

        String user = loginData.get("user");
        String pass = loginData.get("pass");

        Usuario usuario = repositorioUsuario.findByUser(user);

        if (usuario == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Usuario no encontrado"));

        if (!usuario.getPass().equals(pass))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Contraseña incorrecta"));

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Usuario actualizado = servicioUsuario.update(id, usuario);
        if (actualizado == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        servicioUsuario.delete(id);
        return ResponseEntity.noContent().build();
    }
}
