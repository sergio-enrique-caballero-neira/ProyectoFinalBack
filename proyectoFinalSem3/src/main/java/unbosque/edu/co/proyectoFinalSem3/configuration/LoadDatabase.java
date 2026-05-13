package unbosque.edu.co.proyectoFinalSem3.configuration;

import unbosque.edu.co.proyectoFinalSem3.entity.Administrador;
import unbosque.edu.co.proyectoFinalSem3.entity.Usuario;
import unbosque.edu.co.proyectoFinalSem3.repository.AdministradorRepository;
import unbosque.edu.co.proyectoFinalSem3.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(AdministradorRepository adminRepo, UsuarioRepository usuarioRepo,
			PasswordEncoder passwordEncoder) {
		return args -> {

			boolean adminExiste = false;
			for (Administrador a : adminRepo.findAll()) {
				if (a.getNombre().equals("admin")) {
					adminExiste = true;
					break;
				}
			}

			if (adminExiste) {
				log.info("El administrador ya existe, omitiendo creación...");
			} else {
				Administrador adminUser = new Administrador("admin", "admin@proyecto.com", "3001234567",
						passwordEncoder.encode("Admin123!"), "Administrador General");
				adminRepo.save(adminUser);
				log.info("Administrador creado → nombre: admin | contrasena: Admin123!");
			}

			boolean usuarioExiste = false;
			for (Usuario u : usuarioRepo.findAll()) {
				if (u.getNombre().equals("usuario1")) {
					usuarioExiste = true;
					break;
				}
			}

			if (usuarioExiste) {
				log.info("El usuario normal ya existe, omitiendo creación...");
			} else {
				Usuario normalUser = new Usuario("usuario1", "usuario1@proyecto.com", "3009876543",
						passwordEncoder.encode("Usuario1*"));
				usuarioRepo.save(normalUser);
				log.info("Usuario creado → nombre: usuario1 | contrasena: Usuario1*");
			}
		};
	}
}
