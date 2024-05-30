package services;

import edu.princeton.cs.stdlib.In;
import edu.princeton.cs.stdlib.StdOut;
import model.Carta;
import model.Tierra;

import java.util.Arrays;

/**
 * La clase gestorArchivos gestiona la carga, lectura y busqueda de cartas en los archivos de texto.
 */
public class GestorArchivos {

    private Carta[] cartas;
    private int cantidadMaximaCartas;
    private int cantidadActualCartas;

    private Tierra[] tierras;
    private int cantidadMaximaTierras;
    private int cantidadActualTierras;

    /**
     * Constructor de la clase gestor de archivos.
     * @param cantidadMaximaCartas la cantidad maxima de cartas.
     * @param cantidadMaximaTierras la cantidad maxima de cartas tipo tierras.
     */
    public GestorArchivos(int cantidadMaximaCartas, int cantidadMaximaTierras) {
        this.cantidadMaximaCartas = cantidadMaximaCartas;
        this.cartas = new Carta[cantidadMaximaCartas];
        this.cantidadActualCartas = 0;

        this.cantidadMaximaTierras = cantidadMaximaTierras;
        this.tierras = new Tierra[cantidadMaximaTierras];
        this.cantidadActualTierras = 0;
    }

    /**
     * Carga las cartas desde el archivo de texto.
     */
    public void cargarCartas() {
        try {
            lecturaDeCartas("Card_List_txt.txt", cartas);
        } catch (Exception e) {
            StdOut.println("Error al cargar las cartas: " + e.getMessage());
        }
    }

    /**
     * Carga las cartas tipo tierra desde el archivo de texto.
     */
    public void cargaTierras() {
        try {
            lecturaDeTierras("Land_List.txt", tierras);
        } catch (Exception e) {
            StdOut.println("Error al cargar las cartas de Tierra: " + e.getMessage());
        }
    }

    /**
     * Lee las cartas desde el archivo y las guarda en el arreglo de cartas.
     * @param nombreArchivo el nombre del archivo que contiene las cartas.
     * @param arregloCartas el arreglo donde seran almacenadas las cartas.
     */
    private void lecturaDeCartas(String nombreArchivo, Carta[] arregloCartas) {
        In archivoDeEntrada = new In(nombreArchivo);

        archivoDeEntrada.readLine();

        while (!archivoDeEntrada.isEmpty()) {
            String linea = archivoDeEntrada.readLine();
            String[] datos = linea.split(",");

            String nombre = datos[0];
            String texto = datos[1];
            String manaCost = datos[2];
            String tipo = datos[3];
            String poder = datos[4];
            String resistencia = datos[5];
            String cmc = datos[6];

            //crea la nueva carta y la añade al arreglo
            Carta carta = new Carta(nombre, texto, manaCost, tipo, poder, resistencia, cmc){};
            arregloCartas[cantidadActualCartas++] = carta;

            //para mostrar los datos de la carta de forma elegante
            StdOut.println(nombre + " || " + texto + " || " + manaCost + " || " + tipo + " || " + poder + " || " + resistencia + " || " + cmc);

        }
        archivoDeEntrada.close();
    }


    /**
     * Lee las tierras desde el archivo y las guarda en el arreglo de tierras.
     * @param nombreArchivo el nombre del archivo que contiene las cartas tierras
     * @param arregloTierras el arreglo donde se almacenaran las cartas tierras.
     */
    private void lecturaDeTierras(String nombreArchivo, Tierra[] arregloTierras) {
        In archivoDeEntrada = new In(nombreArchivo);

        archivoDeEntrada.readLine();

        while (!archivoDeEntrada.isEmpty()) {
            String linea = archivoDeEntrada.readLine();
            String[] datos = linea.split(",");

            String nombre = datos[0];
            String tipo = datos[1];
            String color = datos[2];

            //crear la nueva carta de tierra y la añade al arreglo
            Tierra tierra = new Tierra(nombre, tipo, color);
            arregloTierras[cantidadActualTierras++] = tierra;

            //mostrar los datos de la tierra
            StdOut.println(nombre + ", " + tipo + ", " + color);
        }
        archivoDeEntrada.close();
    }

    /**
     * Busca una carta por su nombre en el sistema.
     * @param nombreCarta el nombre de la carta a buscar
     * @return la carta encontrada.
     */
    public Carta buscarCarta(String nombreCarta) {
        if (nombreCarta == null || nombreCarta.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la carta no puede ser nulo o vacío.");
        }

        for (int i = 0; i < cantidadActualCartas; i++) {
            if (cartas[i].getNombre().equalsIgnoreCase(nombreCarta)) {
                return cartas[i];
            }
        }

        return null;
    }

    /**
     * Busca una carta por su nombre y obtiene su informacion.
     * @param nombreCarta el nombre de la carta a buscar.
     * @return la información de la carta encontrada.
     */
    public String buscarYobtenerInformacionCarta(String nombreCarta) {
        Carta carta = buscarCarta(nombreCarta);

        if (carta != null) {
            String informacion = "Nombre: " + carta.getNombre() + "\n" +
                    "Texto: " + carta.getTexto() + "\n" +
                     "Costo de Mana: " + carta.getManaCost() + "\n" +
                        "Tipo: " + carta.getTipo() + "\n";

            //verificar si es una carta normal para obtener poder, resistencia y cmc
            if (carta instanceof Carta) {
                Carta cartaNormal = (Carta) carta;
                informacion += "Poder: " + cartaNormal.getPoder() + "\n" +
                         "Resistencia: " + cartaNormal.getResistencia() + "\n" +
                                "CMC: " + cartaNormal.getCmc() + "\n";
            }

            return informacion;
        } else {
            return "La carta \"" + nombreCarta + "\" no se encontro en el sistema.";
        }
    }

}
