package com.example.myacw.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myacw.modelo.Usuario;

public interface Repositorio_Usuario extends JpaRepository<Usuario, Integer> {
    boolean existsByEmail(String email); // Verificar si el email ya existe
    boolean existsByTelefono(Integer telefono); // Verificar si el teléfono ya existe
    boolean existsByUser(String user); // Verificar si el nombre de usuario ya existe

    Usuario findByUser(String user); // Encontrar un usuario por su nombre de usuario
}