package unbosque.edu.co.proyectoFinalSem3.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AdministradorDTO extends PersonaDTO {

	private static final long serialVersionUID = 1L;
	
	private String cargo;
	
	public AdministradorDTO() {
		this.setRole(PersonaDTO.Role.ADMIN);
	}

	public AdministradorDTO(String cargo) {
		super();
		this.cargo = cargo;
		this.setRole(PersonaDTO.Role.ADMIN);
	}

	public AdministradorDTO(String nombre, String email, String telefono, String contrasena, String cargo) {
		super(nombre, email, telefono, contrasena);
		this.cargo = cargo;
		this.setRole(PersonaDTO.Role.ADMIN);
	}

	public AdministradorDTO(String nombre, String email, String telefono, String contrasena) {
		super(nombre, email, telefono, contrasena);
		this.setRole(PersonaDTO.Role.ADMIN);
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + this.getRole().name()));
	}

	@Override
	public String getPassword() {
		return super.getContrasena();
	}

	@Override
	public String getUsername() {
		return super.getNombre();
	}
}
