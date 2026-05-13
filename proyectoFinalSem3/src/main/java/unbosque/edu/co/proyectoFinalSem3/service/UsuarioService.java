package unbosque.edu.co.proyectoFinalSem3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import unbosque.edu.co.proyectoFinalSem3.dto.UsuarioDTO;
import unbosque.edu.co.proyectoFinalSem3.dto.VirusTotalUploadResponseDTO;
import unbosque.edu.co.proyectoFinalSem3.entity.Usuario;
import unbosque.edu.co.proyectoFinalSem3.entity.VirusTotalUploadResponse;
import unbosque.edu.co.proyectoFinalSem3.exception.BadRequestException;
import unbosque.edu.co.proyectoFinalSem3.exception.ResourceNotFoundException;
import unbosque.edu.co.proyectoFinalSem3.repository.UsuarioRepository;

@Service
public class UsuarioService implements CRUDoperation<UsuarioDTO> {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	public UsuarioService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int create(UsuarioDTO data) {
		if (data == null) {
			throw new BadRequestException("Datos de usuario no proporcionados");
		}

		if (data.getNombre() == null || data.getNombre().length() < 6 || data.getNombre().length() > 50) {
			throw new BadRequestException("El nombre debe tener minimo 6 caracteres y maximo 50");
		}

		if (data.getNombre().contains(" ") || !data.getNombre().matches("^[a-zA-Z0-9]+$")) {
			throw new BadRequestException("El nombre de usuario no puede contener espacios ni caracteres especiales");
		}

		if (data.getContrasena() == null || data.getContrasena().trim().isEmpty() || data.getContrasena().length() < 8
				|| data.getContrasena().length() > 64) {
			throw new BadRequestException("Contraseña inválida: mínimo 8 y máximo 64 caracteres");
		}

		if (data.getContrasena() != null && (!data.getContrasena().matches(".*[A-Z].*")
				|| !data.getContrasena().matches(".*[a-z].*") || !data.getContrasena().matches(".*\\d.*")
				|| !data.getContrasena().matches(".*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>?,./].*"))) {
			throw new BadRequestException(
					"Contraseña débil: debe incluir mayúsculas, minúsculas, números y carácter especiales");
		}

		if (data.getEmail() == null || data.getEmail().trim().isEmpty() || data.getEmail().trim().length() > 120) {
			throw new BadRequestException("Email inválido");
		}

		if (data.getEmail().contains(" ")) {
			throw new BadRequestException("El email no puede contener espacios");

		}

		if (data.getEmail() != null
				&& !data.getEmail().trim().matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$")) {
			throw new BadRequestException("Formato de email inválido");
		}

		if (data.getTelefono() == null || data.getTelefono().trim().isEmpty()
				|| !data.getTelefono().trim().matches("^\\d{10}$")) {
			throw new BadRequestException("Teléfono inválido: solo números, de 10 dígitos");
		}

		if (data.getTelefono().contains(" ")) {
			throw new BadRequestException("El telefono no puede contener espacios");
		}

		existByNombre(data.getNombre());
		existByEmail(data.getEmail());
		existByTelefono(data.getTelefono());
		
		Usuario entidad = mapper.map(data, Usuario.class);
	    entidad.setContrasena(passwordEncoder.encode(entidad.getContrasena()));

	    usuarioRepository.save(entidad);
		return 0;
	}

	@Override
	public List<UsuarioDTO> getAll() {
		List<Usuario> entityList = (List<Usuario>) usuarioRepository.findAll();
		List<UsuarioDTO> dtoList = new ArrayList<>();

		entityList.forEach((entity) -> {
			dtoList.add(mapper.map(entity, UsuarioDTO.class));
		});

		if (dtoList.isEmpty()) {
			throw new ResourceNotFoundException("No se encontraron usuarios");
		}

		return dtoList;
	}

	@Override
	public int deleteByID(Long id) {
		if (id == null || id < 0) {
			throw new BadRequestException("ID inválido");
		}

		Optional<Usuario> encontrado = usuarioRepository.findById(id);
		if (!encontrado.isPresent()) {
			throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
		}

		usuarioRepository.deleteById(id);
		return 0;
	}

