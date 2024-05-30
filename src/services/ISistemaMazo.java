package services;

import model.Carta;
import model.Mazo;

/**
 * La interfaz ISistemaMazo se encarga de definir los metodos del sistema de mazos.
 */
public interface ISistemaMazo {

    /**
     * Construye un mazo en el sistema.
     * @return true si el mazo se construye correctamente.
     */
    boolean construirMazo();

    /**
     * Muestra todos los mazos en el sistema.
     * @return los mazos que estan en el sistema.
     */
    String verMazos();

    /**
     * Obtiene los detalles de una carta en especifico indicado por su nombre.
     * @param nombreCarta el nombre de la carta a obtener.
     * @return los detalles de la carta.
     */
    String obtenerCarta(String nombreCarta);

    /**
     * Cierra la sesion actual en el sistema.
     * @return true si la sesion se cierra correctamente.
     */
    boolean cerrarSesion();

    /**
     * Añade una carta a un mazo.
     * @param mazo el mazo al que sera añadida la carta.
     * @param gestorArchivos el gestor que se encarga de añadir la carta al mazo.
     */
    void añadirCarta(Mazo mazo, GestorArchivos gestorArchivos);

    /**
     * Agrega una carta al sideboard.
     * @param carta la carta que se va a agregar al sideboard.
     * @param cantidadCopias la cantidad de copias que se agregaran.
     */
    void agregarSideboard(Carta carta, int cantidadCopias);

    /**
     * Elimina una carta del sideboard indicando la cantidad a eliminar.
     * @param nombreCarta el nombre de la carta a eliminar.
     * @param cantidadCopias la cantidad de copias a eliminar.
     */
    void eliminarSideboard(String nombreCarta, int cantidadCopias);

    /**
     * Busca una carta en el sideboard por su nombre.
     * @param nombreCarta el nombre de la carta a buscar.
     * @return la carta encontrada en el sideboard.
     */
    Carta buscarSideboard(String nombreCarta);
}
