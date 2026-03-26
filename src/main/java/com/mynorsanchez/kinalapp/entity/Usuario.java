package com.mynorsanchez.kinalapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column (name = "codigo_usuario")
    private String codigoUsuario;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String rol;
    @Column
    private Long estado;

    public Usuario(){

    }

    public Usuario(String codigoUsuario, String username, String password, String email, String rol, Long estado) {
        this.codigoUsuario = codigoUsuario;
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.estado = estado;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public Long getEstado() {
        return estado;
    }
    public void setEstado(Long estado) {
        this.estado = estado;
    }
}
