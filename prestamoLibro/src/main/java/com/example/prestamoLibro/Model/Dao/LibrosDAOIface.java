package com.example.prestamoLibro.Model.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prestamoLibro.Model.Entity.Libro;


@Repository
public interface LibrosDAOIface extends JpaRepository<Libro, Long> {

  public List<Libro> findByTituloContainingIgnoreCaseAndEstadoTrue(String term);
    
}
