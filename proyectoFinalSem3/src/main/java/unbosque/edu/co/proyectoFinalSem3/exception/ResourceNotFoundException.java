package unbosque.edu.co.proyectoFinalSem3.exception;

/**
 * Excepción lanzada cuando un recurso solicitado no se encuentra en el sistema.
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Crea una nueva excepción de recurso no encontrado con el mensaje especificado.
     * @param mensaje el mensaje de error
     */
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}