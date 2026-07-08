package com.example.prestamoLibro.Controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.prestamoLibro.Model.Entity.Libro;
import com.example.prestamoLibro.Model.Entity.Prestamo;
import com.example.prestamoLibro.Model.Entity.Usuario;
import com.example.prestamoLibro.Model.Service.PrestamoService;
import com.example.prestamoLibro.Model.Service.PrestamoServiceIface;

import jakarta.validation.Valid;


@Controller
@SessionAttributes("prestamo")
@RequestMapping("/prestamolibro")
public class PrestamoController {

    private final PrestamoServiceIface prestamoService;

   

    public PrestamoController(PrestamoService prestamoService) {
		this.prestamoService = prestamoService;
	}

	@GetMapping("/prestamonuevo/{id}")
	public String prestamoNuevo(@PathVariable Long id, Model model, RedirectAttributes flash) {

		Usuario usuario = prestamoService.buscarUsuarioPorId(id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "El usuario no existe en la base de datos !!");
			return "redirect:/prestamolibro/usuarioslistar";
		}

		Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);		   

		model.addAttribute("titulo", "Nueva prestamo");
		model.addAttribute("btn_accion", "Guardar prestamo");
		model.addAttribute("prestamo", prestamo);   

		return "prestamo/prestamo_nuevo";
	}

    @GetMapping(value = "/cargarlibros/{term}",  produces = "application/json")
	@ResponseBody
	public List<Libro> cargarLibro(@PathVariable String term) {
		return prestamoService.buscarLibrosPorDescripcion(term);
	}

	@PostMapping("/guardarprestamo")
	public String guardarPrestamo(
        @Valid @ModelAttribute Prestamo prestamo,
        BindingResult result,
        @RequestParam(name = "detalle_id[]", required = false) Long[] detalleId, 
        SessionStatus status,
        RedirectAttributes flash,
        Model model) {

    if (result.hasErrors()) {
        model.addAttribute("titulo", "Nueva prestamo");
        model.addAttribute("btn_accion", "Guardar prestamo");
        model.addAttribute("warning", "Complete los campos del prestamo");
        return "prestamo/prestamo_nuevo";
    }

    if (detalleId == null || detalleId.length == 0) {
        model.addAttribute("titulo", "Nueva prestamo");
        model.addAttribute("btn_accion", "Guardar prestamo");
        model.addAttribute("error", "Debe incluir por lo menos un prestamo");
        return "prestamo/prestamo_nuevo";
    }

    for (Long libroId : detalleId) {
        Libro libro = prestamoService.buscarLibrosPorId(libroId);

        // Verificar si el libro ya está prestado
        if (libro.getEstado() == null || !libro.getEstado()) {
            flash.addFlashAttribute("error", "El libro '" + libro.getTitulo() + "' ya está prestado.");
            return "redirect:/prestamolibro/prestamonuevo/" + prestamo.getUsuario().getId();
        }

        // Asociar libro al préstamo
        libro.setPrestamo(prestamo);
        libro.setEstado(false); // marcarlo como no disponible
        prestamo.getLibros().add(libro);
    }

    prestamoService.guardarPrestamo(prestamo);
    status.setComplete();

    flash.addFlashAttribute("success", "Préstamo guardado correctamente.");
    return "redirect:/prestamolibro/usuarioconsultar/" + prestamo.getUsuario().getId();
}

	


	

	

}



    



