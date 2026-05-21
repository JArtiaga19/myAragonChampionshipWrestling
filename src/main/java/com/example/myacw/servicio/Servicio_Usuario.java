package com.example.myacw.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.myacw.modelo.Usuario;
import com.example.myacw.repositorio.Repositorio_Usuario;

@Service
public class Servicio_Usuario {

    private final Repositorio_Usuario usuarioRepositorio;

    public Servicio_Usuario(Repositorio_Usuario usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    // Obtener todos los usuarios
    public List<Usuario> findAll() {
        return usuarioRepositorio.findAll();
    }

    // Buscar un usuario por ID
    public Usuario findById(Integer id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    // Guardar un nuevo usuario
    public Usuario save(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    // Actualizar un usuario existente
    public Usuario update(Integer id, Usuario usuario) {
        Usuario existente = findById(id);
        if (existente == null)
            return null;

        existente.setNombre(usuario.getNombre());
        existente.setApellidos(usuario.getApellidos());
        existente.setEdad(usuario.getEdad());
        existente.setDireccion(usuario.getDireccion());
        existente.setCp(usuario.getCp());
        existente.setTelefono(usuario.getTelefono());
        existente.setEmail(usuario.getEmail());
        existente.setCiudad(usuario.getCiudad());
        existente.setUser(usuario.getUser());
        existente.setPass(usuario.getPass());

        // AHORA SON STRINGS, NO BOOLEANOS
        existente.setSocioEstado(usuario.getSocioEstado());
        existente.setCuotaEstado(usuario.getCuotaEstado());

        existente.setRol(usuario.getRol());

        return usuarioRepositorio.save(existente);
    }

    // Eliminar un usuario
    public void delete(Integer id) {
        usuarioRepositorio.deleteById(id);
    }
}
