package unbosque.edu.co.proyectoFinalSem3.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {

		String mainDescription = "<h2>API REST - Proyecto Final Sem3</h2>"
				+ "<p>API para gestión de usuarios, administradores y análisis de archivos "
				+ "con VirusTotal. Protegida con autenticación JWT.</p>" + "<h3>Conceptos básicos:</h3><ul>"
				+ "    <li><strong>JWT</strong>: Al hacer login recibirás un token que debes "
				+ "        incluir en las cabeceras de tus peticiones.</li>"
				+ "    <li><strong>Autenticación</strong>: Verifica tu identidad con nombre y contraseña.</li>"
				+ "    <li><strong>Autorización</strong>: Determina qué puede hacer cada rol.</li>" + "</ul>"
				+ "<h3>Flujo básico:</h3><ol>"
				+ "    <li>Un ADMINISTRADOR crea usuarios con <code>POST /usuario/crear</code></li>"
				+ "    <li>Inicia sesión en <code>POST /auth/login</code> con tu nombre y contraseña</li>"
				+ "    <li>Copia el token de la respuesta</li>"
				+ "    <li>Inclúyelo en el encabezado: <code>Authorization: Bearer tu_token</code></li>"
				+ "    <li>Usa los endpoints según tu rol</li>" + "</ol>"
				+ "<h3>Roles:</h3><ul>"
				+ "    <li><strong>USUARIO</strong>: Ver lista de usuarios, usar VirusTotal</li>"
				+ "    <li><strong>ADMINISTRADOR</strong>: Todo lo anterior + crear, actualizar "
				+ "        y eliminar usuarios y administradores</li>" + "</ul>"
				+ "<h3>Credenciales de prueba (precargadas al iniciar la app):</h3><ul>"
				+ "    <li>Admin → nombre: <code>admin</code> / contrasena: <code>Admin123!</code></li>"
				+ "    <li>Usuario → nombre: <code>usuario1</code> / contrasena: <code>Usuario1!</code></li>" + "</ul>"
				+ "<h3>Códigos HTTP comunes:</h3><ul>" + "    <li><strong>200/201</strong>: Éxito</li>"
				+ "    <li><strong>400</strong>: Datos incorrectos</li>"
				+ "    <li><strong>401</strong>: Token inválido o expirado</li>"
				+ "    <li><strong>403</strong>: Sin permisos suficientes</li>"
				+ "    <li><strong>404</strong>: Recurso no encontrado</li>"
				+ "    <li><strong>409</strong>: Conflicto (nombre, email o teléfono ya registrado)</li>" + "</ul>";

		String securityDescription = "Autenticación mediante JWT (JSON Web Token)." + "<p>Para autenticarte:</p><ol>"
				+ "    <li>Llama a <code>POST /auth/login</code> con tu nombre y contraseña</li>"
				+ "    <li>Copia el token de la respuesta</li>"
				+ "    <li>Haz clic en \"Authorize\" arriba en esta página</li>"
				+ "    <li>Escribe: <code>Bearer tu_token_jwt</code></li>"
				+ "    <li>Haz clic en \"Authorize\" y luego en \"Close\"</li>" + "</ol>"
				+ "<p>Ahora podrás acceder a los endpoints protegidos.</p>";

	    // Crear objeto Info con la descripción HTML
	    io.swagger.v3.oas.models.info.Info info =
	        new io.swagger.v3.oas.models.info.Info()
	            .title("API de Primera Aplicación Spring")
	            .version("1.0")
	            .description(mainDescription)
	            .contact(
	                new io.swagger.v3.oas.models.info.Contact()
	                    .name("Equipo de Desarrollo")
	                    .email("soporte@ejemplo.com")
	                    .url("https://github.com/tu-usuario/SpringFirstAppJWT"))
	            .license(
	                new io.swagger.v3.oas.models.info.License()
	                    .name("Licencia MIT")
	                    .url("https://opensource.org/licenses/MIT"));

		// Crear el esquema de seguridad
	    io.swagger.v3.oas.models.security.SecurityScheme securityScheme =
	        new io.swagger.v3.oas.models.security.SecurityScheme()
	            .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
	            .scheme("bearer")
	            .bearerFormat("JWT")
	            .description(securityDescription);

	    return new OpenAPI()
	            .info(info)
	            .components(
	                new Components()
	                    .addSecuritySchemes("bearerAuth", securityScheme)
	                    .addResponses(
	                        "UnauthorizedError",
	                        new ApiResponse()
	                            .description("No autenticado - Token JWT inválido o expirado")
	                            .content(
	                                new Content()
	                                    .addMediaType(
	                                        "application/json",
	                                        new MediaType()
	                                            .addExamples(
	                                                "error",
	                                                new Example()
	                                                    .value(
	                                                        "{\"error\": \"No autorizado\", \"mensaje\":"
	                                                            + " \"Token inválido o expirado\"}")))))
	                    .addResponses(
	                        "ForbiddenError",
	                        new ApiResponse()
	                            .description("Acceso prohibido - No tienes permisos suficientes")
	                            .content(
	                                new Content()
	                                    .addMediaType(
	                                        "application/json",
	                                        new MediaType()
	                                            .addExamples(
	                                                "error",
	                                                new Example()
	                                                    .value(
	                                                        "{\"error\": \"Acceso prohibido\", \"mensaje\":"
	                                                            + " \"No tienes permisos para esta"
	                                                            + " operación\"}")))))
	                    .addResponses(
	                        "NotFoundError",
	                        new ApiResponse()
	                            .description("Recurso no encontrado")
	                            .content(
	                                new Content()
	                                    .addMediaType(
	                                        "application/json",
	                                        new MediaType()
	                                            .addExamples(
	                                                "error",
	                                                new Example()
	                                                    .value(
	                                                        "{\"error\": \"No encontrado\", \"mensaje\":"
	                                                            + " \"El recurso solicitado no"
	                                                            + " existe\"}"))))));
	}
}