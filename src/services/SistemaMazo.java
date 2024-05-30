package services;

import edu.princeton.cs.stdlib.StdIn;
import edu.princeton.cs.stdlib.StdOut;
import model.Carta;
import model.Mazo;

/**
 * La clase SistemaMazo implementa la interfaz ISistemaMazo y se encarga de gestionar los mazos y sideboards.
 */
public class SistemaMazo implements ISistemaMazo {

    private GestorArchivos gestorArchivos;
    private GestorUsuarios gestorUsuarios;
    private Mazo mazo;

    /**
     * Añade una carta al mazo utilizando el gestor de archivos para buscar la carta y la cantidad que se quiere añadir.
     * @param mazo el mazo al que sera añadida la carta.
     * @param gestorArchivos el gestor de archivos utilizado para buscar la carta que se quiere añadir.
     */
    @Override
    public void añadirCarta(Mazo mazo, GestorArchivos gestorArchivos) {

        StdOut.println("Ingrese el nombre de la carta que desea añadir:");
        String nombreCarta = StdIn.readString();

        //buscar la carta en el sistema usando el gestor
        Carta carta = gestorArchivos.buscarCarta(nombreCarta);
        if (carta == null) {
            StdOut.println("La carta no se encontro en el sistema.");
            return;
        }


        StdOut.println("Ingrese la cantidad de copias que desea añadir al mazo:");
        int cantidadCopias = StdIn.readInt();

        //verificar si la cantidad de copias es valida
        if (cantidadCopias < 1) {
            StdOut.println("La cantidad de copias debe ser al menos 1.");
            return;
        }

        //se agrega la carta al mazo con su cantidad
        mazo.agregarCarta(carta, cantidadCopias);

        StdOut.println("Carta añadida con exito al mazo.");
    }

    /**
     * Agrega una carta al sideboard.
     * @param carta la carta a agregar al sideboard.
     * @param cantidadCopias la cantidad de copias de la carta a agregar al sideboard.
     */
    @Override
    public void agregarSideboard(Carta carta, int cantidadCopias) {
        //agregar la carta al sideboard con su cantidad
        mazo.agregarSideboard(carta, cantidadCopias);
        StdOut.println("Carta añadida al sideboard.");
    }

    /**
     * Elimina una carta del sideboard indicando su cantidad y nombre.
     * @param nombreCarta el nombre de la carta a eliminar.
     * @param cantidadCopias la cantidad de copias a eliminar.
     */
    @Override
    public void eliminarSideboard(String nombreCarta, int cantidadCopias) {
        //para validar si es posible o no eliminar la carta
        try {
            mazo.eliminarSideboard(nombreCarta, cantidadCopias);
            StdOut.println("Carta eliminada con exito del sideboard.");
        } catch (IllegalArgumentException e) {
            StdOut.println("No se pudo eliminar.");
        }
    }

    @Override
    public boolean construirMazo() {
        return false;
    }

    @Override
    public String verMazos() {
        return null;
    }

    @Override
    public String obtenerCarta(String nombreCarta) {
        return null;
    }

    @Override
    public boolean cerrarSesion() {
        return false;
    }

    @Override
    public Carta buscarSideboard(String nombreCarta) {
        return mazo.buscarSideboard(nombreCarta);
    }


}
