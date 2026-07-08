package com.example.prestamoLibro.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prestamoLibro.Model.Entity.UserAccount;



public interface UserDAOIface extends JpaRepository<UserAccount, Long>{
    public UserAccount findByNombre(String nombre);
}
