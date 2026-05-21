package com.example.myacw.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    private String nombre;
    private String apellidos;
    private int edad;
    private String direccion;
    private int cp;
    private int telefono;
    private String email;
    private String ciudad;

    private String user;
    private String pass;

    private String socioEstado;

    private String cuotaEstado;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String apellidos, int edad, String direccion, int cp, int telefono,
            String email, String ciudad, String user, String pass, String socioEstado, String cuotaEstado, Rol rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.direccion = direccion;
        this.cp = cp;
        this.telefono = telefono;
        this.email = email;
        this.ciudad = ciudad;
        this.user = user;
        this.pass = pass;
        this.socioEstado = socioEstado;
        this.cuotaEstado = cuotaEstado;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSocioEstado() {
        return socioEstado;
    }

    public void setSocioEstado(String socioEstado) {
        this.socioEstado = socioEstado;
    }

    public String getCuotaEstado() {
        return cuotaEstado;
    }

    public void setCuotaEstado(String cuotaEstado) {
        this.cuotaEstado = cuotaEstado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
