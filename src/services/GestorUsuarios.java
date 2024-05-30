package services;

import edu.princeton.cs.stdlib.StdOut;
import model.Mazo;
import model.Usuario;

/**
 * La clase gestorUsuarios gestiona el registro de usuarios e inicio de sesion en el sistema.
 */
public class GestorUsuarios {

    private Usuario[] usuarios;
    private String[] registroInicioSesion;
    private int cantidadUsuariosActual;
    private int cantidadIniciosSesionActual;
    private int cantidadMaximaUsuarios;
    private int cantidadMaximaInicioSesion;
    private Usuario usuarioActual;

    /**
     * Constructor de la clase gestorUsuarios.
     * @param cantidadMaximaUsuarios la cantidad maxima de usuarios en el sistema.
     * @param cantidadMaximaInicioSesion la cantidad maxima de inicios de sesion en el sistema.
     */
    public GestorUsuarios(int cantidadMaximaUsuarios, int cantidadMaximaInicioSesion) {
        this.usuarios = new Usuario[cantidadMaximaUsuarios];
        this.registroInicioSesion = new String[cantidadMaximaInicioSesion];
        this.cantidadMaximaUsuarios = cantidadMaximaUsuarios;
        this.cantidadMaximaInicioSesion = cantidadMaximaInicioSesion;
        this.cantidadUsuariosActual = 0;
        this.cantidadIniciosSesionActual = 0;
    }

    /**
     * Registra un usuario en el sistema.
     * @param nombreUsuario el nombre de usuario a registrarse.
     * @param contrasenia la contraseña del usuario.
     * @param mazos los mazos que tiene el usuario.
     * @param cantidadMazos la cantidad de mazos que tiene el usuario.
     */
    public void registrarUsuario(String nombreUsuario, String contrasenia, Mazo[] mazos, int cantidadMazos) {

        //verificar si se pueden agregar mas usuarios
        if (cantidadUsuariosActual < cantidadMaximaUsuarios) {
            if (buscarUsuario(nombreUsuario) == null) { //verificar si ya existe
                Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasenia, mazos, cantidadMazos);
                usuarios[cantidadUsuariosActual++] = nuevoUsuario; //agregar al usuario
                StdOut.println("Usuario registrado correctamente: " + nombreUsuario);
            }
            StdOut.println("El nombre de usuario ya existe: " + nombreUsuario);
        }
    }

    /**
     * Iniciar sesion con el nombre y constraseña de un usario ya registrado.
     * @param nombreUsuario el nombre de usuario del usuario que desea iniciar sesion.
     * @param contrasenia la contraseña del usuario.
     * @return true si el inicio de sesion se realizo correctamente.
     */
    public boolean iniciarSesion(String nombreUsuario, String contrasenia) {

        Usuario usuario = buscarUsuario(nombreUsuario);

        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {  //verificar si el usuario y la contraseña son correctos
            registrarInicioSesion(nombreUsuario);   // registra el inicio de sesion
            StdOut.println("Inicio de sesion realizado correctamente para el usuario: " + nombreUsuario);
            return true;

        } else {

            StdOut.println("Inicio de sesion fallido.");
            return false;
        }
    }

    /**
     * Busca un usuario en el sistema por su nombre de usuario.
     * @param nombreUsuario el nombre de usuario a buscar.
     * @return el usuario encontrado.
     */
    private Usuario buscarUsuario(String nombreUsuario) {
        for (int i = 0; i < this.cantidadUsuariosActual; i++) {   //itera sobre el arreglo de usuarios
            if (usuarios[i].getNombreUsuario().equals(nombreUsuario)) {   //verificar si se encuentra al usuario
                return usuarios[i];
            }
        }
        return null;
    }

    /**
     * Registra el inicio de sesion del usuario.
     * @param nombreUsuario el nombre de usuario que realiza el inicio de sesion.
     */
    private void registrarInicioSesion(String nombreUsuario) {
        if (this.cantidadIniciosSesionActual < this.cantidadMaximaInicioSesion) {
            this.registroInicioSesion[this.cantidadIniciosSesionActual++] = nombreUsuario;
            StdOut.println("Inicio de sesión registrado para: " + nombreUsuario);
        } else {
            StdOut.println("No se pueden registrar más inicios de sesión. Límite alcanzado.");
        }
    }

    /**
     * Obtiene el usuario que ha iniciado sesion.
     * @return el usuario que ha iniciado sesion.
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

}


