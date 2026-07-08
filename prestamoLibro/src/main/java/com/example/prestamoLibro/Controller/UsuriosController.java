package com.example.prestamoLibro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.prestamoLibro.Model.Entity.Usuario;
import com.example.prestamoLibro.Model.Service.PrestamoServiceIface;
import com.example.prestamoLibro.Utils.paginator.PageRender;

@Controller
@RequestMapping("/prestamolibro")
@SessionAttributes("usuarios")
public class UsuriosController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    private final PrestamoServiceIface prestamoService;

    public UsuriosController(PrestamoServiceIface prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping("/usuarioslistar")
	public String usuariosListar(@RequestParam(name = "pag", defaultValue = "0") int pag, Model model) {

		System.out.println(passwordEncoder.encode("Abc123"));
		Pageable pagina = PageRequest.of(pag, 5);
		Page<Usuario> usuario = prestamoService.BuscarUsuriosTodos(pagina);

		PageRender<Usuario> pageRender = new PageRender<>("/prestamolibro/usuarioslistar", usuario);

		model.addAttribute("pageRender", pageRender);
		model.addAttribute("titulo", "Listado de usuarios activos");
		model.addAttribute("usuarios", usuario);
		return "usuario/listado_usuarios";
	}

	
	@GetMapping("/usuarioconsultar/{id}")
	public String usuarioConsultar(@PathVariable Long id, RedirectAttributes flash, Model model) {
		Usuario usuario = prestamoService.buscarUsuarioPorId(id); 

		

		if (usuario == null) {
			flash.addFlashAttribute("warning", "El registro no fue hallado en la base de datos");
			return "redirect:/prestamolibro/usuarioslistar";
		}
		model.addAttribute("titulo", "Consulta del cliente: " + usuario.getNombreCompleto());
		model.addAttribute("usuario", usuario);

		return "usuario/consulta_usuario";
	}	

    


    
}
