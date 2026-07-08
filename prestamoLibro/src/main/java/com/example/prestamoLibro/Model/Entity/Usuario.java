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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name= "identificacion", nullable = false, length = 10)
    private String identificacion;

    @NotEmpty
    @Column(name= "telefono", nullable= false, length= 15)
    private String telefono;

    @NotEmpty
    @Column(name="nombre_completo", nullable= false, length= 80)
    private String nombreCompleto;

    @NotEmpty
    @Column(name="correo", length= 80)
    @Email
    private String correo;


    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestamo> prestamos;

    public Usuario() {
       prestamos = new ArrayList<>();
    }

    

    public Usuario(Long id, String identificacion, String telefono, String nombreCompleto, String correo) {
        this.id = id;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
    }

    

    public Long getId() {
        return id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public List<Prestamo> getPrestamos() {
        return prestamos;
    }



    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }


 

    @Override
    public String toString() {
        return "Usurios [id=" + id + ", idetificacion=" + identificacion + ", telefono=" + telefono + ", nombreCompleto="
                + nombreCompleto + ", correo=" + correo + "]";
    }



  
    

    

    

    


    
}
