package unbosque.edu.co.proyectoFinalSem3.exception;

/**
 * Excepción lanzada cuando la solicitud enviada por el cliente es inválida o contiene errores de formato.
 */
public class BadRequestException extends RuntimeException {
    /**
     * Crea una nueva excepción de solicitud incorrecta con el mensaje especificado.
     * @param mensaje el mensaje de error
     */
    public BadRequestException(String mensaje) {
        super(mensaje);
    }
}