	@Override
	public int updateByID(Long id, UsuarioDTO data) {
		if (id == null || id <= 0) {
			throw new BadRequestException("ID inválido");
		}

		if (data == null) {
			throw new BadRequestException("Datos de usuario no proporcionados");
		}
		
		Optional<Usuario> encontrado = usuarioRepository.findById(id);
		
		if (!encontrado.isPresent()) {
			throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
		}

		if (data.getNombre() == null || data.getNombre().length() < 6 || data.getNombre().length() > 50) {
			throw new BadRequestException("El nombre debe tener minimo 6 caracteres y maximo 50");
		}

		if (data.getNombre().contains(" ") || !data.getNombre().matches("^[a-zA-Z0-9]+$")) {
			throw new BadRequestException("El nombre de usuario no puede contener espacion ni caracteres especiales");
		}

		if (data.getContrasena() == null || data.getContrasena().trim().isEmpty() || data.getContrasena().length() < 8
				|| data.getContrasena().length() > 64) {
			throw new BadRequestException("Contraseña inválida: mínimo 8 y máximo 64 caracteres");
		}

		if (data.getContrasena() != null && (!data.getContrasena().matches(".*[A-Z].*")
				|| !data.getContrasena().matches(".*[a-z].*") || !data.getContrasena().matches(".*\\d.*")
				|| !data.getContrasena().matches(".*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>?,./].*"))) {
			throw new BadRequestException(
					"Contraseña débil: debe incluir mayúsculas, minúsculas, números y carácter especiales");
		}

		if (data.getEmail() == null || data.getEmail().trim().isEmpty() || data.getEmail().trim().length() > 120) {
			throw new BadRequestException("Email inválido");
		}

		if (data.getEmail().contains(" ")) {
			throw new BadRequestException("El email no puede contener espacios");

		}

		if (data.getEmail() != null
				&& !data.getEmail().trim().matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$")) {
			throw new BadRequestException("Formato de email inválido");
		}

		if (data.getTelefono() == null || data.getTelefono().trim().isEmpty()
				|| !data.getTelefono().trim().matches("^\\d{10}$")) {
			throw new BadRequestException("Teléfono inválido: solo números, de 10 dígitos");
		}

		if (data.getTelefono().contains(" ")) {
			throw new BadRequestException("El telefono no puede contener espacios");
		}

		UsuarioDTO temp = mapper.map(encontrado.get(), UsuarioDTO.class);
		temp.setNombre(data.getNombre());
		temp.setContrasena(passwordEncoder.encode(data.getContrasena()));
		temp.setEmail(data.getEmail());
		temp.setTelefono(data.getTelefono());

		usuarioRepository.save(mapper.map(temp, Usuario.class));

		return 0;
	}

	@Override
	public long count() {
		return usuarioRepository.count();
	}

	@Override
	public boolean exist(Long id) {
		return usuarioRepository.existsById(id);
	}

	public boolean existByEmail(String email) {
		usuarioRepository.findAll().forEach(usuario -> {
			if (usuario.getEmail().equals(email)) {
				throw new BadRequestException("El email ya esta registrado");
			}
		});
		return false;
	}

	public boolean existByTelefono(String telefono) {
		usuarioRepository.findAll().forEach(usuario -> {
			if (usuario.getTelefono().equals(telefono)) {
				throw new BadRequestException("El telefono ya esta registrado");
			}
		});
		return false;
	}

	public boolean existByNombre(String nombre) {
		usuarioRepository.findAll().forEach(usuario -> {
			if (usuario.getNombre().equals(nombre)) {
				throw new BadRequestException("El nombre de usuario ya esta registrado");
			}
		});
		return false;
	}

	public int agregarDatoHistorial(Long id, VirusTotalUploadResponseDTO datoDTO) {

		Optional<Usuario> encontrado = usuarioRepository.findById(id);

		if (!encontrado.isPresent()) {
			throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
		}

		Usuario temp = encontrado.get();

		temp.getHistorial().forEach(virustotalresponse -> {
			if (virustotalresponse.getData().getId().equals(datoDTO.getData().getId())) {
				throw new BadRequestException("El archivo ya fue scaneado, revise su historial de analisis");
			}
		});

		VirusTotalUploadResponse dato = mapper.map(datoDTO, VirusTotalUploadResponse.class);

		temp.getHistorial().add(dato);

		usuarioRepository.save(mapper.map(temp, Usuario.class));

		return 0;
	}

	public int actulizarDatoHistorial(Long id, VirusTotalUploadResponseDTO datoDTO, String analysisId) {

		VirusTotalUploadResponse dato = mapper.map(datoDTO, VirusTotalUploadResponse.class);

		Optional<Usuario> encontrado = usuarioRepository.findById(id);

		if (!encontrado.isPresent()) {
			throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
		}

		Usuario temp = encontrado.get();

		for (VirusTotalUploadResponse response : temp.getHistorial()) {
			if (response.getData().getId().equals(analysisId)) {
				response.getData().setAttributes(dato.getData().getAttributes());
				response.getData().setId(dato.getData().getId());
				break;
			}
		}

		usuarioRepository.save(mapper.map(temp, Usuario.class));

		return 0;
	}
	
	public long getIdByUsername(String nombre) {
		return usuarioRepository.findByNombre(nombre).get().getId();
	}

}
