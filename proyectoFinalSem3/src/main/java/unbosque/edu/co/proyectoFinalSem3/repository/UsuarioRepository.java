package unbosque.edu.co.proyectoFinalSem3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import unbosque.edu.co.proyectoFinalSem3.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	
	public Optional<Usuario> findByNombre(String nombre);

}
