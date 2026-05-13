package unbosque.edu.co.proyectoFinalSem3.dto;

import org.springframework.security.core.userdetails.UserDetails;

public abstract class PersonaDTO implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private long id;
    private String nombre;
    private String email;
    private String telefono;
    private String contrasena;
    private Role role;
    
    public PersonaDTO() {
		// TODO Auto-generated constructor stub
	}

	public PersonaDTO(String nombre, String email, String telefono, String contrasena) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.contrasena = contrasena;
	}
	
	public enum Role {
		/** Usuario regular con permisos básicos */
		USUARIO, 
		/** Administrador con permisos completos */
		ADMIN
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
