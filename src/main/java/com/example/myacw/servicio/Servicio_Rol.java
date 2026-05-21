package com.example.myacw.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.myacw.modelo.Rol;
import com.example.myacw.repositorio.Repositorio_Rol;

@Service
public class Servicio_Rol {

    private final Repositorio_Rol repositorioRol;

    public Servicio_Rol(Repositorio_Rol repositorioRol) {
        this.repositorioRol = repositorioRol;
    }

    public List<Rol> findAll() {
        return repositorioRol.findAll();
    }

    public Rol findById(Integer id) {
        return repositorioRol.findById(id).orElse(null);
    }

    public Rol save(Rol rol) {
        return repositorioRol.save(rol);
    }

    public Rol update(Integer id, Rol rol) {
        Rol existente = findById(id);
        if (existente == null)
            return null;

        existente.setNombreRol(rol.getNombreRol());
        existente.setDescripcion(rol.getDescripcion());
        existente.setNivel(rol.getNivel());

        return repositorioRol.save(existente);
    }

    public void delete(Integer id) {
        repositorioRol.deleteById(id);
    }
}
