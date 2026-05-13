package unbosque.edu.co.proyectoFinalSem3.service;

import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre cualquier tipo de entidad.
 * Esta interfaz define los métodos básicos que deben implementar los servicios para gestionar entidades en la aplicación.
 * @param <T> Tipo de dato sobre el que se realizan las operaciones CRUD.
 */
public interface CRUDoperation<T> {

    /**
     * Crea una nueva entidad en el sistema.
     * @param data Objeto con los datos de la entidad a crear.
     * @return Código de resultado de la operación (0 si es exitoso, otro valor si hay error).
     */
    public int create(T data);

    /**
     * Obtiene todas las entidades registradas.
     * @return Lista de entidades existentes.
     */
    public List<T> getAll();

    /**
     * Elimina una entidad por su identificador único.
     * @param id Identificador de la entidad a eliminar.
     * @return Código de resultado de la operación (0 si es exitoso, otro valor si hay error).
     */
    public int deleteByID(Long id);

    /**
     * Actualiza los datos de una entidad existente por su identificador.
     * @param id Identificador de la entidad a actualizar.
     * @param data Objeto con los nuevos datos de la entidad.
     * @return Código de resultado de la operación (0 si es exitoso, otro valor si hay error).
     */
    public int updateByID(Long id, T data);

    /**
     * Cuenta el número total de entidades registradas.
     * @return Número total de entidades.
     */
    public long count();

    /**
     * Verifica si existe una entidad con el identificador dado.
     * @param id Identificador de la entidad.
     * @return true si existe, false en caso contrario.
     */
    public boolean exist(Long id);
}