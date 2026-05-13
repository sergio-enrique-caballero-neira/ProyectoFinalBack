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
		checkData(data);
		
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
		
		Optional<Administrador> encontrado = administradorRepository.findById(id);
		
		if (!encontrado.isPresent()) {
			throw new ResourceNotFoundException("Administrador no encontrado con id: " + id);
		}
		
		checkData(data);
		
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
	
	public boolean checkData(AdministradorDTO data) {
	    validateNotNull(data);
	    validateNombre(data.getNombre());
	    validateContrasena(data.getContrasena());
	    validateEmail(data.getEmail());
	    validateTelefono(data.getTelefono());
	    validateCargo(data.getCargo());
	    return true;
	}

	private void validateNotNull(AdministradorDTO data) {
	    if (data == null) {
	        throw new BadRequestException("Datos de usuario no proporcionados");
	    }
	}

	private void validateNombre(String nombre) {
	    if (nombre == null || nombre.length() < 6 || nombre.length() > 50) {
	        throw new BadRequestException("El nombre debe tener mínimo 6 caracteres y máximo 50");
	    }
	    if (!nombre.matches("^[a-zA-Z0-9]{6,50}$")) {
	        throw new BadRequestException("El nombre de usuario no puede contener espacios ni caracteres especiales");
	    }
	}

	private void validateContrasena(String contrasena) {
	    if (contrasena == null || contrasena.isBlank() || contrasena.length() < 8 || contrasena.length() > 64) {
	        throw new BadRequestException("Contraseña inválida: mínimo 8 y máximo 64 caracteres");
	    }
	    if (!contrasena.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>?,./]).+$")) {
	        throw new BadRequestException("Contraseña débil: debe incluir mayúsculas, minúsculas, números y caracteres especiales");
	    }
	}

	private void validateEmail(String email) {
	    if (email == null || email.isBlank() || email.length() > 120) {
	        throw new BadRequestException("Email inválido");
	    }
	    if (!email.trim().matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$") || email.contains(" ")) {
	        throw new BadRequestException("Formato de email inválido");
	    }
	}

	private void validateTelefono(String telefono) {
	    if (telefono == null || telefono.isBlank() || !telefono.trim().matches("^\\d{10}$")) {
	        throw new BadRequestException("Teléfono inválido: solo números, de 10 dígitos");
	    }
	}
	
	private void validateCargo(String cargo) {
	    if (cargo == null || cargo.isBlank() || cargo.length() < 3 || cargo.length() > 50) {
	        throw new BadRequestException("Cargo inválido: debe tener entre 3 y 50 caracteres");
	    }
	}
	
}
