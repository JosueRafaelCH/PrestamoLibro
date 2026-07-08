package com.example.prestamoLibro.Model.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.prestamoLibro.Model.Dao.UserDAOIface;
import com.example.prestamoLibro.Model.Entity.Rol;
import com.example.prestamoLibro.Model.Entity.UserAccount;


@Service
public class UsuarioDetailService implements UserDetailsService{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UserDAOIface userDAO;

    public UsuarioDetailService(UserDAOIface userDAO) {
        this.userDAO = userDAO;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        //Obtener usuaruis de base de datos
		UserAccount usuarioUser = userDAO.findByNombre(username);

		if (usuarioUser == null) {
			logger.info("*** Error de autenticación, el usuario ...");
			throw new UsernameNotFoundException("*** Error de autenticación, el usuario ...");
		}
        
		// si usuario existe, obener sus roñes
		List<GrantedAuthority> roles = new ArrayList<>();
		for (Rol rol : usuarioUser.getRoles()){
			logger.info("*** Rol : " + rol.getNombre());
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}


		//si no hay roles
		if (roles.isEmpty()){
			logger.warn("El usuario no" + usuarioUser.getNombre() + "no tiene roles asignados");
			throw new UsernameNotFoundException("El usuario" + usuarioUser.getNombre() + "no tiene roles asignados");
		}

		//retornar un nuevo usuario del tipo security

		return new User(usuarioUser.getNombre(), usuarioUser.getClave(), usuarioUser.isActivo(),true,true,true, roles);
    }
    
}
