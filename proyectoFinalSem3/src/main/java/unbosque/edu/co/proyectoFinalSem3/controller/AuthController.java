package unbosque.edu.co.proyectoFinalSem3.controller;

import unbosque.edu.co.proyectoFinalSem3.entity.Persona;
import unbosque.edu.co.proyectoFinalSem3.security.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = { "http://localhost:8080", "*" })
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Login unificado para Usuario y Administrador.
     *
     * Enviar: nombre + contrasena (como @RequestParam).
     * Responde con: { "token": "...", "role": "USUARIO" | "ADMIN" }
     *
     * IMPORTANTE: las contraseñas en la BD deben estar encriptadas con BCrypt.
     * LoadDatabase ya las guarda encriptadas al iniciar la app.
     * Al crear usuarios desde /usuario/crear o /administrador/crear, asegúrate
     * de encriptar la contraseña con PasswordEncoder antes de guardar en BD.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String nombre,
            @RequestParam String contrasena) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(nombre, contrasena));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtil.generateToken(userDetails);

            String role = null;
            if (userDetails instanceof Persona) {
                role = ((Persona) userDetails).getRole().name();
            }

            return ResponseEntity.ok(new AuthResponse(jwt, role));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Nombre o contraseña inválidos");
        }
    }

    private static class AuthResponse {
        private final String token;
        private final String role;

        public AuthResponse(String token, String role) {
            this.token = token;
            this.role = role;
        }

        public String getToken() { return token; }
        public String getRole()  { return role; }
    }
}
