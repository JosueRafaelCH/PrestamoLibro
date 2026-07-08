package com.example.prestamoLibro.Model.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.prestamoLibro.Model.Entity.Libro;
import com.example.prestamoLibro.Model.Entity.Prestamo;
import com.example.prestamoLibro.Model.Entity.Usuario;



public interface PrestamoServiceIface {
    
    //Servicio para el Usurio
    public Page<Usuario> BuscarUsuriosTodos(Pageable pageable);
    public Usuario buscarUsuarioPorId(Long id);
    //Servicio Prestamo
    public void guardarPrestamo(Prestamo prestamo);

    //Servicio Libro
    public List<Libro> buscarLibrosPorDescripcion(String term);
    public Libro buscarLibrosPorId(Long id);

}
