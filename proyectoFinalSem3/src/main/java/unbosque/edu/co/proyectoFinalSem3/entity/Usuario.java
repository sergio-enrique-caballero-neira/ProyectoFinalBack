package unbosque.edu.co.proyectoFinalSem3.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario extends Persona {


	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<VirusTotalUploadResponse> historial;

	public Usuario() {
		this.setRole(Persona.Role.USUARIO);
	}

	public Usuario(List<VirusTotalUploadResponse> historial) {
		super();
		this.historial = List.copyOf(historial);
		this.setRole(Persona.Role.USUARIO);
	}

	public Usuario(String nombre, String email, String telefono, String contrasena,
			List<VirusTotalUploadResponse> historial) {
		super(nombre, email, telefono, contrasena);
		this.historial = List.copyOf(historial);
		this.setRole(Persona.Role.USUARIO);
	}

	public Usuario(String nombre, String email, String telefono, String contrasena) {
		super(nombre, email, telefono, contrasena);
		this.historial = new ArrayList<>();
		this.setRole(Persona.Role.USUARIO);
	}

	public List<VirusTotalUploadResponse> getHistorial() {
		return historial;
	}

	public void setHistorial(List<VirusTotalUploadResponse> historial) {
		this.historial = List.copyOf(historial);
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
