import edu.princeton.cs.stdlib.StdIn;
import edu.princeton.cs.stdlib.StdOut;
import model.Carta;
import model.Mazo;
import model.Usuario;
import services.GestorArchivos;
import services.GestorUsuarios;
import services.ISistemaMazo;
import util.Instalador;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        configuracion(new Instalador());

    }

    /**
     * Configura el sistema instalandolo y cargando los datos.
     *
     * @param instalador el instalador utilizado para configurar el sistema.
     */
    public static void configuracion(Instalador instalador){
        //
        ISistemaMazo sistema = instalador.instalarSistema();
        GestorArchivos gestorArchivos = new GestorArchivos(10000, 10000);
        GestorUsuarios gestorUsuarios = new GestorUsuarios(10000, 10000);
        gestorArchivos.cargarCartas();  //para carga las cartas
        gestorArchivos.cargaTierras();   //para cargas las tierras
        menuInicioSesion(gestorUsuarios, sistema, gestorArchivos);
    }

    /**
     * Muestra el menu de inicio de sesion y sus diferentes opciones.
     * @param gestorUsuarios el gestor de usuarios.
     * @param sistemaMazo el sistema de gestion de los mazos.
     * @param gestorArchivos el gestor de archivos.
     */
    public static void menuInicioSesion(GestorUsuarios gestorUsuarios, ISistemaMazo sistemaMazo, GestorArchivos gestorArchivos){
        String opcion;
        while(true){
            //muestra el menu y sus opciones
            StdOut.println("=============================\n" +
                    "¡Bienvenido al Deck Builder!\n" +
                    "   ¿Que desea hacer?\n" +
                    "=============================");
            StdOut.println("[1] Iniciar sesion");
            StdOut.println("[2] Registrarse");
            StdOut.println("[3] Salir");
            opcion = StdIn.readString();

            if (opcion.equals("1")){
                iniciarSesion(gestorUsuarios, sistemaMazo, gestorArchivos);  //si el usuario elige 1, le permite iniciar sesion
                continue;
            }
            if (opcion.equals("2")){
                registrarse(gestorUsuarios);  //si el usuario elige 2, le permite registrarse
                continue;
            }
            if (opcion.equals("3")){  //termina el programa
                break;
            }
            StdOut.println("Ingrese una opcion valida por favor.");
        }
    }

    /**
     * Inicia sesion en el sistema.
     * @param gestorUsuarios el gestor de usuarios.
     * @param sistemaMazo el sistema de gestion de mazos.
     * @param gestorArchivos el gestor de archivos.
     */
    public static void iniciarSesion(GestorUsuarios gestorUsuarios, ISistemaMazo sistemaMazo, GestorArchivos gestorArchivos) {

        //le pregunta los datos al usuario para iniciar sesion en el sistema

        StdOut.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = StdIn.readString();
        StdOut.print("Ingrese su contraseña: ");
        String contrasenia = StdIn.readString();

        if (gestorUsuarios.iniciarSesion(nombreUsuario, contrasenia)) {  // si los datos son correctos, el usuario inicia sesion con exito
            StdOut.println("Inicio de sesion exitoso.");
            menuPrincipal(gestorUsuarios, sistemaMazo, gestorArchivos);
        } else {
            StdOut.println("Nombre de usuario o contraseña incorrectos.");  //en el caso contrario, no puede acceder al sistema
        }
    }

    /**
     * Registra un usuario en el sistema.
     * @param gestorUsuarios el gestor de usuarios.
     */
    public static void registrarse(GestorUsuarios gestorUsuarios) {

        //le pregunta los datos para registrarse al usuario
        StdOut.print("Ingrese un nombre de usuario: ");
        String nombreUsuario = StdIn.readString();
        StdOut.print("Ingrese una contraseña: ");
        String contrasenia = StdIn.readString();

        Mazo[] mazos = new Mazo[0]; //los usuarios se registran inicialmwnte sin mazos

        gestorUsuarios.registrarUsuario(nombreUsuario, contrasenia, mazos, 0);
    }


    /**
     * Muestra el menu principal luego del inicio de sesion y muestra las diferentes opciones al usuario.
     * @param gestorUsuarios el gestor de usuarios.
     * @param sistemaMazo el sistema de gestion de mazos.
     * @param gestorArchivos el gestor de archivos.
     */
    public static void menuPrincipal(GestorUsuarios gestorUsuarios, ISistemaMazo sistemaMazo, GestorArchivos gestorArchivos) {
        String opcion;
        while (true) {
            //le muestra las opciones del menu principal al usuario
            StdOut.println("=============================\n" +
                    "       Menu Prinicipal\n" +
                    "=============================");
            StdOut.println("Ingrese una opcion:");
            StdOut.println("[1] Contruir Mazo");
            StdOut.println("[2] Ver mis Mazos");
            StdOut.println("[3] Buscar Carta");
            StdOut.println("[4] Cerrar Sesion");
            opcion = StdIn.readString();

            if (opcion.equals("1")) {
                menuConstruirMazo(gestorUsuarios, sistemaMazo, gestorArchivos);
                continue;
            }
            if (opcion.equals("2")) {
                verMisMazos(gestorUsuarios, sistemaMazo, gestorArchivos);
                continue;
            }
            if (opcion.equals("3")) {
                buscarCarta(gestorArchivos);
                continue;
            }
            if (opcion.equals("4")) {
                StdOut.println("Cerrando sesion...");
                break;
            }
        }
    }

    /**
     * Muestra el menu para construir un mazo.
     * @param gestorUsuarios el gestor de usuarios
     * @param sistemaMazo el sistema de gestion de mazos
     * @param gestorArchivos el gestor de archivos
     */
    public static void menuConstruirMazo(GestorUsuarios gestorUsuarios, ISistemaMazo sistemaMazo, GestorArchivos gestorArchivos) {
        String opcion;
        while (true) {
            StdOut.println("=============================\n" +
                    "    Menu Construir Mazo\n" +
                     "=============================");
            StdOut.println("Ingrese una opcion:");
            StdOut.println("[1] Crear nuevo Mazo");
            StdOut.println("[2] Modificar Mazo existente");
            StdOut.println("[3] Regresar");
            opcion = StdIn.readString();

            if (opcion.equals("1")) {
                crearMazo(gestorUsuarios, sistemaMazo, gestorArchivos);
                continue;
            }
            if (opcion.equals("2")) {
                modificarMazo(gestorUsuarios);
                continue;
            }
            if (opcion.equals("3")) {
                break;
            }

            StdOut.println("Ingrese una opcion valida por favor.");

        }
    }

    /**
     * Muestra el meno para crea un nuevo mazo ofreciendole las distintas opciones al usuario.
     *
     * @param gestorUsuarios el gestor de usuarios.
     * @param sistemaMazo el sistema de gestion de mazos.
     * @param gestorArchivos el gestor de archivos.
     */
    public static void crearMazo(GestorUsuarios gestorUsuarios, ISistemaMazo sistemaMazo, GestorArchivos gestorArchivos) {
        StdOut.println("Creando un nuevo mazo.");
        Mazo nuevoMazo = new Mazo(60);

        String opcion;
        while (true) {
            StdOut.println("=============================\n" +
                    "   Menu Crear Nuevo Mazo\n" +
                    "=============================");
            StdOut.println("Ingrese una opcion:");
            StdOut.println("[1] Añadir Carta");
            StdOut.println("[2] Eliminar Carta");
            StdOut.println("[3] Buscar Carta");
            StdOut.println("[4] Modificar Sideboard");
            StdOut.println("[5] Regresar");
            opcion = StdIn.readString();

            if (opcion.equals("1")) {
                añadirCarta(nuevoMazo, gestorArchivos);
                continue;
            }
            if (opcion.equals("2")) {
                eliminarCarta(nuevoMazo);
                continue;
            }
            if (opcion.equals("3")) {
                buscarCarta(gestorArchivos);
                continue;
            }
            if (opcion.equals("4")) {
                menuModificarSideboard(gestorUsuarios, sistemaMazo, gestorArchivos);
                continue;
            }
            if (opcion.equals("5")) {
                StdOut.println("Volviendo...");
                break;
            }

            StdOut.println("Ingrese una opcion valida por favor");

        }
    }

    /**
     * Muestra el menu para modificar el sideboard.
     * @param gestorUsuarios el gestor de usuarios.
     * @param sistemaMazo el sistema de gestion de mazos.
     * @param gestorArchivos el gestor de archivos.
     */
    public static void menuModificarSideboard(GestorUsuarios gestorUsuarios, ISistemaMazo sistemaMazo, GestorArchivos gestorArchivos) {

        String opcion;
        while (true) {
            StdOut.println("=============================\n" +
                    "   Menu Modificar Sideboard\n" +
                        "=============================");
            StdOut.println("Ingrese una opcion:");
            StdOut.println("[1] Añadir Sideboard");
            StdOut.println("[2] Eliminar Sideboard");
            StdOut.println("[3] Buscar Sideboard");
            StdOut.println("[4] Regresar");
            opcion = StdIn.readString();

            if (opcion.equals("1")) {
                añadirSideboard(sistemaMazo);
                continue;
            }
            if (opcion.equals("2")) {
                eliminarSideboard(sistemaMazo);
                continue;
            }
            if (opcion.equals("3")) {
                buscarSideboard(sistemaMazo);
            }
            if (opcion.equals("4")) {
                StdOut.println("Volviendo...");
                break;
            }

            StdOut.println("Ingrese una opcion valida por favor.");

        }
    }

    /**
     * Añade una carta al sideboard junto a la cantidad deseada.
     * @param sistemaMazo el sistema de gestión de mazos
     */
    public static void añadirSideboard(ISistemaMazo sistemaMazo) {
        StdOut.println("Ingrese el nombre de la carta a añadir:");
        String nombreSideboard = StdIn.readString();
        StdOut.println("Ingrese la cantidad de copias:");
        int cantidadCopias = StdIn.readInt();

        Carta sideboard = sistemaMazo.buscarSideboard(nombreSideboard);
        if (sideboard != null) {
            sistemaMazo.agregarSideboard(sideboard, cantidadCopias);
            StdOut.println("Carta añadida al sideboard.");
        }

        StdOut.println("Carta no encontrada.");
    }

    /**
     * Elimina una carta del sideboard junto con la cantidad que desea eliminar.
     * @param sistemaMazo el sistema de gestion de mazos.
     */
    public static void eliminarSideboard(ISistemaMazo sistemaMazo) {
        StdOut.println("Ingrese el nombre de la carta de sideboard a eliminar:");
        String nombreSideboard = StdIn.readString();
        StdOut.println("Ingrese la cantidad de copias:");
        int cantidadCopias = StdIn.readInt();

        //verificar que sea posible eliminar la carta del sideboard
        try {
            sistemaMazo.eliminarSideboard(nombreSideboard, cantidadCopias);
            StdOut.println("Carta eliminada del sideboard.");
        } catch (IllegalArgumentException e) {
            StdOut.println("no se puede eliminar");
        }
    }

    /**
     * Busca una carta en el sideboard.
     * @param sistemaMazo el sistema de gestion de mazos.
     */
    public static void buscarSideboard(ISistemaMazo sistemaMazo) {
        StdOut.println("Ingrese el nombre de la carta a buscar:");
        String nombreCarta = StdIn.readString();

        Carta carta = sistemaMazo.buscarSideboard(nombreCarta);
        if (carta != null) {
            StdOut.println("Carta encontrada: " + carta.getNombre() + " ||  Cantidad: " + carta.getCantidad());
        }
        StdOut.println("Carta no encontrada en el sideboard.");

    }

    /**
     * Añade una carta al mazo.
     * @param mazo el mazo al que se va a añadir la carta.
     * @param gestorArchivos el gestor de archivos.
     */
    public static void añadirCarta(Mazo mazo, GestorArchivos gestorArchivos) {

        StdOut.println("Ingrese el nombre de la carta que desea añadir:");
        String nombreCarta = StdIn.readString();


        Carta carta = gestorArchivos.buscarCarta(nombreCarta);
        if (carta == null) {  //verificar si la carta es nula
            StdOut.println("La carta no se encontro en el sistema, intentalo nuevamente.");
            return;
        }

        StdOut.println("Ingrese la cantidad de copias que desea añadir al mazo;");
        int cantidadCopias = StdIn.readInt();

        if (cantidadCopias < 1) {  //verificar si la cantidad es menor que 1
            StdOut.println("La cantidad de copias no puede ser menor que 1, intentalo nuevamente.");
            return;
        }

        //añadir la carta al mazo con la cantidad especificada
        mazo.agregarCarta(carta, cantidadCopias);

        StdOut.println("Carta añadida con exito al mazo.");
    }

    /**
     * Elimina una carta de un mazo.
     * @param mazo el mazo del que se va a eliminar la carta.
     */
    public static void eliminarCarta(Mazo mazo) {
        while (true) {
            //preguntar la carta que se desea eliminar
            StdOut.println("Ingrese el nombre de la carta que desea eliminar:");
            String nombreCarta = StdIn.readString();

            //verificar que sea posible eliminar la carta
            try {
                if (mazo.buscarCarta(nombreCarta) == null) {
                    StdOut.println("La carta no se encontro en el mazo.");
                    break;
                }

                StdOut.println("Ingrese la cantidad de copias que desea eliminar del mazo:");
                int cantidadCopias = StdIn.readInt();

                mazo.eliminarCarta(nombreCarta, cantidadCopias);

                StdOut.println("Carta eliminada con exito del mazo.");
                break;
            } catch (IllegalArgumentException e) {
                StdOut.println("No se puede eliminar");
            }
        }
    }

    /**
     * Busca una carta en el gestor y muestra su informacion.
     * @param gestorArchivos el gestor de archivos
     */
    public static void buscarCarta(GestorArchivos gestorArchivos) {
        StdOut.print("Ingrese el nombre de la carta que desea buscar: ");
        String nombreCarta = StdIn.readString();
        String informacionCarta = gestorArchivos.buscarYobtenerInformacionCarta(nombreCarta);
        StdOut.println(informacionCarta);
    }

    /**
     * Muestra los mazos del usuario que se encuentra en el sistema.
     * @param gestorUsuarios el gestor de usuarios.
     * @param sistemaMazo el sistema de gestion de mazos.
     * @param gestorArchivos el gestor de archivos.
     */
    public static void verMisMazos(GestorUsuarios gestorUsuarios, ISistemaMazo sistemaMazo, GestorArchivos gestorArchivos) {
        Usuario usuario = gestorUsuarios.getUsuarioActual();

        //verificar si el usuario tiene mazos
        if (usuario == null || usuario.getMazos().length == 0) {
            StdOut.println("No tienes mazos. ");
        }
        //obtener los mazos del usuario
        Mazo[] mazos = usuario.getMazos();

        StdOut.println("Mazos: ");
        for (int i = 0; i < usuario.getCantidadMazos(); i++) {
            StdOut.println((i+1));
            StdOut.print(Arrays.toString(mazos[i].getCartas()));

        }
    }
    public static void modificarMazo(GestorUsuarios gestorUsuarios) {
        StdOut.println("Elija el mazo que desea modificar:");

    }

}