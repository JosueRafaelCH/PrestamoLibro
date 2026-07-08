package com.example.prestamoLibro.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prestamoLibro.Model.Entity.Prestamo;

@Repository
public interface PrestamosDAOIface extends JpaRepository<Prestamo, Long>{
    
}
