package model;


/**
 * La clase Mazo representa el mazo de cartas en el juego, se pueden gestionar las cartas en el mazo principal y en el sideboard.
 */
public class Mazo {

    private Carta[] cartas;
    private int[] cantidades;
    private Carta[] sideboard;
    private int cantidadActualCartas;
    private int cantidadMaximaCartas;
    private int cantidadActualSideboard;
    private int cantidadMaximaSideboard;

    /**
     * Constructor de la clase Mazo.
     * @param cantidadMaxima la cantidad maxima de cartas que contiene el mazo
     */
    public Mazo(int cantidadMaxima) {
        this.cantidadMaximaCartas = cantidadMaxima;
        this.cartas = new Carta[cantidadMaxima];
        this.cantidades = new int[cantidadMaxima];
        this.sideboard = new Carta[15]; //15 porque es el tamaño maximo del sidebaord
        this.cantidadActualCartas = 0;
        this.cantidadActualSideboard = 0;
    }

    /**
     * Agrega una carta al mazo principal.
     * @param carta la carta a agregar
     * @param cantidadCopias la cantidad de copias de la carta a agregar
     */
    public void agregarCarta(Carta carta, int cantidadCopias) {
        //buscar si la carta ya existe en el mazo
        for (int i = 0; i < cantidadActualCartas; i++) {
            if (cartas[i].getNombre().equalsIgnoreCase(carta.getNombre())) {
                //aumenta la cantidad de copias si ya existe
                cantidades[i] += cantidadCopias;
                return;
            }
        }
        //para verificar si hay espacio disponible
        if (cantidadActualCartas < cantidadMaximaCartas) {
            cartas[cantidadActualCartas] = carta;
            cantidades[cantidadActualCartas] = cantidadCopias;
            cantidadActualCartas++;
        }
    }

    /**
     * Busca una carta en el mazo por su nombre.
     * @param nombreCarta el nombre de la carta a buscar.
     * @return la carta encontrada o nulo si no se encuentra.
     */
    public Carta buscarCarta(String nombreCarta) {
        //para verificar que el nombre de la carta sea valido
        if (nombreCarta == null || nombreCarta.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la carta no puede ser nulo o vacío.");
        }

        //para buscar la carta por su nombre
        for (int i = 0; i < cantidadActualCartas; i++) {
            if (cartas[i].getNombre().equalsIgnoreCase(nombreCarta)) {
                return cartas[i]; //devuelve la carta si se encuentra
            }
        }
        return null;
    }

    /**
     * Elimina una carta del mazo principal, indicando el nombre y la cantidad a eliminar.
     * @param nombreCarta el nombre de la carta a eliminar.
     * @param cantidadCopias la cantidad de copias a eliminar.
     */
    public void eliminarCarta(String nombreCarta, int cantidadCopias) {
        for (int i = 0; i < cantidadActualCartas; i++) {
            if (cartas[i].getNombre().equalsIgnoreCase(nombreCarta)) {
                //si es que la cantidad que deseamos eliminar es menor a la cantidad de copias
                if (cantidades[i] < cantidadCopias) {
                    throw new IllegalArgumentException("No hay suficientes copias de la carta para eliminar.");
                }
                //si es que la cantidad de copias a eliminar llega a 0 la carta se elimina del mazo
                cantidades[i] -= cantidadCopias;
                if (cantidades[i] == 0) {
                    for (int j = i; j < cantidadActualCartas - 1; j++) {
                        cartas[j] = cartas[j + 1];
                        cantidades[j] = cantidades[j + 1];
                    }
                    cartas[cantidadActualCartas - 1] = null;
                    cantidades[cantidadActualCartas - 1] = 0;
                    cantidadActualCartas--;
                }
                return;
            }
        }
        throw new IllegalArgumentException("La carta no se encontró en el mazo.");
    }

    /**
     * Agrega una carta al sideboard.
     * @param carta la carta a agregar.
     * @param cantidadCopias la cantidad de copias de la carta a agregar.
     */
    public void agregarSideboard(Carta carta, int cantidadCopias) {

        for (int i = 0; i < cantidadActualSideboard; i++) {
            if (sideboard[i].getNombre().equalsIgnoreCase(carta.getNombre())) { //busca si la carta existe para incrementar las copias
                sideboard[i].setCantidad(sideboard[i].getCantidad() + cantidadCopias);
                return;
            }
        }

        if (cantidadActualSideboard < sideboard.length) {
            carta.setCantidad(cantidadCopias);
            sideboard[cantidadActualSideboard++] = carta; // si es que no existe, la agrega al sideboard
        }
    }

    /**
     * Busca una carta en el sideboard por su nombre.
      * @param nombreCarta el nombre de la carta a buscar.
     * @return la carta encontrada o nulo si no se encuentra.
     */
    public Carta buscarSideboard(String nombreCarta) {
        for (int i = 0; i < cantidadActualSideboard; i++) {
            if (sideboard[i].getNombre().equalsIgnoreCase(nombreCarta)) { //buscar la carta por su nombre en el sideboard
                return sideboard[i];
            }
        }
        return null;
    }

    /**
     * Elimina una cantidad especifica de copias de una carta del sideboard.
     * @param nombreCarta el nombre de la carta a eliminar.
     * @param cantidadCopias la cantidad de copias a eliminar.
     */
    public void eliminarSideboard(String nombreCarta, int cantidadCopias) {
        for (int i = 0; i < cantidadActualSideboard; i++) {
            if (sideboard[i].getNombre().equalsIgnoreCase(nombreCarta)) {  //buscar la carta por su nombre en el sideboard
                if (sideboard[i].getCantidad() <= cantidadCopias) {  // si es mayor o igual la cantidad de copias, elimina la carta
                    for (int j = i; j < cantidadActualSideboard - 1; j++) {
                        sideboard[j] = sideboard[j + 1];
                    }
                    sideboard[cantidadActualSideboard--] = null;
                }
                //disminuye la cantidad solamente en el caso contrario al de arriba
                    sideboard[i].setCantidad(sideboard[i].getCantidad() - cantidadCopias);
                return;
            }
        }
        throw new IllegalArgumentException("La carta no se encontró en el sideboard.");
    }

    /**
     * Obtiene el arreglo de cartas en el mazo.
     * @return el arreglo de cartas en el mazo.
     */
    public Carta[] getCartas() {
        return cartas;
    }

}