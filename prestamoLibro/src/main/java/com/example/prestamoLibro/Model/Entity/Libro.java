package com.example.prestamoLibro.Model.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name="isbn", nullable= false, length= 50)
    private String isbn;

    @NotEmpty
    @Column(name="titulo", nullable=false, length= 100)
    private String titulo;

    @NotEmpty
    @Column(name="autor", nullable=false, length= 100)
    private String autor;

    @NotEmpty
    @Column(name="editorial", nullable=false, length=50)
    private String editorial;

    @NotEmpty
    @Column(name="año", nullable=false, length=10)
    private String año;

    @NotEmpty
    @Column(name="edicion", nullable=false, length=30)
    private String edicion;

   
    @Column(name="estado", nullable=false)
    private Boolean estado;


    @ManyToOne
    @JoinColumn(name = "prestamo_id")
    @JsonIgnore
    private Prestamo prestamo;


    public Libro() {
    }

    public Libro(Long id, String isbn, String titulo, String autor, String editorial, String año, String edicion) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.año = año;
        this.edicion = edicion;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getAño() {
        return año;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }


    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }



    @Override
    public String toString() {
        return "Libro [id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", editorial="
                + editorial + ", año=" + año + ", edicion=" + edicion + "]";
    }

    

    

 
    

    

    
}
