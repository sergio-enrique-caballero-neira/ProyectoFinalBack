package unbosque.edu.co.proyectoFinalSem3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import unbosque.edu.co.proyectoFinalSem3.entity.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador,Long>{
	
	public Optional<Administrador> findByNombre(String nombre);
	
}
