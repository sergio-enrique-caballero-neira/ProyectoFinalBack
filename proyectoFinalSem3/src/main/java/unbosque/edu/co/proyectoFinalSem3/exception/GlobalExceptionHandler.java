package unbosque.edu.co.proyectoFinalSem3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(QueueException.class)
    public ResponseEntity<String> handleQueue(QueueException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(FileEmpyException.class)
    public ResponseEntity<String> handleEmpyFile(FileEmpyException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(AlreadySubmittedException.class)
    public ResponseEntity<String> handleAlreadySubmitted(AlreadySubmittedException ex) {
        return new ResponseEntity<String>("El archivo ya fue scaneado.", HttpStatus.ALREADY_REPORTED);
    }
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxUpload(MaxUploadSizeExceededException ex) {
        return new ResponseEntity<String>("El archivo no puede pasar los 32mb", HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleClosed(IllegalStateException ex) {
        return new ResponseEntity<String>("Ah subido demasiados archivos muy seguido espere unos minutos", HttpStatus.TOO_MANY_REQUESTS);
    }
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja cualquier otra excepción no controlada.
     * @param ex la excepción lanzada
     * @return respuesta con estado 500 y mensaje genérico de error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception ex) {
        return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
