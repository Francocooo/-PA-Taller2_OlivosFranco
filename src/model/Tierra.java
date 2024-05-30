package model;

/**
 * La clase Tierra representa una carta de tipo tierra que extiende de la clase abstracta Carta.
 */
public class Tierra extends Carta {

    private String color;

    /**
     * Constructor de la clase Tierra con los atributos heredado de la clase Carta.
     * @param nombre el nombre de la carta.
     * @param tipo el tipo de la carta.
     * @param color el color de la carta.
     */
    public Tierra(String nombre, String tipo, String color) {
        super(nombre, tipo, color, "", "", "", "");
        this.color = color;
    }

    /**
     * Obtiene el color de la carta tierra.
      * @return el color de la carta tierra.
     */
    public String getColor() {
        return color;
    }

    /**
     * Define el color de la carta tierra.
      * @param color el color que modificara de la carta tierra
     */
    public void setColor(String color) {
        this.color = color;
    }
}
