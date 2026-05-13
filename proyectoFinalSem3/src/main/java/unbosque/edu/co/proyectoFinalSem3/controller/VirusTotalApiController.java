package unbosque.edu.co.proyectoFinalSem3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import unbosque.edu.co.proyectoFinalSem3.dto.VirusTotalUploadResponseDTO;
import unbosque.edu.co.proyectoFinalSem3.service.UsuarioService;
import unbosque.edu.co.proyectoFinalSem3.service.VirustotalService;

@RestController
@RequestMapping("/virustotal")
@CrossOrigin(origins = { "http://localhost:8080", "*" })
@Transactional
@SecurityRequirement(name = "bearerAuth")
public class VirusTotalApiController {

	@Autowired
	private VirustotalService virustotalService;

	@Autowired
	private UsuarioService usuarioService;

	public VirusTotalApiController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping(value = "/subir", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> uploadFile(@RequestParam Long id, @RequestParam("file") MultipartFile file) throws Exception {
		VirusTotalUploadResponseDTO response = virustotalService.subirArchivo(file);
		usuarioService.agregarDatoHistorial(id, response);
		return new ResponseEntity<String>(response.getData().getId(), HttpStatus.CREATED);
	}

	@GetMapping("/analisis")
	public ResponseEntity<VirusTotalUploadResponseDTO> getAnalisis(@RequestParam long id, @RequestParam String analysisId) throws Exception {
		VirusTotalUploadResponseDTO response = virustotalService.getAnalysis(analysisId);
		usuarioService.actulizarDatoHistorial(id, response, analysisId);
		return new ResponseEntity<VirusTotalUploadResponseDTO>(response, HttpStatus.OK);
	}

}
