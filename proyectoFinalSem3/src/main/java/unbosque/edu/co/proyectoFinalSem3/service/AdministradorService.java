package unbosque.edu.co.proyectoFinalSem3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import unbosque.edu.co.proyectoFinalSem3.dto.AdministradorDTO;
import unbosque.edu.co.proyectoFinalSem3.entity.Administrador;
import unbosque.edu.co.proyectoFinalSem3.exception.BadRequestException;
import unbosque.edu.co.proyectoFinalSem3.exception.ResourceNotFoundException;
import unbosque.edu.co.proyectoFinalSem3.repository.AdministradorRepository;

@Service
public class AdministradorService implements CRUDoperation<AdministradorDTO> {
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public AdministradorService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int create(AdministradorDTO data) {
		if (data == null) {
			throw new BadRequestException("Datos de administrador no proporcionados");
		}

		if (data.getNombre() == null || data.getNombre().length() < 6 || data.getNombre().length() > 50) {
			throw new BadRequestException("El nombre debe tener minimo 6 caracteres y maximo 50");
		}

		if (data.getNombre().contains(" ") || !data.getNombre().matches("^[a-zA-Z0-9]+$")) {
			throw new BadRequestException("El nombre de administrador no puede contener espacion ni caracteres especiales");
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
		
		if (data.getCargo() == null || data.getCargo().trim().isEmpty() || data.getCargo().length() < 3 || data.getCargo().length() > 50) {
			throw new BadRequestException("Cargo inválido: debe tener entre 3 y 50 caracteres");
		}
		
		existByNombre(data.getNombre());
		existByEmail(data.getEmail());
		existByTelefono(data.getTelefono());
		
	    Administrador entidad = mapper.map(data, Administrador.class);
	    entidad.setContrasena(passwordEncoder.encode(entidad.getContrasena()));
	    administradorRepository.save(entidad);
		return 0;
	}

	@Override
	public List<AdministradorDTO> getAll() {
		List<Administrador> entityList = (List<Administrador>) administradorRepository.findAll();
		List<AdministradorDTO> dtoList = new ArrayList<>();
		
		entityList.forEach((entity) -> {
			dtoList.add(mapper.map(entity, AdministradorDTO.class));
		});
		
		if (dtoList.isEmpty()) {
			throw new BadRequestException("No se encontraron administradores");
		}
		
		return dtoList;
	}

	@Override
	public int deleteByID(Long id) {
		if (id == null || id < 0) {
			throw new BadRequestException("ID inválido");
		}
		
		Optional<Administrador> encontrado = administradorRepository.findById(id);
		
		if (!encontrado.isPresent()) {
			throw new ResourceNotFoundException("Administrador no encontrado con id: " + id);
		}
		
		administradorRepository.deleteById(id);
		return 0;
	}

	@Override
	public int updateByID(Long id, AdministradorDTO data) {
		if (id == null || id <= 0) {
			throw new BadRequestException("ID inválido");
		}

		if (data == null) {
			throw new BadRequestException("Datos de administrador no proporcionados");
		}
		
		Optional<Administrador> encontrado = administradorRepository.findById(id);
		
		if (!encontrado.isPresent()) {
			throw new ResourceNotFoundException("Administrador no encontrado con id: " + id);
		}
		
		if (data.getNombre() == null || data.getNombre().length() < 6 || data.getNombre().length() > 50) {
			throw new BadRequestException("El nombre debe tener minimo 6 caracteres y maximo 50");
		}

		if (data.getNombre().contains(" ") || !data.getNombre().matches("^[a-zA-Z0-9]+$")) {
			throw new BadRequestException("El nombre de administrador no puede contener espacion ni caracteres especiales");
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
		
		if (data.getCargo() == null || data.getCargo().trim().isEmpty() || data.getCargo().length() < 3 || data.getCargo().length() > 50) {
			throw new BadRequestException("Cargo inválido: debe tener entre 3 y 50 caracteres");
		}
		
		AdministradorDTO temp = mapper.map(encontrado.get(), AdministradorDTO.class);
		temp.setNombre(data.getNombre());
		temp.setContrasena(passwordEncoder.encode(data.getContrasena()));
		temp.setEmail(data.getEmail());
		temp.setTelefono(data.getTelefono());
		temp.setCargo(data.getCargo());
		
		administradorRepository.save(mapper.map(temp, Administrador.class));
		return 0;
	}

	@Override
	public long count() {
		return administradorRepository.count();
	}

	@Override
	public boolean exist(Long id) {
		return administradorRepository.existsById(id);
	}

	public boolean existByEmail(String email) {
		administradorRepository.findAll().forEach(administrador -> {
			if (administrador.getEmail().equals(email)) {
				throw new BadRequestException("El email ya esta registrado");
			}
		});
		return false;
	}

	public boolean existByTelefono(String telefono) {
		administradorRepository.findAll().forEach(administrador -> {
			if (administrador.getTelefono().equals(telefono)) {
				throw new BadRequestException("El telefono ya esta registrado");
			}
		});
		return false;
	}

	public boolean existByNombre(String nombre) {
		administradorRepository.findAll().forEach(administrador -> {
			if (administrador.getNombre().equals(nombre)) {
				throw new BadRequestException("El nombre de administrador ya esta registrado");
			}
		});
		return false;
	}

}
