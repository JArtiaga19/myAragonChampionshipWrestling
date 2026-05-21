package com.example.myacw.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myacw.modelo.Rol;

public interface Repositorio_Rol extends JpaRepository<Rol, Integer> {
    boolean existsByNombreRol(String nombreRol);
}
