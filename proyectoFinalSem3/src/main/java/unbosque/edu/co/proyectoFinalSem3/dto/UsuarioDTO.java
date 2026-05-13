package unbosque.edu.co.proyectoFinalSem3.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UsuarioDTO extends PersonaDTO {

	private static final long serialVersionUID = 1L;
	
	private List<VirusTotalUploadResponseDTO> historial;

	public UsuarioDTO() {
		this.setRole(PersonaDTO.Role.USUARIO);
	}

	public UsuarioDTO(List<VirusTotalUploadResponseDTO> historial) {
		super();
		this.historial = List.copyOf(historial);
		this.setRole(PersonaDTO.Role.USUARIO);
	}

	public UsuarioDTO(String nombre, String email, String telefono, String contrasena,
			List<VirusTotalUploadResponseDTO> historial) {
		super(nombre, email, telefono, contrasena);
		this.historial = List.copyOf(historial);
		this.setRole(PersonaDTO.Role.USUARIO);
	}

	public UsuarioDTO(String nombre, String email, String telefono, String contrasena) {
		super(nombre, email, telefono, contrasena);
		this.setRole(PersonaDTO.Role.USUARIO);
	}

	public List<VirusTotalUploadResponseDTO> getHistorial() {
		return historial;
	}

	public void setHistorial(List<VirusTotalUploadResponseDTO> historial) {
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
