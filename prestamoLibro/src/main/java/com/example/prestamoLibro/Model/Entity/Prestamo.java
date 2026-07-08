package com.example.prestamoLibro.Model.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_prestamo")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
    private LocalDate fechaPrestamo;

    @Column(name = "fecha_devolucion")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
    private LocalDate fechaDevolucion;

    private String observacion;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL)
    private List<Libro> libros;

    public Prestamo() {
        libros = new ArrayList<>();
    }


    public Prestamo(Long id, LocalDate fechaPrestamo, LocalDate fechaDevolucion, String observacion) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.observacion = observacion;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }


    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }


    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }


    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }


    public String getObservacion() {
        return observacion;
    }


    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Libro> getLibros() {
        return libros;
    }


    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }



    @Override
    public String toString() {
        return "Prestamo [id=" + id + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion
                + ", observacion=" + observacion + "]";
    }


    
    
}
