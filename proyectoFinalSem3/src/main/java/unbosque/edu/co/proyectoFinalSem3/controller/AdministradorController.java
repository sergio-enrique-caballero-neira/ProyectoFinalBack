package unbosque.edu.co.proyectoFinalSem3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import unbosque.edu.co.proyectoFinalSem3.dto.AdministradorDTO;
import unbosque.edu.co.proyectoFinalSem3.service.AdministradorService;

@RestController
@RequestMapping("/administrador")
@CrossOrigin(origins = { "http://localhost:8080", "*" })
@Transactional
@SecurityRequirement(name = "bearerAuth")
public class AdministradorController {
	
	@Autowired
	private AdministradorService administradorService;
	
	public AdministradorController() {
		// TODO Auto-generated constructor stub
	}
	
    @PostMapping("/crear")
    public ResponseEntity<String> crearUsuarioNormal(@RequestParam String nombre, @RequestParam String email, @RequestParam String telefono, @RequestParam String contrasena, @RequestParam String cargo){
        AdministradorDTO nuevo = new AdministradorDTO(nombre, email, telefono,contrasena, cargo);
        administradorService.create(nuevo);
        return new ResponseEntity<>("Dato Creado con exito", HttpStatus.CREATED);
    }


    @GetMapping("/mostrartodo")
    public ResponseEntity<List<AdministradorDTO>> mostrarTodo(){
        return new ResponseEntity<>(administradorService.getAll(), HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestParam long id, @RequestParam String nombre, @RequestParam String email, @RequestParam String telefono, @RequestParam String contrasena, @RequestParam String cargo){
    	AdministradorDTO nuevo = new AdministradorDTO(nombre, email, telefono, contrasena, cargo);
    	administradorService.updateByID(id,nuevo);
        return new ResponseEntity<>("Dato actualizado con exito", HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminar(@RequestParam long id){
    	administradorService.deleteByID(id);
        return new ResponseEntity<>("Dato eliminado con exito", HttpStatus.ACCEPTED);
    }
	
}
