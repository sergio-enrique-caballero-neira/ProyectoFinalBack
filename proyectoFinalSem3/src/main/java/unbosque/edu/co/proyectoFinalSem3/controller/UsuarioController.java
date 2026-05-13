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
import unbosque.edu.co.proyectoFinalSem3.dto.UsuarioDTO;
import unbosque.edu.co.proyectoFinalSem3.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = { "http://localhost:8080", "*" })
@Transactional
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	public UsuarioController() {
		// TODO Auto-generated constructor stub
	}
	
    @PostMapping("/crear")
    public ResponseEntity<String> crearUsuarioNormal(@RequestParam String nombre, @RequestParam String email, @RequestParam String telefono, @RequestParam String contrasena){
        UsuarioDTO nuevo = new UsuarioDTO(nombre, email, telefono,contrasena);
        usuarioService.create(nuevo);
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
    }


    @GetMapping("/mostrartodo")
    public ResponseEntity<List<UsuarioDTO>> mostrarTodo(){
        return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/getIdByUsername")
    public ResponseEntity<Long> getIdByUsername(@RequestParam String nombre){
		return new ResponseEntity<>(usuarioService.getIdByUsername(nombre), HttpStatus.ACCEPTED);
	}
    
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestParam long id, @RequestParam String nombre, @RequestParam String email, @RequestParam String telefono, @RequestParam String contrasena){
        UsuarioDTO nuevo = new UsuarioDTO(nombre, email, telefono, contrasena);
        usuarioService.updateByID(id,nuevo);
        return new ResponseEntity<>("Dato actualizado con exito", HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminar(@RequestParam long id){
        usuarioService.deleteByID(id);
        return new ResponseEntity<>("Dato eliminado con exito", HttpStatus.ACCEPTED);
    }
}
