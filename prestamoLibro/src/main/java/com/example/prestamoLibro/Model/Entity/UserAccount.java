package com.example.prestamoLibro.Model.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usersAccount")
public class UserAccount {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(length = 20, unique = true)
    private String nombre;

    @Column(length = 128)
    private String clave;

    private boolean activo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userAccount_id")
	private List<Rol> roles;

    public UserAccount() {
        roles = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserAccount [id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", activo=" + activo + ", roles=" + roles
                + "]";
    }  
    
}
