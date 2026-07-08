package com.example.prestamoLibro.Model.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prestamoLibro.Model.Dao.LibrosDAOIface;
import com.example.prestamoLibro.Model.Dao.PrestamosDAOIface;
import com.example.prestamoLibro.Model.Dao.UsuariosDAOIface;
import com.example.prestamoLibro.Model.Entity.Libro;
import com.example.prestamoLibro.Model.Entity.Prestamo;
import com.example.prestamoLibro.Model.Entity.Usuario;


@Service
public class PrestamoService implements PrestamoServiceIface {

    private final PrestamosDAOIface PrestamoDAO;
    private final UsuariosDAOIface UsurioDAO;
    private final LibrosDAOIface LibroDAO;

    

    public PrestamoService(PrestamosDAOIface prestamoDAO, UsuariosDAOIface usuriosDAO, LibrosDAOIface libroDAO) {
        PrestamoDAO = prestamoDAO;
        UsurioDAO = usuriosDAO;
        LibroDAO = libroDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> BuscarUsuriosTodos(Pageable pageable) {
        return UsurioDAO.findAll(pageable); 
    }

    @Override
    @Transactional
    public void guardarPrestamo(Prestamo prestamo) {
        PrestamoDAO.save(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorId(Long id) {
        return UsurioDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> buscarLibrosPorDescripcion(String term) {
        return LibroDAO.findByTituloContainingIgnoreCaseAndEstadoTrue(term);
    }

 


    @Override
    @Transactional(readOnly = true)
    public Libro buscarLibrosPorId(Long id) {
        return LibroDAO.findById(id).orElse(null);
    }
   
}
