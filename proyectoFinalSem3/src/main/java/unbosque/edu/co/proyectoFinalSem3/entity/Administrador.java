package unbosque.edu.co.proyectoFinalSem3.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "administradores")
public class Administrador extends Persona {

	private static final long serialVersionUID = 1L;
	
	private String cargo;
	
	public Administrador() {
		this.setRole(Persona.Role.ADMIN);
	}

	public Administrador(String cargo) {
		super();
		this.cargo = cargo;
		this.setRole(Persona.Role.ADMIN);
	}

	public Administrador(String nombre, String email, String telefono, String contrasena, String cargo) {
		super(nombre, email, telefono, contrasena);
		this.cargo = cargo;
		this.setRole(Persona.Role.ADMIN);
	}

	public Administrador(String nombre, String email, String telefono, String contrasena) {
		super(nombre, email, telefono, contrasena);
		this.setRole(Persona.Role.ADMIN);
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
