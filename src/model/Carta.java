package model;

/**
 * La clase Carta es abstracta y representa una carta normal con atributos que hereda para otros tipos de cartas.
 * Cada carta tiene un nombre, costo de mana, tipo, texto descriptivo, poder, resistencia, CMC (costo de mana convertido) y la cantidad.
 */
public abstract class Carta {

    private String nombre;
    private String manaCost;
    private String tipo;
    private String texto;
    private String poder;
    private String resistencia;
    private String cmc;
    private int cantidad;

    /**
     * Constructor de la clase Carta.
     * @param nombre el nombre de la carta
     * @param texto el texto descriptivo de la carta
     * @param manaCost el costo de maná de la carta
     * @param tipo el tipo de la carta
     * @param poder el poder de la carta
     * @param resistencia la resistencia de la carta
     * @param cmc el costo de mana convertido de la carta
     */
    public Carta(String nombre, String texto, String manaCost, String tipo, String poder, String resistencia, String cmc) {
        this.nombre = nombre;
        this.manaCost = manaCost;
        this.tipo = tipo;
        this.texto = texto;
        this.poder = poder;
        this.resistencia = resistencia;
        this.cmc = cmc;
    }

    /**
     * Obtiene el nombre de la carta.
     * @return el nombre de la carta
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el costo de mana de la carta.
     * @return el costo de mana de la carta
     */
    public String getManaCost() {
        return manaCost;
    }

    /**
     * Obtiene el tipo de la carta.
     * @return el tipo de la carta
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene la descripcion de la carta.
     * @return la descripcion de la carta
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Obtiene el poder de la carta.
     * @return el poder de la carta
     */
    public String getPoder() {
        return poder;
    }

    /**
     * Obtiene la resistencia de la carta.
     * @return la resistencia de la carta
     */
    public String getResistencia() {
        return resistencia;
    }

    /**
     * Obtiene el costo de mana convertido de la carta.
     * @return el costo de mana convertido de la carta
     */
    public String getCmc() {
        return cmc;
    }

    /**
     * Obtiene la cantidad de cartas.
     * @return la cantidad de cartas
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Define la cantidad de cartas.
     * @param cantidad la cantidad modificadad de cartas
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
