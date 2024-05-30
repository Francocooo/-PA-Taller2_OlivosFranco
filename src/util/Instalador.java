package util;

import services.ISistemaMazo;
import services.SistemaMazo;

/**
 * La clase que se encarga de la instalación y configuración del sistema de mazos.
 */
public class Instalador {

    private ISistemaMazo sistemaHaInstalar;

    /**
     * Constructor del instalador.
     */
    public Instalador(){
        this.sistemaHaInstalar = new SistemaMazo();
    }

    /**
     * Instala el sistema de mazos.
     * @return la instancia de ISistemaMazo que sera instalada.
     */
    public ISistemaMazo instalarSistema(){
        return  this.sistemaHaInstalar;
    }


}
