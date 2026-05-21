package com.example.myacw.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.myacw.modelo.Rol;
import com.example.myacw.servicio.Servicio_Rol;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin("*")
public class Controlador_Rol {

    private final Servicio_Rol servicioRol;

    public Controlador_Rol(Servicio_Rol servicioRol) {
        this.servicioRol = servicioRol;
    }

    @GetMapping
    public List<Rol> getAll() {
        return servicioRol.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Rol rol) {

        List<String> errores = new ArrayList<>();

        if (rol.getNombreRol() == null || rol.getNombreRol().isBlank())
            errores.add("El NOMBRE_ROL es obligatorio");

        if (rol.getDescripcion() == null || rol.getDescripcion().isBlank())
            errores.add("La DESCRIPCION es obligatoria");

        if (rol.getNivel() == null)
            errores.add("El NIVEL es obligatorio");

        if (!errores.isEmpty()) {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("errores", errores);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        Rol guardado = servicioRol.save(rol);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {

        Rol rol = servicioRol.findById(id);

        if (rol == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe el rol con ID " + id);

        return ResponseEntity.ok(rol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Rol rol) {

        Rol actualizado = servicioRol.update(id, rol);

        if (actualizado == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe el rol con ID " + id);

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        Rol existente = servicioRol.findById(id);

        if (existente == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe el rol con ID " + id);

        servicioRol.delete(id);

        return ResponseEntity.ok("Rol eliminado correctamente");
    }
}
